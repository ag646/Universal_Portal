package com.dal.universityPortal.database;

import com.dal.universityPortal.config.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBSession implements AutoCloseable {

    private Connection connection;

    private Configuration configuration;

    public DBSession() throws SQLException {
        configuration = new Configuration();
        connection = DriverManager.getConnection(configuration.getDBConnection());
        connection.setAutoCommit(true);
    }

    public void setAutoCommit(Boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    public void commit() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        }
    }

    private PreparedStatement getPreparedStatement(String query, List<Object> params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < params.size(); i++) {
            preparedStatement.setObject(i+1, params.get(i));
        }
        return preparedStatement;
    }

    private List<Map<String, Object>> getFieldsMappedRows(ResultSet result) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        List<Map<String, Object>> rows = new ArrayList<>();
        while (result.next()) {
            Map<String, Object> resultMap = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                resultMap.put(metaData.getColumnName(i), result.getObject(i));
            }
            rows.add(resultMap);
        }
        return rows;
    }

    public void execute(String query, List<Object> params) throws SQLException {
        try (PreparedStatement statement = getPreparedStatement(query, params)) {
            statement.executeUpdate();
        }
    }

    public void execute(String query) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }

    public List<Map<String, Object>> fetch(String query, List<Object> params) throws SQLException {
        try (PreparedStatement statement = getPreparedStatement(query, params)) {
            return getFieldsMappedRows(statement.executeQuery());
        }
    }

    public  List<Map<String, Object>> fetch(String query) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            return getFieldsMappedRows(statement.executeQuery());
        }
    }
}
