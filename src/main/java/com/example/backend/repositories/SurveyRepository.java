package com.example.backend.repositories;

import com.example.backend.models.Survey;

import java.util.List;

public interface SurveyRepository {
    void save(Survey survey);
    List<Survey> findAll();
    List<Survey> findByUserId(String userId);
    List<Survey> search(String keyword);
    Survey findById(String surveyId);  // 新增方法，用于根据ID查询问卷
}
