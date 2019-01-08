package com.karolina.project.sonarqube.builder;

import com.karolina.project.sonarqube.bean.review.result.ResultBugs;
import com.karolina.project.sonarqube.bean.review.result.ResultValue;

public class ResultBugsBuilder {

    public static ResultBugs build() {
        return build(ResultValueBuilder.build(), ResultValueBuilder.build(), ResultValueBuilder.build(), ResultValueBuilder.build(), ResultValueBuilder.build(), ResultValueBuilder.build());
    }

    public static ResultBugs build(ResultValue bug, ResultValue blocker, ResultValue critical, ResultValue major, ResultValue minor, ResultValue finalResult) {
        return new ResultBugs(bug, blocker, critical, major, minor, finalResult);
    }

}
