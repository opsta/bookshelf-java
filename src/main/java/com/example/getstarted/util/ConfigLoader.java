/* Copyright 2018 Opsta (Thailand) Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.getstarted.util;

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
