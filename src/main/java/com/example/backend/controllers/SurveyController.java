package com.example.backend.controllers;

import com.example.backend.models.Survey;
import com.example.backend.services.SurveyService;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        String attachmentPath = handleFileUpload(ctx);  // 处理文件上传，并获取附件路径

        Survey survey = new Survey(userId, question, answer, attachmentPath);
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

    public void getSurveyById(Context ctx) {
        String surveyId = ctx.pathParam("surveyId");
        Survey survey = surveyService.getSurveyById(surveyId);
        if (survey != null) {
            ctx.json(survey);
        } else {
            ctx.status(404).json("Survey not found");
        }
    }


    private String handleFileUpload(Context ctx) {
        String uploadDirectory = "uploads";  // 存储上传文件的目录
        try {
            List<UploadedFile> files = ctx.uploadedFiles("attachment");

            if (files.isEmpty()) {
                // 处理没有上传文件的情况
                return null;
            }

            Path tempFile = Files.createTempFile(Paths.get(uploadDirectory), "upload-", ".tmp");
            InputStream input = files.get(0).getContent();
            Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            return tempFile.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
