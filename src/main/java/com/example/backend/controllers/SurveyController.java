package com.example.backend.controllers;

import com.example.backend.models.Survey;
import com.example.backend.services.SurveyService;
import io.javalin.http.Context;

import java.util.List;

public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public void submitSurvey(Context ctx) {
        String userId = ctx.formParam("userId");
        String question = ctx.formParam("question");
        String answer = ctx.formParam("answer");

        Survey survey = new Survey(userId, question, answer);
        surveyService.saveSurvey(survey);

        ctx.json("Survey submitted successfully");
    }

    public void getAllSurveys(Context ctx) {
        List<Survey> surveys = surveyService.getAllSurveys();
        ctx.json(surveys);
    }

    public void getSurveysByUserId(Context ctx) {
        String userId = ctx.pathParam("userId");
        List<Survey> userSurveys = surveyService.getSurveysByUserId(userId);
        ctx.json(userSurveys);
    }

    public void searchSurveys(Context ctx) {
        String keyword = ctx.queryParam("keyword");
        List<Survey> searchResults = surveyService.searchSurveys(keyword);
        ctx.json(searchResults);
    }
}
