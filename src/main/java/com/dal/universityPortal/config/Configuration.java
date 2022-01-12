package com.dal.universityPortal.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import static com.dal.universityPortal.constant.CommonConstant.DB_CONNECTION_FORMAT;
import static com.dal.universityPortal.constant.CommonConstant.PROPERTIES_FILE;

public class Configuration {
    public String getDBConnection() throws SQLException {
        try{
            Properties properties = new Properties();
            InputStream resourceAsStream = new FileInputStream(PROPERTIES_FILE);
            properties.load(resourceAsStream);
            return String.format(DB_CONNECTION_FORMAT, properties.get("db.host"), properties.get("db.port"),
                    properties.get("db.schema"), properties.get("db.user"), properties.get("db.password"));
        } catch (IOException exception) {
            throw new SQLException();
        }
    }
}
