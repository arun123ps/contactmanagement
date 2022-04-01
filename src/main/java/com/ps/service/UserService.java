package com.ps.service;

import com.ps.domain.User;
import com.ps.repository.UserRepository;
import com.ps.repository.UserRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static final Logger logger = LogManager.getLogger(ContactService.class);
    public User createUsers (User user) throws SQLException, NamingException {
        UserRepository userrepository = new UserRepositoryImpl();
        logger.info("about to call repository to insert user to DB");
        return userrepository.create(user);
    }
    public List<User> getUsers() throws SQLException, NamingException {
        UserRepository userrepository = new UserRepositoryImpl();
        return userrepository.find();
    }

    public void updateStatus(int id,String name)
    {
        UserRepository userRepository=new UserRepositoryImpl();
        logger.info("about to call the users to DB");
        userRepository.edit(id,name);
    }
    public void inprogress(int id)
    {
        UserRepository userRepository=new UserRepositoryImpl();
        logger.info("delete the users to DB");
        userRepository.inprogress(id);
    }
}
