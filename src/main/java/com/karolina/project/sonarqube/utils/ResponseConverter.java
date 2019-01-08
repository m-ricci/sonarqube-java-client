package com.karolina.project.sonarqube.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karolina.project.sonarqube.bean.sonarqube.response.ResponseIssues;
import com.karolina.project.sonarqube.bean.sonarqube.response.ResponseMeasures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ResponseConverter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseConverter.class);

    public static ResponseMeasures getMeasures(String body) {
        if(body == null)
            return null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(body, ResponseMeasures.class);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static ResponseIssues getIssues(String body) {
        if(body == null)
            return null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(body, ResponseIssues.class);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}
