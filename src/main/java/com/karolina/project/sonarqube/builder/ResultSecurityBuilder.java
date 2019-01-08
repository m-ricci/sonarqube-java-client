package com.karolina.project.sonarqube.builder;

import com.karolina.project.sonarqube.bean.review.result.ResultSecurity;
import com.karolina.project.sonarqube.bean.review.result.ResultValue;

public class ResultSecurityBuilder {

    public static ResultSecurity build() {
        return build(ResultValueBuilder.build(), ResultValueBuilder.build());
    }

    public static ResultSecurity build(ResultValue security, ResultValue finalResult) {
        return new ResultSecurity(security, finalResult);
    }

}
