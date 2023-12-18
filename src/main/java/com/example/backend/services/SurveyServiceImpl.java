package com.example.backend.services;

import com.example.backend.models.Survey;
import com.example.backend.repositories.SurveyRepository;

import java.util.List;

public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;

    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public void saveSurvey(Survey survey) {
        surveyRepository.save(survey);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public List<Survey> getSurveysByUserId(String userId) {
        return surveyRepository.findByUserId(userId);
    }

    @Override
    public List<Survey> searchSurveys(String keyword) {
        return surveyRepository.search(keyword);
    }

    @Override
    public Survey getSurveyById(String surveyId) {
        return surveyRepository.findById(surveyId);
    }
}
