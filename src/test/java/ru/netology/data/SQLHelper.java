package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final String URL = System.getProperty("db.url");
    private static final String USERNAME = System.getProperty("db.username");
    private static final String PASSWORD = System.getProperty("db.password");

    private static Connection startConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @SneakyThrows(SQLException.class)
    public static Debit getDebitInfo() {
        QueryRunner runner = new QueryRunner();
        String query = "SELECT * FROM payment_entity";
        try (Connection connect = startConnection()) {
            return runner.query(connect, query, new BeanHandler<>(Debit.class));
        }
    }

    @SneakyThrows(SQLException.class)
    public static Credit getCreditInfo() {
        QueryRunner runner = new QueryRunner();
        String query = "SELECT * FROM credit_request_entity";
        try (Connection connect = startConnection()) {
            return runner.query(connect, query, new BeanHandler<>(Credit.class));
        }
    }
}