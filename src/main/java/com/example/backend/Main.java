package com.example.backend;

import com.example.backend.controllers.AuthenticationController;
import com.example.backend.controllers.SurveyController;
import com.example.backend.repositories.UserRepository;
import com.example.backend.repositories.UserRepositoryImpl;
import com.example.backend.repositories.SurveyRepository;
import com.example.backend.repositories.SurveyRepositoryImpl;
import com.example.backend.services.AuthService;
import com.example.backend.services.AuthServiceImpl;
import com.example.backend.services.SurveyService;
import com.example.backend.services.SurveyServiceImpl;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        // Set up repositories
        UserRepository userRepository = new UserRepositoryImpl();
        SurveyRepository surveyRepository = new SurveyRepositoryImpl();

        // Set up services
        AuthService authService = new AuthServiceImpl(userRepository);
        SurveyService surveyService = new SurveyServiceImpl(surveyRepository);

        // Set up controllers
        AuthenticationController authController = new AuthenticationController(authService);
        SurveyController surveyController = new SurveyController(surveyService);

        // Set up Javalin app
        Javalin app = Javalin.create();

        // Define routes
        app.post("/login", authController::login);
        app.post("/submit-survey", surveyController::submitSurvey);
        app.get("/surveys", surveyController::getAllSurveys);
        app.get("/surveys/:userId", surveyController::getSurveysByUserId);
        app.get("/search", surveyController::searchSurveys);
        app.get("/surveys/:surveyId", surveyController::getSurveyById);  // 新增路由，用于根据ID查询问卷

        // Start the application
        app.start(8080);
    }
}
