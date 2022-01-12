package com.dal.universityPortal.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class DBSessionTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private ResultSetMetaData resultSetMetaData;

    @InjectMocks
    private DBSession dbSession;

    @BeforeEach
    public void setUp()  {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void close() throws SQLException {
        dbSession.close();
        Mockito.verify(connection).close();
    }

    @Test
    void commit() throws SQLException {
        dbSession.commit();
        Mockito.verify(connection).commit();
    }

    @Test
    void setAutoCommit() throws SQLException {
        dbSession.setAutoCommit(true);
        Mockito.verify(connection).setAutoCommit(true);
    }

    @Test
    void executeWithoutParams() throws SQLException {
        setupMock();
        dbSession.execute("INSERT INTO test (id, name) VALUES (1, 'Test')");
        Mockito.verify(preparedStatement).executeUpdate();
    }

    @Test
    void executeWithParams() throws SQLException {
        setupMock();
        dbSession.execute("INSERT INTO test (id, name) VALUES (?, ?)", Arrays.asList(1, "Test"));
        Mockito.verify(preparedStatement).executeUpdate();

    }

    @Test
    void fetchWithoutParams() throws SQLException {
        setupMock();
        mockValuesForFetch();
        List<Map<String, Object>> fetchedObject = dbSession.fetch("SELECT * FROM test");
        List<Map<String, Object>> expected = getExpectedValuesForFetch();
        assertEquals(expected, fetchedObject);
    }

    @Test
    void fetchWithParams() throws SQLException {
        setupMock();
        mockValuesForFetch();
        List<Map<String, Object>> fetchedObject = dbSession.fetch("SELECT * FROM test WHERE id = ?", Arrays.asList(1));
        List<Map<String, Object>> expected = getExpectedValuesForFetch();
        assertEquals(expected, fetchedObject);
    }

    private void setupMock() throws SQLException {
        Mockito.when(connection.prepareStatement(any())).thenReturn(preparedStatement);
    }

    private List<Map<String, Object>> getExpectedValuesForFetch() {
        List<Map<String, Object>> expected = new ArrayList<>();
        Map<String, Object> row = new HashMap<>();
        row.put("id", 1);
        row.put("name", "Test");
        expected.add(row);
        return expected;
    }

    private void mockValuesForFetch() throws SQLException {
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getMetaData()).thenReturn(resultSetMetaData);
        Mockito.when(resultSetMetaData.getColumnCount()).thenReturn(2);
        Mockito.when(resultSetMetaData.getColumnName(anyInt())).thenReturn("id").thenReturn("name");
        Mockito.when(resultSet.getObject(anyInt())).thenReturn(1).thenReturn("Test");
    }
}
