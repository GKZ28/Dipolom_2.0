package ru.netology.data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final String URL = System.getProperty("db.url");
    private static final String USERNAME = System.getProperty("db.username");
    private static final String PASSWORD = System.getProperty("db.password");

    private static Connection startConnection() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connect;
    }

    public static String statusOfPayment() {
        QueryRunner runner = new QueryRunner();
        String payStatus = "SELECT status FROM payment_entity";

        try (Connection connect = startConnection()) {
            var paymentStatus = runner.query(connect, payStatus, new BeanHandler<>(Debit.class));
            return paymentStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static String statusOfCredit() {
        QueryRunner runner = new QueryRunner();
        String cStatus = "SELECT status FROM credit_request_entity";

        try (Connection connect = startConnection()) {
            var creditStatus = runner.query(connect, cStatus, new BeanHandler<>(Credit.class));
            return creditStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static String statusOfAmount() {
        QueryRunner runner = new QueryRunner();
        String payAmount = "SELECT amount FROM payment_entity";

        try (Connection connect = startConnection()) {
            var paymentAmount = runner.query(connect, payAmount, new BeanHandler<>(Debit.class));
            return paymentAmount.getAmount();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
