package com.ps.service;

import com.ps.domain.Contact;
import com.ps.repository.ContactRepository;
import com.ps.repository.ContactRepositoryImpl;
import com.ps.repository.UserRepository;
import com.ps.repository.UserRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class ContactService {
    private static final Logger logger = LogManager.getLogger(ContactService.class);
    public Contact createContact (Contact contact) throws SQLException, NamingException {
        ContactRepository contactrepository = new ContactRepositoryImpl();
        logger.info("about to call repository to insert user to DB");
        return contactrepository.create(contact);
    }
    public List<Contact> getContacts() throws SQLException, NamingException {
        ContactRepository contactrepository =new ContactRepositoryImpl();
        return contactrepository.findAll();
    }
    public void updateStatus(int id,String firstname)
    {
        ContactRepository contactRepository=new ContactRepositoryImpl();
        logger.info("about to call the users to DB");
        contactRepository.edit(id,firstname);
    }
    public void detelecontact(int id)
    {
        ContactRepository contactRepository=new ContactRepositoryImpl();
        logger.info("delete the users to DB");
        contactRepository.delete(id);
    }

}
