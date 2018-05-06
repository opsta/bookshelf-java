/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.getstarted.auth;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@WebServlet(name = "login", value = "/login")
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

  private Logger logger = Logger.getLogger(this.getClass().getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {

    String state = new BigInteger(130, new SecureRandom()).toString(32);  // prevent request forgery
    req.getSession().setAttribute("state", state);

    if (req.getAttribute("loginDestination") != null) {
      req
          .getSession()
          .setAttribute("loginDestination", (String) req.getAttribute("loginDestination"));
      logger.log(
          Level.INFO, "logging destination " + (String) req.getAttribute("loginDestination"));
    } else {
      req.getSession().setAttribute("loginDestination", "/books");
      logger.log(Level.INFO, "logging destination /books");
    }

  }
}
// [END example]
