package com.karolina.project.sonarqube.bean.review.result;

public class ResultReview {

    private ResultValue finalResult;
    private ResultCodeQuality codeQuality;
    private ResultBugs bugs;
    private ResultSecurity security;

    public ResultReview() {}

    public ResultReview(ResultCodeQuality codeQuality, ResultBugs bugs, ResultSecurity security, ResultValue finalResult) {
        this.codeQuality = codeQuality;
        this.bugs = bugs;
        this.security = security;
        this.finalResult = finalResult;
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
