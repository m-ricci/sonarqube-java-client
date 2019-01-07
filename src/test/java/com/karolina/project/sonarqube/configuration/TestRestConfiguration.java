package com.karolina.project.sonarqube.configuration;

import com.karolina.project.sonarqube.bean.configuration.RestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRestConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(TestRestConfiguration.class);

    @Autowired
    private RestConfiguration restConfiguration;

    @Test
    public void testRestProperties() {
        logger.info(restConfiguration.getSonarqubeUrl());
        logger.info(restConfiguration.getSonarqubeLogin());
        logger.info(restConfiguration.getSonarqubePassword());
    }

}
