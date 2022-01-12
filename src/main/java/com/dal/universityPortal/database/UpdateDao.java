package com.dal.universityPortal.database;

import java.sql.SQLException;

public interface UpdateDao<T> {
    void update(T t) throws SQLException;
}
