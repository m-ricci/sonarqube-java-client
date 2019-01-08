package com.karolina.project.sonarqube.bean.sonarqube.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseIssues {

    private List<IssueElement> issues;

    public void setIssues(List<IssueElement> issues) {
        this.issues = issues;
    }

    public List<IssueElement> getIssues() {
        return issues;
    }

}
