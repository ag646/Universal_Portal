package com.dal.universityPortal.database;

import java.sql.SQLException;
import java.util.List;

public interface SelectDao<T> {
    List<T> fetchAll() throws SQLException;
}
