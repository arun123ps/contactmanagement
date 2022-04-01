package com.ps.repository;

import com.ps.domain.Contact;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface ContactRepository {
    List<Contact> findAll() throws SQLException, NamingException;

    public Contact create(Contact contact) throws SQLException, NamingException;
    void insert (Contact contact);

//    public Contact updateStatus(Contact contact) throws  SQLException, NamingException;
    void edit(int id, String firstname);
    void delete(int id);
}
