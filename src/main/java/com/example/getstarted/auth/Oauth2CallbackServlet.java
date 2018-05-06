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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@WebServlet(name = "oauth2callback", value = "/oauth2callback")
@SuppressWarnings("serial")
public class Oauth2CallbackServlet extends HttpServlet {

  private static final Logger logger = Logger.getLogger(Oauth2CallbackServlet.class.getName());

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
      ServletException {

    // Ensure that this is no request forgery going on, and that the user
    // sending us this connect request is the user that was supposed to.
    if (req.getSession().getAttribute("state") == null
        || !req.getParameter("state").equals((String) req.getSession().getAttribute("state"))) {
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      logger.log(
          Level.WARNING,
          "Invalid state parameter, expected " + (String) req.getSession().getAttribute("state")
              + " got " + req.getParameter("state"));
      resp.sendRedirect("/books");
      return;
    }

    req.getSession().removeAttribute("state");     // Remove one-time use state.
  }
}
// [END example]
