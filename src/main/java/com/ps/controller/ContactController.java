package com.ps.controller;

import com.google.gson.Gson;
import com.ps.domain.Contact;
import com.ps.service.ContactService;
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

@WebServlet(urlPatterns = {"/contact"})
public class ContactController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ContactController.class);
@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
    logger.debug("got request from client");
    List<Contact> contacts = null;
    try {
        contacts = new ContactService().getContacts();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (NamingException e) {
        e.printStackTrace();
    }
    String contactsJson = new Gson().toJson(contacts);
    res.setContentType("application/json");
    res.getWriter().write(contactsJson);
    res.getWriter().flush();

}

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        logger.info("inside doPost()");
        try (InputStream is = req.getInputStream(); JsonReader jsonReader = Json.createReader(is);) {
            JsonObject jsonObject = jsonReader.readObject();

            int id = jsonObject.getInt("id");
            String firstname = jsonObject.getString("firstname");
            String lastname = jsonObject.getString("lastname");
            String mobilenumber = jsonObject.getString("mobilenumber");
            String emailid = jsonObject.getString("emailid");
            String dateofbirth = jsonObject.getString("dateofbirth");
            String address = jsonObject.getString("address");
            String photo = jsonObject.getString("photo");
            int userid = jsonObject.getInt("userid");

            logger.info("about to call contactcontro to create contact..");
            Contact contact = new Contact(id, firstname, lastname, mobilenumber,emailid,dateofbirth,address,photo,userid);
            logger.info("dateofbith is "+ dateofbirth);
            ContactService contactservices = new ContactService();
            contactservices.createContact(contact);
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
            String firstname=jsonObject.getString("firstname");
            logger.info("about to call user to create user..");

            ContactService contactService = new ContactService();
            contactService.updateStatus(id,firstname);

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

            int id = jsonObject.getInt("id");

            logger.info("about to delete user ..");

            ContactService contactService = new ContactService();
            contactService.detelecontact(id);

        } catch (Exception e) {
            logger.catching(e);
            logger.debug(e);
            logger.debug("error creating user.. returning 500 to client..");
            e.printStackTrace();
        }

    }
}
