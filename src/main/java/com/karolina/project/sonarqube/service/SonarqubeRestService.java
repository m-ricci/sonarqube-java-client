package com.karolina.project.sonarqube.service;

import com.karolina.project.sonarqube.bean.configuration.RestConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class SonarqubeRestService {

    private static final Logger logger = LoggerFactory.getLogger(SonarqubeRestService.class);

    private RestConfiguration restConfiguration;

    private static final String API_LOGIN = "/api/authentication/login";

    public SonarqubeRestService(RestConfiguration restConfiguration) {
        this.restConfiguration = restConfiguration;
    }

    public boolean login() {
        try {
            RestTemplate template = new RestTemplate();
            String resourceUrl = restConfiguration.getSonarqubeUrl() + API_LOGIN;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("login", restConfiguration.getSonarqubeLogin());
            map.add("password", restConfiguration.getSonarqubePassword());
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            ResponseEntity<String> response = template.postForEntity(resourceUrl, request, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (RestClientException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

}
