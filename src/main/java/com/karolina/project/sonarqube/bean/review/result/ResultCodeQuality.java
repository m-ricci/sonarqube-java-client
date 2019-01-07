package com.karolina.project.sonarqube.bean.review.result;

public class ResultCodeQuality {

    private ResultValue comments;
    private ResultValue duplications;
    private ResultValue finalResult;

    public ResultCodeQuality() {}

    public ResultCodeQuality(ResultValue comments, ResultValue duplications, ResultValue finalResult) {
        this.comments = comments;
        this.duplications = duplications;
        this.finalResult = finalResult;
    }

    public void setComments(ResultValue comments) {
        this.comments = comments;
    }

    public ResultValue getComments() {
        return comments;
    }

    public void setDuplications(ResultValue duplications) {
        this.duplications = duplications;
    }

    public ResultValue getDuplications() {
        return duplications;
    }

    public void setFinalResult(ResultValue finalResult) {
        this.finalResult = finalResult;
    }

    public ResultValue getFinalResult() {
        return finalResult;
    }

}
