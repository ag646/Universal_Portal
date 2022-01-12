package com.dal.universityPortal.database;

import java.sql.SQLException;

public interface InsertDao<T> {
    void insert(T t) throws SQLException;
}
