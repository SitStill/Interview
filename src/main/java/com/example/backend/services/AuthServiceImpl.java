package com.example.backend.services;

import com.example.backend.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String authenticate(String username, String password) {
        // 身份验证逻辑，检查用户名和密码是否匹配
        // 这里使用 UserRepository 的方法检查用户是否存在
        try {
            if (userRepository.findByUsername(username).isPresent()) {
                String storedPassword = userRepository.findByUsername(username).get().getPassword();
                if (storedPassword.equals(password)) {
                    // 如果用户存在且密码匹配，可以生成并返回一个 token
                    // 在实际应用中，可能需要更安全的身份验证方式
                    String generatedToken = generateToken(username); // 你需要实现生成 token 的逻辑
                    logger.info("Authentication successful for user: {}", username);
                    return generatedToken;
                } else {
                    logger.warn("Authentication failed. Incorrect password for user: {}", username);
                    return null;
                }
            } else {
                logger.warn("Authentication failed. User not found: {}", username);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error during authentication", e);
            return null;
        }
    }

    @Override
    public void registerUser(String username, String password) {
        // 注册用户
        userRepository.registerUser(username, password);
    }

    private String generateToken(String username) {
        // 实现生成 token 的逻辑
        // 这可能涉及到使用 JSON Web Tokens (JWT) 或其他身份验证机制
        // 请根据实际需求来实现
        return "generated_token";
    }
}
