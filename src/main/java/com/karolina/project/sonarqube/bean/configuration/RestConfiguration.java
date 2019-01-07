package com.karolina.project.sonarqube.bean.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestConfiguration {

    @Value("${configuration.sonarqube.url}")
    private String sonarqubeUrl;

    @Value("${configuration.sonarqube.login}")
    private String sonarqubeLogin;

    @Value("${configuration.sonarqube.password}")
    private String sonarqubePassword;

    public void setSonarqubeUrl(String sonarqubeUrl) {
        this.sonarqubeUrl = sonarqubeUrl;
    }

    public String getSonarqubeUrl() {
        return sonarqubeUrl;
    }

    public void setSonarqubeLogin(String sonarqubeLogin) {
        this.sonarqubeLogin = sonarqubeLogin;
    }

    public String getSonarqubeLogin() {
        return sonarqubeLogin;
    }

    public void setSonarqubePassword(String sonarqubePassword) {
        this.sonarqubePassword = sonarqubePassword;
    }

    public String getSonarqubePassword() {
        return sonarqubePassword;
    }

}
