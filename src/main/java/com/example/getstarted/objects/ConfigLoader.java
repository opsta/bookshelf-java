package com.example.getstarted.objects;

import java.util.Properties;
import org.apache.commons.lang3.StringUtils;

public class ConfigLoader {

  private Properties props;
  private static ConfigLoader instance = null;

  private ConfigLoader() {

    Properties props = new Properties();

    props.setProperty("storageType",
      StringUtils.defaultIfEmpty(System.getenv("STORAGE_TYPE"), "sql"));
    props.setProperty("sqlInstanceName",
      StringUtils.defaultIfEmpty(System.getenv("SQL_INSTANCE_NAME"), "localhost"));
    props.setProperty("sqlDbName",
      StringUtils.defaultIfEmpty(System.getenv("SQL_DB_NAME"), "bookshelf"));
    props.setProperty("sqlUsername",
      StringUtils.defaultIfEmpty(System.getenv("SQL_USERNAME"), "root"));
    props.setProperty("sqlPassword",
      StringUtils.defaultIfEmpty(System.getenv("SQL_PASSWORD"), "rootpass"));

    String sqlConnect = "jdbc:mysql://" + props.getProperty("sqlInstanceName") +
      "/" + props.getProperty("sqlDbName") + "?user=" + 
      props.getProperty("sqlUsername") + "&password=" + 
      props.getProperty("sqlPassword") + "&useSSL=false";
    props.setProperty("sqlConnect", sqlConnect);

    this.props = props;
  }

  public Properties getConfig() {
    return props;
  }

  public static ConfigLoader getInstance() {
    if (instance == null) {
      instance = new ConfigLoader();
    }
    return instance;
  }

}
