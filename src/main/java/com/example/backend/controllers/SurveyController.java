package com.example.backend.controllers;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.models.Survey;
import com.example.backend.services.SurveyService;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;


public class SurveyController {
    private static final Logger logger = LogManager.getLogger(SurveyController.class);
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
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

    public void submitSurvey(Context ctx) {
        String userId = ctx.formParam("userId");
        String question = ctx.formParam("question");
        String answer = ctx.formParam("answer");

        // Handle optional file upload
        Optional<UploadedFile> attachmentOpt = Optional.ofNullable(ctx.uploadedFile("attachment"));

        String attachmentPath = null;
        if (attachmentOpt.isPresent()) {
            UploadedFile attachment = attachmentOpt.get();

            // Do something with the attachment
            attachmentPath = handleFileUpload(attachment);
        }

        // Create your Survey object
        Survey survey = new Survey(userId, question, answer, attachmentPath);

        // Save the survey
        surveyService.saveSurvey(survey);

        logger.info("All Surveys in the database: {}", surveyService.getAllSurveys());


        // 添加日志以检查是否成功存入数据库
        if (surveyService.getSurveyById(survey.getUserId()) != null) {
            logger.info("Survey saved successfully to the database: {}", survey);
        } else {
            logger.warn("Failed to save survey to the database: {}", survey);
        }

        ctx.json("Survey submitted successfully");
    }

    private String handleFileUpload(UploadedFile attachment) {
        if (attachment == null) {
            return null; // 如果附件为空，返回 null
        }

        String uploadDirectory = "uploads";  // 存储上传文件的目录
        try {
            Path tempFile = Files.createTempFile(Paths.get(uploadDirectory), "upload-", ".tmp");
            InputStream input = attachment.getContent();
            Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            return tempFile.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
