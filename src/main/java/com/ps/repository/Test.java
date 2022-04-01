package com.ps.repository;

import com.ps.domain.Contact;

import javax.naming.NamingException;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, NamingException {
        ContactRepository contactrepository = new ContactRepositoryImpl();
        Contact contact = new Contact(1,"Arun","kumar","7845451997","arunkmoorthy97@gmail.com","16-10-1997","114,svn pillai st kanchipuram","img",3);
        Contact contact1 = contactrepository.create(contact);
        System.out.println(contact1);
    }
}
