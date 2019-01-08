package com.karolina.project.sonarqube.service;

import com.karolina.project.sonarqube.bean.review.result.ResultReview;
import com.karolina.project.sonarqube.exception.ProjectNotFoundException;
import com.karolina.project.sonarqube.exception.SonarqubeNotReachableException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSonarqubeService {

    public static final Logger logger = LoggerFactory.getLogger(TestSonarqubeService.class);

    @Autowired
    private SonarqubeService sonarqubeService;

    @Test
    public void test_01_getReview() {
        try {
            ResultReview review = sonarqubeService.getReview("ExamCameraProject");
        } catch(SonarqubeNotReachableException e) {
            logger.warn("unable to connect to SonarQube");
        } catch(ProjectNotFoundException e) {
            logger.warn("unable to find project ");
        }
    }

}
