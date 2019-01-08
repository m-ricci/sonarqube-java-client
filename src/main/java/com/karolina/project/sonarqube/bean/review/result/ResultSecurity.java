package com.karolina.project.sonarqube.bean.review.result;

public class ResultSecurity {

    private ResultValue security;
    private ResultValue finalResult;

    public ResultSecurity(ResultValue security, ResultValue finalResult) {
        this.security = security;
        this.finalResult = finalResult;
    }

    public void setSecurity(ResultValue security) {
        this.security = security;
    }

    public ResultValue getSecurity() {
        return security;
    }

    public void setFinalResult(ResultValue finalResult) {
        this.finalResult = finalResult;
    }

    public ResultValue getFinalResult() {
        return finalResult;
    }

}
