package com.karolina.project.sonarqube.bean.sonarqube.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.karolina.project.sonarqube.utils.SeverityEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueElement {

    private SeverityEnum severity;
    private String status;

    public void setSeverity(SeverityEnum severity) {
        this.severity = severity;
    }

    public SeverityEnum getSeverity() {
        return severity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
