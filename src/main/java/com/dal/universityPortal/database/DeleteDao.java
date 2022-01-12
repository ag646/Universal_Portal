package com.dal.universityPortal.database;

import java.sql.SQLException;

public interface DeleteDao<T> {
    void delete(T t) throws SQLException;
}
