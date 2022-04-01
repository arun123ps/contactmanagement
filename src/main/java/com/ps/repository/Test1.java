package com.ps.repository;

import com.ps.domain.User;

import javax.naming.NamingException;
import java.sql.SQLException;

public class Test1 {
    public static void main(String[] args) throws SQLException, NamingException {
        UserRepository userrepository = new UserRepositoryImpl();
        User user = new User(1,"dinesh","1234");
        User User1 = userrepository.create(user);
        System.out.println(User1);
    }
}
