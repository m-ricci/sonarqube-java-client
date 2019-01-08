package com.karolina.project.sonarqube.controller;

import com.karolina.project.sonarqube.bean.review.result.ResultReview;
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
        ResultReview review = service.getReview(projectKey);
        if(review == null) {
            return new ModelAndView("index");
        } else {
            ModelAndView mav = new ModelAndView("review");
            mav.addObject("result", review);
            return mav;
        }
    }

}
