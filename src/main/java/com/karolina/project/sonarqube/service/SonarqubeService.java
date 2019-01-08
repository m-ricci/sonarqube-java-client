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
                    codeLines = new ResultValue(measure.getValue(), StatusEnum.OK); // TODO: sostituire con la logica di calcolo del valore
                    break;
                case METRICS_MEASURE_COMMENTS:
                    comments = new ResultValue(measure.getValue(), StatusEnum.OK); // TODO: sostituire con la logica di calcolo del valore
                    break;
                case METRICS_MEASURE_DUPLICATIONS:
                    duplications = new ResultValue(measure.getValue(), StatusEnum.OK); // TODO: sostituire con la logica di calcolo del valore
                    break;
                case METRICS_MEASURE_BUGS:
                    bugs = new ResultValue(measure.getValue(), StatusEnum.OK); // TODO: sostituire con la logica di calcolo del valore
                    break;
                case METRICS_MEASURE_CODE_SMELLS:
                    codeSmells = new ResultValue(measure.getValue(), StatusEnum.OK); // TODO: sostituire con la logica di calcolo del valore
                    break;
                case METRICS_MEASURE_VULNERABILITIES:
                    vulnerabilities = new ResultValue(measure.getValue(), StatusEnum.OK); // TODO: sostituire con la logica di calcolo del valore
                    break;
                default:
                    logger.debug("{} metric not expected", measure.getMetric());
            }
        }

        if(codeLines == null || comments == null || duplications == null || bugs == null || codeSmells == null || vulnerabilities == null)
            throw new ProjectNotFoundException("unable to find project: " + projectKey + ", some of the mandatory information are missing");

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

        ResultValue blocker = new ResultValue(numBlocker, StatusEnum.OK); // TODO: sostituire con la funzione di calcolo
        ResultValue critical = new ResultValue(numCritical, StatusEnum.OK); // TODO: sostituire con la funzione di calcolo
        ResultValue major = new ResultValue(numMajor, StatusEnum.OK); // TODO: sostituire con la funzione di calcolo
        ResultValue minor = new ResultValue(numMinor, StatusEnum.OK); // TODO: sostituire con la funzione di calcolo

        ResultValue finalResultCodeQuality = new ResultValue(codeLines.getValue() + comments.getValue() + duplications.getValue(), StatusEnum.OK); // TODO: sostituire con la funzione di calcolo corretta
        ResultValue finalResultBugs = new ResultValue(bugs.getValue() + blocker.getValue() + critical.getValue() + major.getValue() + minor.getValue(), StatusEnum.OK); // TODO: sostituire con la funzione di calcolo corretta
        ResultValue finalResultSecurity = new ResultValue(vulnerabilities.getValue(), StatusEnum.OK); // TODO: sostituire con la funzione di calcolo corretta
        ResultValue finalResult = new ResultValue(finalResultCodeQuality.getValue() + finalResultBugs.getValue() + finalResultSecurity.getValue(), StatusEnum.OK); // TODO: sostituire con la funzione di calcolo corretta

        return new ResultReview(
            new ResultCodeQuality(codeLines, comments, duplications, finalResultCodeQuality),
            new ResultBugs(bugs, blocker, critical, major, minor, finalResultBugs),
            new ResultSecurity(vulnerabilities, finalResultSecurity),
            finalResult
        );
    }

}
