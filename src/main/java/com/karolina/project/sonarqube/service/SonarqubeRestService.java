package com.karolina.project.sonarqube.service;

import com.karolina.project.sonarqube.bean.configuration.RestConfiguration;
import com.karolina.project.sonarqube.exception.SonarqubeNotReachableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class SonarqubeRestService {

    private static final Logger logger = LoggerFactory.getLogger(SonarqubeRestService.class);

    private RestConfiguration restConfiguration;

    public static final String METRICS_MEASURE_LINES_OF_CODE = "ncloc";
    public static final String METRICS_MEASURE_COMMENTS = "comment_lines";
    public static final String METRICS_MEASURE_DUPLICATIONS = "duplicated_lines";
    public static final String METRICS_MEASURE_BUGS = "bugs";
    public static final String METRICS_MEASURE_CODE_SMELLS = "code_smells";
    public static final String METRICS_MEASURE_VULNERABILITIES = "vulnerabilities";

    private static final String API_LOGIN = "/api/authentication/login";
    private static final String API_LOGOUT = "/api/authentication/logout";
    private static final String API_MEASURE = "/api/measures/search?metricKeys={metricKeys}&projectKeys={projectKeys}";
    private static final String API_COMPONENT = "/api/components/show?component={component}";
    private static final String API_ISSUES = "/api/issues/search?componentKeys={componentKeys}";

    public SonarqubeRestService(RestConfiguration restConfiguration) {
        this.restConfiguration = restConfiguration;
    }

    public boolean login() throws SonarqubeNotReachableException {
        try {
            logger.info("SonarqubeRestService::login");
            RestTemplate template = new RestTemplate();
            String resourceUrl = restConfiguration.getSonarqubeUrl() + API_LOGIN;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("login", restConfiguration.getSonarqubeLogin());
            map.add("password", restConfiguration.getSonarqubePassword());
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            ResponseEntity<String> response = template.postForEntity(resourceUrl, request, String.class);
            logger.debug("{}", response);
            return response.getStatusCode() == HttpStatus.OK;
        } catch(ResourceAccessException e) {
            throw new SonarqubeNotReachableException("unable to connect to sonarqube");
        } catch (RestClientException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public boolean logout() throws SonarqubeNotReachableException {
        try {
            logger.info("SonarqubeRestService::logout");
            RestTemplate template = new RestTemplate();
            String resourceUrl = restConfiguration.getSonarqubeUrl() + API_LOGOUT;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            ResponseEntity<String> response = template.postForEntity(resourceUrl, request, String.class);
            logger.debug("{}", response);
            return response.getStatusCode() == HttpStatus.OK;
        } catch(ResourceAccessException e) {
            throw new SonarqubeNotReachableException("unable to connect to sonarqube");
        } catch (RestClientException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public ResponseEntity<String> getComponent(String projectKey) throws SonarqubeNotReachableException {
        try {
            logger.info("SonarqubeRestService::getComponent");
            RestTemplate template = new RestTemplate();
            String resourceUrl = restConfiguration.getSonarqubeUrl() + API_COMPONENT;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> map = new HashMap<>();
            map.put("component", projectKey);
            ResponseEntity<String> response = template.getForEntity(resourceUrl, String.class, map);
            logger.debug("{}", response);
            return response;
        } catch(ResourceAccessException e) {
            throw new SonarqubeNotReachableException("unable to connect to sonarqube");
        } catch (RestClientException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public ResponseEntity<String> getMetrics(String projectKey, String... metrics) throws SonarqubeNotReachableException {
        try {
            logger.info("SonarqubeRestService::getCodeQuality");
            RestTemplate template = new RestTemplate();
            String resourceUrl = restConfiguration.getSonarqubeUrl() + API_MEASURE;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> map = new HashMap<>();
            map.put("metricKeys", String.join(",", metrics));
            map.put("projectKeys", projectKey);
            ResponseEntity<String> response = template.getForEntity(resourceUrl, String.class, map);
            logger.debug("{}", response);
            return response;
        } catch(ResourceAccessException e) {
            throw new SonarqubeNotReachableException("unable to connect to sonarqube");
        } catch (RestClientException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public ResponseEntity<String> getIssues(String projectKey) throws SonarqubeNotReachableException {
        try {
            logger.info("SonarqubeRestService::getIssues");
            RestTemplate template = new RestTemplate();
            String resourceUrl = restConfiguration.getSonarqubeUrl() + API_ISSUES;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> map = new HashMap<>();
            map.put("componentKeys", projectKey);
            ResponseEntity<String> response = template.getForEntity(resourceUrl, String.class, map);
            logger.debug("{}", response);
            return response;
        } catch(ResourceAccessException e) {
            throw new SonarqubeNotReachableException("unable to connect to sonarqube");
        } catch (RestClientException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}
