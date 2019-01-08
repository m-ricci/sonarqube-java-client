package com.karolina.project.sonarqube.service;

import com.karolina.project.sonarqube.bean.review.result.*;
import com.karolina.project.sonarqube.bean.sonarqube.response.IssueElement;
import com.karolina.project.sonarqube.bean.sonarqube.response.MeasureElement;
import com.karolina.project.sonarqube.bean.sonarqube.response.ResponseIssues;
import com.karolina.project.sonarqube.bean.sonarqube.response.ResponseMeasures;
import com.karolina.project.sonarqube.exception.ProjectNotFoundException;
import com.karolina.project.sonarqube.exception.SonarqubeNotReachableException;
import com.karolina.project.sonarqube.utils.ResponseConverter;
import com.karolina.project.sonarqube.utils.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.karolina.project.sonarqube.service.SonarqubeRestService.*;

@Service
public class SonarqubeService {

    private static final Logger logger = LoggerFactory.getLogger(SonarqubeService.class);

    private SonarqubeRestService sonarqubeRestService;

    public SonarqubeService(SonarqubeRestService sonarqubeRestService) {
        this.sonarqubeRestService = sonarqubeRestService;
    }

    public ResultReview getReview(String projectKey) throws SonarqubeNotReachableException, ProjectNotFoundException {

        if(sonarqubeRestService.getComponent(projectKey) == null)
            throw new ProjectNotFoundException("unable to find project: " + projectKey);

        ResponseEntity<String> metricsResponse = sonarqubeRestService.getMetrics(projectKey, METRICS_MEASURE_LINES_OF_CODE, METRICS_MEASURE_COMMENTS, METRICS_MEASURE_DUPLICATIONS, METRICS_MEASURE_BUGS, METRICS_MEASURE_VULNERABILITIES, METRICS_MEASURE_CODE_SMELLS);
        ResponseEntity<String> issuesResponse = sonarqubeRestService.getIssues(projectKey);

        if(metricsResponse == null || metricsResponse.getStatusCode() != HttpStatus.OK || metricsResponse.getBody() == null
                || issuesResponse == null || issuesResponse.getStatusCode() != HttpStatus.OK || issuesResponse.getBody() == null)
            throw new ProjectNotFoundException("unable to find project: " + projectKey + ", missing metrics or issues");

        ResponseMeasures measures = ResponseConverter.getMeasures(metricsResponse.getBody());
        ResponseIssues issues = ResponseConverter.getIssues(issuesResponse.getBody());

        if(measures == null || issues == null)
            throw new ProjectNotFoundException("unable to find project: " + projectKey + ", unable to convert the response of metrics or issues");

        ResultValue codeLines = null, comments = null, duplications = null, bugs = null, codeSmells = null, vulnerabilities = null;

        for(MeasureElement measure : measures.getMeasures()) {
            switch (measure.getMetric()) {
                case METRICS_MEASURE_LINES_OF_CODE:
                    codeLines = new ResultValue(measure.getValue(), StatusEnum.OK);
                    break;
                case METRICS_MEASURE_COMMENTS:
                    comments = new ResultValue(measure.getValue(), StatusEnum.OK);
                    break;
                case METRICS_MEASURE_DUPLICATIONS:
                    duplications = new ResultValue(measure.getValue(), StatusEnum.OK);
                    break;
                case METRICS_MEASURE_BUGS:
                    bugs = new ResultValue(measure.getValue(), StatusEnum.OK);
                    break;
                case METRICS_MEASURE_CODE_SMELLS:
                    codeSmells = new ResultValue(measure.getValue(), StatusEnum.OK);
                    break;
                case METRICS_MEASURE_VULNERABILITIES:
                    vulnerabilities = new ResultValue(measure.getValue(), StatusEnum.OK);
                    break;
                default:
                    logger.debug("{} metric not expected", measure.getMetric());
            }
        }

        if(codeLines == null || comments == null || duplications == null || bugs == null || codeSmells == null || vulnerabilities == null)
            throw new ProjectNotFoundException("unable to find project: " + projectKey + ", some of the mandatory information are missing");

        float commentPercentage = comments.getValue() / codeLines.getValue();
        if(commentPercentage < 0.1f || commentPercentage > 0.15f)
            comments.setStatus(StatusEnum.ERROR);

        float duplicationPercentage = duplications.getValue() / codeLines.getValue();
        if(duplicationPercentage > 0.02f)
            duplications.setStatus(StatusEnum.ERROR);



        float numBlocker = 0, numCritical = 0, numMajor = 0, numMinor = 0;

        for(IssueElement issue : issues.getIssues()) {
            switch (issue.getSeverity()) {
                case BLOCKER:
                    numBlocker++;
                    break;
                case CRITICAL:
                    numCritical++;
                    break;
                case MAJOR:
                    numMajor++;
                    break;
                case MINOR:
                    numMinor++;
                    break;
                default:
                    logger.debug("{} severity not expected");
            }
        }

        ResultValue blocker = new ResultValue(numBlocker, StatusEnum.OK);
        ResultValue critical = new ResultValue(numCritical, StatusEnum.OK);
        ResultValue major = new ResultValue(numMajor, StatusEnum.OK);
        ResultValue minor = new ResultValue(numMinor, StatusEnum.OK);

        int numError = 0;
        int numWarning = 0;
        int numOk = 0;

        StatusEnum codeQualityStatus;
        if(comments.getStatus() == StatusEnum.OK && duplications.getStatus() == StatusEnum.OK) {
            codeQualityStatus = StatusEnum.OK;
            numOk++;
        }
        else if(comments.getStatus() == StatusEnum.ERROR && duplications.getStatus() == StatusEnum.ERROR) {
            codeQualityStatus = StatusEnum.ERROR;
            numError++;
        }
        else {
            codeQualityStatus = StatusEnum.WARNING;
            numWarning++;
        }

        float bugsStatusComputation = blocker.getValue() + critical.getValue() * 0.7f + major.getValue() * 0.4f + minor.getValue() * 0.2f;
        bugsStatusComputation /= codeLines.getValue();
        StatusEnum bugsResultStatus;
        if(bugsStatusComputation <= 0.02) {
            bugsResultStatus = StatusEnum.OK;
            numOk++;
        }
        else if(bugsStatusComputation <= 0.07) {
            bugsResultStatus = StatusEnum.WARNING;
            numWarning++;
        }
        else {
            bugsResultStatus = StatusEnum.ERROR;
            numError++;
        }

        StatusEnum securityStatusResult;
        if(vulnerabilities.getValue() <= 0) {
            securityStatusResult = StatusEnum.OK;
            numOk++;
        }
        else {
            securityStatusResult = StatusEnum.ERROR;
            numError++;
        }

        StatusEnum finalResultStatus;
        if(numOk == 0 && numError > 0)
            finalResultStatus = StatusEnum.ERROR;
        else if(numOk == 0 && numError == 0)
            finalResultStatus = StatusEnum.WARNING;
        else if(numOk == 1 && numError == 0)
            finalResultStatus = StatusEnum.WARNING;
        else if(numOk == 1 && numError > 0)
            finalResultStatus = StatusEnum.ERROR;
        else if(numOk == 2 && numError == 0)
            finalResultStatus = StatusEnum.OK;
        else if(numOk == 2 && numError > 0)
            finalResultStatus = StatusEnum.WARNING;
        else
            finalResultStatus = StatusEnum.OK;

        ResultValue finalResultCodeQuality = new ResultValue(0, codeQualityStatus);
        ResultValue finalResultBugs = new ResultValue(bugsStatusComputation, bugsResultStatus);
        ResultValue finalResultSecurity = new ResultValue(vulnerabilities.getValue(), securityStatusResult);
        ResultValue finalResult = new ResultValue(0, finalResultStatus);

        return new ResultReview(
            new ResultCodeQuality(codeLines, comments, duplications, finalResultCodeQuality),
            new ResultBugs(bugs, blocker, critical, major, minor, finalResultBugs),
            new ResultSecurity(vulnerabilities, finalResultSecurity),
            finalResult
        );
    }

}
