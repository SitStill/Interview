package com.example.backend.services;

import com.example.backend.models.Survey;

import java.util.List;

public interface SurveyService {
    void saveSurvey(Survey survey);
    List<Survey> getAllSurveys();
    List<Survey> getSurveysByUserId(String userId);
    List<Survey> searchSurveys(String keyword);
    Survey getSurveyById(String surveyId); // 新增方法，用于根据ID查询问卷
}
