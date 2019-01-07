package com.karolina.project.sonarqube.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSonarqubeRestService {

    private static final Logger logger = LoggerFactory.getLogger(TestSonarqubeRestService.class);

    @Autowired
    private SonarqubeRestService service;

    @Test
    public void login() {
        boolean response = service.login();
        logger.info("{}", response);
    }

}
