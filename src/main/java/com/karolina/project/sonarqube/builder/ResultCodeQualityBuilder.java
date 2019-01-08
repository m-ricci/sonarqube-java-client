package com.karolina.project.sonarqube.builder;

import com.karolina.project.sonarqube.bean.review.result.ResultCodeQuality;
import com.karolina.project.sonarqube.bean.review.result.ResultValue;

public class ResultCodeQualityBuilder {

    public static ResultCodeQuality build() {
        return build(ResultValueBuilder.build(), ResultValueBuilder.build(), ResultValueBuilder.build(), ResultValueBuilder.build());
    }

    public static ResultCodeQuality build(ResultValue codeLines, ResultValue comments, ResultValue duplications, ResultValue finalResult) {
        return new ResultCodeQuality(codeLines, comments, duplications, finalResult);
    }

}
