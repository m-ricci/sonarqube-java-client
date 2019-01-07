package com.karolina.project.sonarqube.bean.review.result;

import com.karolina.project.sonarqube.utils.StatusEnum;

public class ResultValue {

    private float value;
    private StatusEnum status;

    public ResultValue() {}

    public ResultValue(float value, StatusEnum status) {
        this.value = value;
        this.status = status;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public StatusEnum getStatus() {
        return status;
    }

}
