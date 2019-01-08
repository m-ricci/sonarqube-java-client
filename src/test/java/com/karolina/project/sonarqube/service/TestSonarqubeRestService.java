package com.karolina.project.sonarqube.service;

import com.karolina.project.sonarqube.exception.SonarqubeNotReachableException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static com.karolina.project.sonarqube.service.SonarqubeRestService.METRICS_MEASURE_LINES_OF_CODE;
import static com.karolina.project.sonarqube.service.SonarqubeRestService.METRICS_MEASURE_COMMENTS;
import static com.karolina.project.sonarqube.service.SonarqubeRestService.METRICS_MEASURE_DUPLICATIONS;
import static com.karolina.project.sonarqube.service.SonarqubeRestService.METRICS_MEASURE_BUGS;
import static com.karolina.project.sonarqube.service.SonarqubeRestService.METRICS_MEASURE_CODE_SMELLS;
import static com.karolina.project.sonarqube.service.SonarqubeRestService.METRICS_MEASURE_VULNERABILITIES;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSonarqubeRestService {

    private static final Logger logger = LoggerFactory.getLogger(TestSonarqubeRestService.class);

    @Autowired
    private SonarqubeRestService service;

    @Test
    public void test_01_login() {
        try {
            boolean response = service.login();
            Assert.assertNotNull(response);
            Assert.assertTrue(response);
        } catch(SonarqubeNotReachableException e) {
            logger.warn("unable to connect to SonarQube");
        }
    }

    @Test
    public void test_02_getComponent() {
        try {
            ResponseEntity<String> response = service.getComponent("ExamCameraProject");
            if(response != null)
                Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
            logger.info("{}", response.getBody());
        } catch(SonarqubeNotReachableException e) {
            logger.warn("unable to connect to SonarQube");
        }
    }

    @Test
    public void test_03_getMeasure() {
        try {
            ResponseEntity response = service.getMetrics("ExamCameraProject", METRICS_MEASURE_LINES_OF_CODE, METRICS_MEASURE_COMMENTS, METRICS_MEASURE_DUPLICATIONS, METRICS_MEASURE_BUGS, METRICS_MEASURE_VULNERABILITIES, METRICS_MEASURE_CODE_SMELLS);
            if(response != null)
                Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
            logger.info("{}", response.getBody());
        } catch(SonarqubeNotReachableException e) {
            logger.warn("unable to connect to SonarQube");
        }
    }

    @Test
    public void test_04_getIssues() {
        try {
            ResponseEntity response = service.getIssues("ExamCameraProject");
            if(response != null)
                Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
            logger.info("{}", response.getBody());
        } catch(SonarqubeNotReachableException e) {
            logger.warn("unable to connect to SonarQube");
        }
    }

    @Test
    public void test_05_logout() {
        try {
            boolean response = service.logout();
            Assert.assertTrue(response);
        } catch(SonarqubeNotReachableException e) {
            logger.warn("unable to connect to SonarQube");
        }
    }

}
