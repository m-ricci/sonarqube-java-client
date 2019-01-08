package com.karolina.project.sonarqube.builder;

import com.karolina.project.sonarqube.bean.review.result.ResultValue;
import com.karolina.project.sonarqube.utils.StatusEnum;

public class ResultValueBuilder {

    public static ResultValue build() {
        return build(0, StatusEnum.OK);
    }

    public static ResultValue build(float value, StatusEnum status) {
        return new ResultValue(value, status);
    }

}
