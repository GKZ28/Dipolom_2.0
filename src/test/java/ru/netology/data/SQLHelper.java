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
    private static Connection connect;

    private static Connection startConnection() {
        try {
            connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connect;
    }

    public String statusOfPayment() {
        var runner = new QueryRunner();
        var payStatus = "SELECT status FROM payment_entity";

        try (var connect = startConnection()) {
            var paymentStatus = runner.query(connect, payStatus, new BeanHandler<>(Debit.class));
            return paymentStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public String statusOfCredit() {
        var runner = new QueryRunner();
        var cStatus = "SELECT status FROM credit_request_entity";

        try (var connect = startConnection()) {
            var creditStatus = runner.query(connect, cStatus, new BeanHandler<>(Credit.class));
            return creditStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public String statusOfAmount() {
        var runner = new QueryRunner();
        var payAmount = "SELECT amount FROM payment_entity";

        try (var connect = startConnection()) {
            var paymentAmount = runner.query(connect, payAmount, new BeanHandler<>(Debit.class));
            return paymentAmount.getAmount();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}