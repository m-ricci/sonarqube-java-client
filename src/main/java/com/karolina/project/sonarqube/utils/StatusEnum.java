package com.karolina.project.sonarqube.utils;

public enum StatusEnum {

    OK("label-success", "status.label.ok"), WARNING("label-warning", "status.label.warning"), ERROR("label-danger", "status.label.error");

    StatusEnum(String cssClass, String label) {
        this.cssClass = cssClass;
        this.label = label;
    }

    private String cssClass;
    private String label;

    public String getCssClass() {
        return cssClass;
    }

    public String getLabel() {
        return label;
    }

}
