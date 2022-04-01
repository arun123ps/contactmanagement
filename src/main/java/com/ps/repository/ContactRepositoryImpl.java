package com.ps.repository;

import com.ps.domain.Contact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.naming.NamingException;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactRepositoryImpl implements ContactRepository {
    private static final Logger logger = LogManager.getLogger(ContactRepositoryImpl.class);

    @Override
    public List<Contact> findAll() throws SQLException, NamingException {
        logger.debug("about to get connection from DBConnectionManager");
        Connection connection = DBConnnectionManager.getconnection();
        String sql = "select * from contactform";
        List<Contact> contactaa = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
            logger.debug("sql executed successfully");
            ResultSet rs = ps.getResultSet();
            Contact contact;
            Integer id,userid;
            String firstname, lastname, mobilenumber, emailid, address, photo;
            String dateofbirth;
            while (rs.next()) {
                logger.debug("insert the db");
                id = rs.getInt("id");
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                mobilenumber = rs.getString("mobilenumber");
                emailid = rs.getString("emailid");
                dateofbirth = String.valueOf(rs.getDate("dateofbirth"));
                address = rs.getString("address");
                photo = rs.getString("photo");
                userid = rs.getInt("userid");


                contact = new Contact(id, firstname, lastname, mobilenumber, emailid,dateofbirth, address, photo, userid);
                contactaa.add(contact);
            }
            System.out.println(contactaa);

        } catch (SQLException e) {
            logger.debug(e);
            e.printStackTrace();
        }
        return contactaa;
    }


    @Override
    public Contact create(Contact contact) throws SQLException, NamingException {

        try {
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact?user=root&password=1234");
            Connection connection = DBConnnectionManager.getconnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO contactform values(?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, contact.getId());
            statement.setString(2, contact.getFirstname());
            statement.setString(3, contact.getLastname());
            statement.setString(4, contact.getMobilenumber());
            statement.setString(5, contact.getEmailid());
            logger.info("dateofbith is get");
            statement.setDate(6, Date.valueOf(contact.getDateofbirth()));
            statement.setString(7, contact.getAddress());
            statement.setString(8, contact.getPhoto());
            statement.setInt(9,contact.getUserid());

            logger.info("about to execute SQL to insert contacts");
            int count = statement.executeUpdate();
            if (count > 0) return contact;
        } catch (SQLException e) {
            logger.catching(e);
        }
        return contact;
    }

    @Override
    public void insert(Contact contact) {

    }





    @Override
    public void edit(int id, String firstname) {

        try {
            Connection connection = DBConnnectionManager.getconnection();
            PreparedStatement statement = connection.prepareStatement("update contactform set firstname=? where id=? ");

            statement.setString(1, firstname);
            statement.setInt(2,id);

            logger.info("about to execute SQL to edit contacts");

            int recordsUpdated = statement.executeUpdate();
            logger.info("recordsUpdated: "+ recordsUpdated);
        }catch (SQLException | NamingException e) {
            logger.catching(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            Connection connection = DBConnnectionManager.getconnection();
            PreparedStatement statement = connection.prepareStatement("delete from contactform where id=? ");

            statement.setInt(1,id);

            logger.info("about to delete SQL to edit users");

            int recordsUpdated = statement.executeUpdate();
            logger.info("recordsUpdated: "+ recordsUpdated);
        }catch (SQLException | NamingException e) {
            logger.catching(e);
        }
    }


}


