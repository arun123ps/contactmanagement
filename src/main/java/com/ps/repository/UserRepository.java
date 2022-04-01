package com.ps.repository;

import com.ps.domain.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    List<User> find() throws SQLException, NamingException;

    public User create(User user) throws SQLException, NamingException;
    void insert (User user);

    //public User update(User user) throws SQLException, NamingException;
    void edit(int id, String name);
  void inprogress(int id);
}
