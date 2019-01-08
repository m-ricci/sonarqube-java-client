package com.karolina.project.sonarqube.controller;

import com.karolina.project.sonarqube.bean.review.result.ResultReview;
import com.karolina.project.sonarqube.builder.ResultReviewBuilder;
import com.karolina.project.sonarqube.exception.ProjectNotFoundException;
import com.karolina.project.sonarqube.exception.SonarqubeNotReachableException;
import com.karolina.project.sonarqube.service.SonarqubeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    private SonarqubeService service;

    public ReviewController(SonarqubeService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/review")
    public ModelAndView getReview(@RequestParam String projectKey) {
        logger.info("ReviewController::getReview::{}", projectKey);
        ResultReview review;
        String errorCode = "";
        try {
            review = service.getReview(projectKey);
        } catch(SonarqubeNotReachableException e) {
            review = null;
            errorCode = "error.message.sonarqube.connection";
        } catch(ProjectNotFoundException e) {
            review = null;
            errorCode = "error.message.sonarqube.project";
        }

        if(review == null) {
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("error", errorCode);
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("review");
            mav.addObject("projectKeyName", projectKey);
            mav.addObject("result", review);
            return mav;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/review")
    public ModelAndView getReview() {
        logger.info("ReviewController::getReview");
        ResultReview review = ResultReviewBuilder.build();
        ModelAndView mav = new ModelAndView("review");
        mav.addObject("projectKeyName", "Test");
        mav.addObject("result", review);
        return mav;
    }

}
