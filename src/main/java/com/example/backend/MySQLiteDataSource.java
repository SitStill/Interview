package com.example.backend;

// MySQLiteDataSource.java

import org.sqlite.SQLiteDataSource;

public class MySQLiteDataSource {
    public static SQLiteDataSource create(String jdbcUrl) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(jdbcUrl);
        return dataSource;
    }
}