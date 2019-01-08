package com.karolina.project.sonarqube.bean.sonarqube.response;

public class MeasureElement {

    private String metric;
    private Float value;
    private String component;
    private Boolean bestValue;

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getMetric() {
        return metric;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getComponent() {
        return component;
    }

    public void setBestValue(Boolean bestValue) {
        this.bestValue = bestValue;
    }

    public Boolean getBestValue() {
        return bestValue;
    }

}
