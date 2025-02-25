package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.dao;

import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.entities.Contact;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.exception.handler.NotAllowedResponse;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.repositories.ContactRepository;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.repositories.CustomerRepository;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.service.ContactService;
// import lombok.extern.slf4j.Slf4j;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @Slf4j // ** it's not log4j so it won't do log4j.prop you provide
@Log4j // log4j
@Service
public class DaoContact implements ContactService<Contact> {
    private ContactRepository contactRepository;
    private CustomerRepository customerRepository;
    // private final static Logger daoContactLogger = Logger.getLogger(DaoContact.class); // i used @Log4j to config my log4j
    @Autowired
    public DaoContact(ContactRepository contactRepository , CustomerRepository customerRepository) {
        this.contactRepository = contactRepository;
        this.customerRepository = customerRepository;
    }
/*
    @Override
    public List<Contact> update(List<Contact> obj , Long id) {
        for (int i = 0; i < obj.size(); i++) {
            if (!validate(obj.get(i))){
                throw new RuntimeException("unacceptable properties of contact");
            }
        }
        return customerRepository.findById(id)
                .map((customer) -> {
                    List<Contact> contacts = new ArrayList<>();
                    contacts.addAll(obj);
                    customer.setContacts(contacts);
                    customerRepository.save(customer); // it will store id (primary key) So it will update instead create
                    return obj;
                }).orElseThrow(() -> {
                    throw new RuntimeException("something was wrong in method crate() of contact");
                });
    }
*/

    @Override
    public List<Contact> reads() {
        List<Contact> contacts = new ArrayList<>(); // default size is zero
        contactRepository
                .findAll()
                .forEach(contact -> {
                    contacts.add(contact);
                });
        if (contacts.size() == 0) {
            log.warn("there were no rows in your contacts table");
            throw new NotAllowedResponse("unacceptable to reads method of contact , no rows");
        }
        else {
            return contacts;
        }
    }

    @Override
    public Contact read(String phone) {
        return contactRepository
                .findById(phone)
                .orElseThrow(()->{
                    log.warn("there were no phone : "+phone+" in your contacts table");
                    throw new NotAllowedResponse("unacceptable to read method of contact , no phone id");
                });
    }

    @Override
    public Contact delete(String phone) {
        return contactRepository
                .findById(phone)
                .map(contact -> {
                    contactRepository.deleteById(phone);
                    return contact;
                })
                .orElseThrow(()->{
                    log.warn("there were no phone : "+phone+" in your contacts table");
                    throw new NotAllowedResponse("unacceptable to read method of contact , no phone id");
                });
    }

    @Override
    public Map<?, ?> deleteAll(Long id) {
        Map<String,Integer> response = new HashMap<>();
        return customerRepository
                .findById(id)
                .map((customer) -> {
                    Integer rows = contactRepository.deleteByForeignKey(id);
                    response.put("deleted all" , rows);
                    return response;
                })
                .orElseThrow(()->{
                    log.warn("there were no foreign key id : "+id+" in your contacts table");
                    throw new RuntimeException("unacceptable to read method of deleteAll , no foreign key");
                });
    }

    /*    public static void main(String[] args) {
        List<Contact> contacts = new ArrayList<>();
        daoContactLogger.log(Level.INFO,"start object list store empty value");
        daoContactLogger.info("contacts.size() is "+contacts.size()+" if they were empty lists object");
        Contact contact = new Contact();
        contact.setPhone("123");
        contact.setEmail("123@123");
        contact.setStatus(true);
        contacts.add(contact);
        daoContactLogger.log(Level.INFO,"after add some value to list"); //  Logs a message object with the given level.
        daoContactLogger.info("contacts.size() is "+contacts.size()+" if they weren't empty lists object"); // output same top line
    }*/
}


