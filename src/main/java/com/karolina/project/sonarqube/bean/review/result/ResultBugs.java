package com.karolina.project.sonarqube.bean.review.result;

public class ResultBugs {

    private ResultValue bug;
    private ResultValue blocker;
    private ResultValue critical;
    private ResultValue major;
    private ResultValue minor;
    private ResultValue finalResult;

    public ResultBugs(ResultValue bug, ResultValue blocker, ResultValue critical, ResultValue major, ResultValue minor, ResultValue finalResult) {
        this.bug = bug;
        this.blocker = blocker;
        this.critical = critical;
        this.major = major;
        this.minor = minor;
        this.finalResult = finalResult;
    }

    public void setBug(ResultValue bug) {
        this.bug = bug;
    }

    public ResultValue getBug() {
        return bug;
    }

    public void setBlocker(ResultValue blocker) {
        this.blocker = blocker;
    }

    public ResultValue getBlocker() {
        return blocker;
    }

    public void setCritical(ResultValue critical) {
        this.critical = critical;
    }

    public ResultValue getCritical() {
        return critical;
    }

    public void setMajor(ResultValue major) {
        this.major = major;
    }

    public ResultValue getMajor() {
        return major;
    }

    public void setMinor(ResultValue minor) {
        this.minor = minor;
    }

    public ResultValue getMinor() {
        return minor;
    }

    public void setFinalResult(ResultValue finalResult) {
        this.finalResult = finalResult;
    }

    public ResultValue getFinalResult() {
        return finalResult;
    }

}
