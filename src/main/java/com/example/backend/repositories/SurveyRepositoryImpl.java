package com.example.backend.repositories;


import com.example.backend.models.Survey;

import java.util.ArrayList;
import java.util.List;

public class SurveyRepositoryImpl implements SurveyRepository {
    private List<Survey> surveyDatabase = new ArrayList<>();

    @Override
    public void save(Survey survey) {
        surveyDatabase.add(survey);
    }

    @Override
    public List<Survey> findAll() {
        return new ArrayList<>(surveyDatabase);
    }

    @Override
    public List<Survey> findByUserId(String userId) {
        List<Survey> userSurveys = new ArrayList<>();
        for (Survey survey : surveyDatabase) {
            if (survey.getUserId().equals(userId)) {
                userSurveys.add(survey);
            }
        }
        return userSurveys;
    }

    @Override
    public List<Survey> search(String keyword) {
        List<Survey> searchResults = new ArrayList<>();
        for (Survey survey : surveyDatabase) {
            if (survey.getQuestion().contains(keyword) || survey.getAnswer().contains(keyword)) {
                searchResults.add(survey);
            }
        }
        return searchResults;
    }

    @Override
    public Survey findById(String surveyId) {
        for (Survey survey : surveyDatabase) {
            if (survey.getUserId().equals(surveyId)) {
                return survey;
            }
        }
        return null;
    }
}
