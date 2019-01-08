package com.karolina.project.sonarqube.bean.sonarqube.response;

import java.util.List;

public class ResponseMeasures {

    private List<MeasureElement> measures;

    public void setMeasures(List<MeasureElement> measures) {
        this.measures = measures;
    }

    public List<MeasureElement> getMeasures() {
        return measures;
    }

}
