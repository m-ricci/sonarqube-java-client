package com.karolina.project.sonarqube.controller;

import com.karolina.project.sonarqube.bean.review.result.*;
import com.karolina.project.sonarqube.utils.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView index() {
        logger.info("IndexController::index");
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/review")
    public ModelAndView review() {
        logger.info("IndexController::index");
        ModelAndView mav = new ModelAndView("review");
        ResultReview result = new ResultReview(
                new ResultValue(0.0f, StatusEnum.WARNING),
                new ResultCodeQuality(
                        new ResultValue(1.0f, StatusEnum.OK),
                        new ResultValue(1.1f, StatusEnum.WARNING),
                        new ResultValue(1.2f, StatusEnum.ERROR)
                ),
                new ResultBugs(
                        new ResultValue(2.0f, StatusEnum.OK),
                        new ResultValue(2.1f, StatusEnum.WARNING),
                        new ResultValue(2.2f, StatusEnum.ERROR),
                        new ResultValue(2.3f, StatusEnum.OK),
                        new ResultValue(2.4f, StatusEnum.WARNING),
                        new ResultValue(2.5f, StatusEnum.ERROR)
                ),
                new ResultSecurity(
                        new ResultValue(3.0f, StatusEnum.OK),
                        new ResultValue(3.1f, StatusEnum.WARNING)
                )
        );
        mav.addObject("result", result);
        return mav;
    }

}
