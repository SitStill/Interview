package com.example.backend.repositories;

import com.example.backend.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    private final SQLiteDataSource dataSource;

    public UserRepositoryImpl(SQLiteDataSource dataSource) {
        this.dataSource = dataSource;
        createTableIfNotExists();
        createDefaultUserIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection connection = dataSource.getConnection()) {
            logger.info("Connected to the database");

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL," +
                    "password TEXT NOT NULL" +
                    ")";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
                logger.info("Table 'users' created successfully");
            }

            createDefaultUserIfNotExists(); // 确保在表创建后调用创建默认用户的方法

            logger.info("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error creating table: " + e.getMessage());
        }
    }


    private void createDefaultUserIfNotExists() {
        // 检查数据库中是否已经有默认用户，如果没有则创建
        String defaultUsername = "admin";
        Optional<User> existingUser = findByUsername(defaultUsername);

        if (!existingUser.isPresent()) {
            logger.info("Creating default user...");

            // 你可以使用自定义的 registerUser 方法或 save 方法创建用户
            registerUser(defaultUsername, "admin"); // 用于你现有的 registerUser 方法
            // 或者
            // save(new User(defaultUsername, "admin")); // 用于你现有的 save 方法

            logger.info("Default user created successfully");
        } else {
            logger.warn("Default user already exists");
        }
    }



    @Override
    public Optional<User> findByUsername(String username) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new User(
                                resultSet.getString("username"),
                                resultSet.getString("password")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerUser(String username, String password) {
        // 创建一个新用户对象
        User newUser = new User(username, password);
        // 将新用户保存到数据库
        save(newUser);
    }
}
