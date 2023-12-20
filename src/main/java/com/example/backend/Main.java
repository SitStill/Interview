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
import org.sqlite.SQLiteDataSource;


public class Main {
    public static void main(String[] args) {
        // Set up SQLite data source using the create method
        SQLiteDataSource dataSource = MySQLiteDataSource.create("jdbc:sqlite:survey.db");

        // Set up repositories
        UserRepository userRepository = new UserRepositoryImpl(dataSource);
        SurveyRepository surveyRepository = new SurveyRepositoryImpl(dataSource);

        // Set up services
        AuthService authService = new AuthServiceImpl(userRepository);
        SurveyService surveyService = new SurveyServiceImpl(surveyRepository);

        // Set up controllers
        AuthenticationController authController = new AuthenticationController(authService);
        SurveyController surveyController = new SurveyController(surveyService);

        // Set up Javalin app
        Javalin app = Javalin.create();

        presetUsers(authService);

        // Define routes
        app.post("/login", authController::login);
        app.post("/submit-survey", surveyController::submitSurvey);
        app.get("/surveys", surveyController::getAllSurveys);
        app.get("/surveys/:userId", surveyController::getSurveysByUserId);
        app.get("/search", surveyController::searchSurveys);
        app.get("/surveys/:surveyId", surveyController::getSurveyById);  // 新增路由，用于根据ID查询问卷
        app.get("/", ctx -> {
            ctx.result("Hello, this is the root endpoint!");
        });

        // Add a new endpoint in Main.java
        app.get("/initialize", ctx -> {
            // Register some test users
            userRepository.registerUser("123", "123");
            userRepository.registerUser("adminn", "admin");
            userRepository.registerUser("testuser3", "password3");
            ctx.result("Initialization complete");
        });
        // Start the application
        app.start(8080);
    }

    private static void presetUsers(AuthService authService) {
        // 预设用户
        authService.registerUser("123", "123");
        authService.registerUser("admin", "admin");
        // 添加更多用户...
    }

}
