package com.karolina.project.sonarqube.builder;

import com.karolina.project.sonarqube.bean.review.result.*;

public class ResultReviewBuilder {

    public static ResultReview build() {
        return build(ResultCodeQualityBuilder.build(), ResultBugsBuilder.build(), ResultSecurityBuilder.build(), ResultValueBuilder.build());
    }

    public static ResultReview build(ResultCodeQuality resultCodeQuality, ResultBugs resultBugs, ResultSecurity resultSecurity, ResultValue finalResult) {
        return new ResultReview(resultCodeQuality, resultBugs, resultSecurity, finalResult);
    }

}
