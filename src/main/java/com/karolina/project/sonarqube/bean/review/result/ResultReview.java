package com.karolina.project.sonarqube.bean.review.result;

import com.karolina.project.sonarqube.utils.StatusEnum;

public class ResultReview {

    private ResultValue finalResult;
    private ResultCodeQuality codeQuality;
    private ResultBugs bugs;
    private ResultSecurity security;

    public ResultReview() {}

    public ResultReview(ResultValue finalResult, ResultCodeQuality codeQuality, ResultBugs bugs, ResultSecurity security) {
        this.finalResult = finalResult;
        this.codeQuality = codeQuality;
        this.bugs = bugs;
        this.security = security;
    }

    public void setFinalResult(ResultValue finalResult) {
        this.finalResult = finalResult;
    }

    public ResultValue getFinalResult() {
        return finalResult;
    }

    public void setCodeQuality(ResultCodeQuality codeQuality) {
        this.codeQuality = codeQuality;
    }

    public ResultCodeQuality getCodeQuality() {
        return codeQuality;
    }

    public void setBugs(ResultBugs bugs) {
        this.bugs = bugs;
    }

    public ResultBugs getBugs() {
        return bugs;
    }

    public void setSecurity(ResultSecurity security) {
        this.security = security;
    }

    public ResultSecurity getSecurity() {
        return security;
    }

}
