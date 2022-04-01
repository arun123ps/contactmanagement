package com.ps.controller;

import com.google.gson.Gson;
import com.ps.domain.User;
import com.ps.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/user"})
public class UserController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        logger.debug("got request from client");
        List<User> users = null;
        try {
            users = new UserService().getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        String contactsJson = new Gson().toJson(users);
        res.setContentType("application/json");
        res.getWriter().write(contactsJson);
        res.getWriter().flush();

    }


    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        logger.info("inside doPost()");
        try (InputStream is = req.getInputStream(); JsonReader jsonReader = Json.createReader(is);) {
            JsonObject jsonObject = jsonReader.readObject();

            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String password = jsonObject.getString("password");

            logger.info("about to call contactcontro to create contact..");
            User user = new User(id, name, password);
            UserService userService = new UserService();
            userService.createUsers(user);
            res.setStatus(200);
        } catch (Exception e) {
            logger.catching(e);
            logger.debug(e);

            logger.debug("error creating user.. returning 500 to client..");
            res.setStatus(500);
        }

    }



    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("inside doPut()");
        try (InputStream inputStream = req.getInputStream();
             JsonReader jsonReader = Json.createReader(inputStream)) {
            JsonObject jsonObject = jsonReader.readObject();

            int id=jsonObject.getInt("id");
            String name=jsonObject.getString("name");
            logger.info("about to call user to create user..");

            UserService userService = new UserService();
            userService.updateStatus(id,name);

        } catch (Exception e) {
            logger.catching(e);
            logger.debug(e);
            logger.debug("error creating user.. returning 500 to client..");
            e.printStackTrace();
        }
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("inside doDelete()");
        try (InputStream inputStream = req.getInputStream();
             JsonReader jsonReader = Json.createReader(inputStream)) {
            JsonObject jsonObject = jsonReader.readObject();

            int id=jsonObject.getInt("id");

            logger.info("about to delete user ..");

            UserService userService = new UserService();
           userService.inprogress(id);

        } catch (Exception e) {
            logger.catching(e);
            logger.debug(e);
            logger.debug("error creating user.. returning 500 to client..");
            e.printStackTrace();
        }
    }


}
