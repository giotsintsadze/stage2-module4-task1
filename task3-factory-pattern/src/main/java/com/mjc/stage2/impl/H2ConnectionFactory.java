package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
  private Properties properties;

  public H2ConnectionFactory() {
    properties = loadProperties();
  }

  private Properties loadProperties() {
    Properties props = new Properties();
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("h2database.properties")) {
      props.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return props;
  }

  @Override
  public Connection createConnection() {
    Connection connection = null;
    try {
      Class.forName(properties.getProperty("db.driver"));
      connection = DriverManager.getConnection(
              properties.getProperty("db.url"),
              properties.getProperty("db.username"),
              properties.getProperty("db.password"));
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }
}
