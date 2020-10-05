package com.nix.jdbcdaotask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public abstract class AbstractJdbcDao {

    private static final Logger logger = LoggerFactory.getLogger(AbstractJdbcDao.class);
    private static Properties properties = loadProperties();

//    private static final String DB_URI = "jdbc:h2:tcp://localhost/D:\\Devstvo\\MyJavaProjects\\IdeaProjects\\jdbskvachproject\\db";
//    private static final String DB_DRIVER = "org.h2.Driver";
//    private static final String DB_USER = "sa";
//    private static final String DB_PASSWORD = "sa";

    private String DB_DRIVER = "";
    private String DB_URI = "";
    private String DB_USER = "";
    private String DB_PASSWORD = "";

    public AbstractJdbcDao() {
        this.DB_DRIVER = properties.getProperty("db.driver");
        this.DB_URI = properties.getProperty("db.uri");
        this.DB_USER = properties.getProperty("db.username");
        this.DB_PASSWORD = properties.getProperty("db.password");
    }

    public AbstractJdbcDao(String DB_DRIVER, String DB_URI) {
        this.DB_DRIVER = DB_DRIVER;
        this.DB_URI = DB_URI;
    }

    protected Connection createConnection() {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_URI, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            logger.error("createConnection method failure", e);
            throw new IllegalArgumentException("createConnection method failure", e);
        }
    }

    private static Properties loadProperties() {
        try (InputStream in = AbstractJdbcDao.class.getResourceAsStream("/.h2.serverForDocker.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        } catch (Exception e) {
            logger.error("properties loading failure", e);
            throw new IllegalArgumentException("properties loading failure", e);
        }
    }
}
