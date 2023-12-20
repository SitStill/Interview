package com.example.backend.repositories;


import com.example.backend.models.Survey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SurveyRepositoryImpl implements SurveyRepository {
    private static final Logger logger = LogManager.getLogger(SurveyRepositoryImpl.class);
    private final SQLiteDataSource dataSource;

    public SurveyRepositoryImpl(SQLiteDataSource dataSource) {
        this.dataSource = dataSource;
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            // 创建 survey 表
            String createTableQuery = "CREATE TABLE IF NOT EXISTS survey (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_id VARCHAR(255)," +
                    "question TEXT," +
                    "answer TEXT," +
                    "attachment TEXT" +
                    ")";
            statement.execute(createTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Survey survey) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO survey (user_id, question, answer, attachment) VALUES (?, ?, ?, ?)")) {

            statement.setString(1, survey.getUserId());
            statement.setString(2, survey.getQuestion());
            statement.setString(3, survey.getAnswer());
            statement.setString(4, survey.getAttachment());

            // 添加日志以输出 SQL 语句和参数
            logger.info("Executing SQL: {}", statement.toString());

            statement.executeUpdate();

            logger.info("Survey saved successfully to the database: {}", survey);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error saving survey to the database: {}", e.getMessage());
        }
    }

    @Override
    public List<Survey> findAll() {
        List<Survey> surveys = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM survey")) {

            while (resultSet.next()) {
                Survey survey = mapResultSetToSurvey(resultSet);
                surveys.add(survey);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return surveys;
    }

    @Override
    public List<Survey> findByUserId(String userId) {
        List<Survey> userSurveys = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM survey WHERE user_id = ?")) {

            statement.setString(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Survey survey = mapResultSetToSurvey(resultSet);
                    userSurveys.add(survey);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userSurveys;
    }

    @Override
    public List<Survey> search(String keyword) {
        List<Survey> searchResults = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM survey WHERE question LIKE ? OR answer LIKE ?")) {

            String searchKeyword = "%" + keyword + "%";
            statement.setString(1, searchKeyword);
            statement.setString(2, searchKeyword);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Survey survey = mapResultSetToSurvey(resultSet);
                    searchResults.add(survey);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
    }

    @Override
    public Survey findById(String surveyId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM survey WHERE user_id = ?")) {

            statement.setString(1, surveyId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToSurvey(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // 如果找不到对应的 survey
    }

    private Survey mapResultSetToSurvey(ResultSet resultSet) throws SQLException {
        return new Survey(
                resultSet.getString("user_id"),
                resultSet.getString("question"),
                resultSet.getString("answer"),
                resultSet.getString("attachment")
        );
    }
}
