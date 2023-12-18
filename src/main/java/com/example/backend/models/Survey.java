package com.example.backend.models;

public class Survey {
    private String userId;
    private String question;
    private String answer;
    private String attachment;  // 新增字段，用于保存附件路径

    // Constructors, getters, and setters


    public Survey(String userId, String question, String answer, String attachment) {
        this.userId = userId;
        this.question = question;
        this.answer = answer;
        this.attachment = attachment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}