package com.ps.repository;

import com.ps.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    public List<User> find() throws SQLException, NamingException {
        logger.debug("about to get connection from DBConnectionManager");
        Connection connection = DBConnnectionManager.getconnection();
        String sql = "select * from userform";
        List<User> userus = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
            logger.debug("sql executed successfully");
            ResultSet rs = ps.getResultSet();
            User user;
            Integer id;
            String name;
            String password;
            while (rs.next()) {
                logger.debug("insert the db");
                id = rs.getInt("id");
                name = rs.getString("name");
                password = rs.getString("password");


                user = new User(id, name, password);
                userus.add(user);
            }
            System.out.println(userus);

        } catch (SQLException e) {
            logger.debug(e);
            e.printStackTrace();
        }
        return userus;
    }


    public User create(User user) throws SQLException, NamingException {

        try {
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?user=root&password=1234");
            Connection connection = DBConnnectionManager.getconnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO userform values(?,?,?)");
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPassword());

            logger.info("about to execute SQL to insert contacts");
            int count = statement.executeUpdate();
            if (count > 0) return user;
        } catch (SQLException e) {
            logger.catching(e);
        }
        return user;
    }

    public void insert(User user) {

    }

    @Override
    public void edit(int id,String name) {

        try {
            Connection connection = DBConnnectionManager.getconnection();
            PreparedStatement statement = connection.prepareStatement("update userform set name=? where id=? ");

            statement.setString(1, name);
            statement.setInt(2,id);

            logger.info("about to execute SQL to edit users");

            int recordsUpdated = statement.executeUpdate();
            logger.info("recordsUpdated: "+ recordsUpdated);
        }catch (SQLException | NamingException e) {
            logger.catching(e);
        }
    }

    @Override
    public void inprogress(int id) {

        try {
            Connection connection = DBConnnectionManager.getconnection();
            PreparedStatement statement = connection.prepareStatement("delete from userform where id=? ");

            statement.setInt(1,id);

            logger.info("about to delete SQL to edit users");

            int recordsUpdated = statement.executeUpdate();
            logger.info("recordsUpdated: "+ recordsUpdated);
        }catch (SQLException | NamingException e) {
            logger.catching(e);
        }
    }
}
