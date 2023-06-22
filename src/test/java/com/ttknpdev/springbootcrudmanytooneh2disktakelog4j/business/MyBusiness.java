package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.business;

import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.dao.DaoContact;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.dao.DaoCustomer;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.entities.Contact;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.entities.Customer;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.repositories.ContactRepository;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.repositories.CustomerRepository;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.service.ContactService;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@Rollback(value = false)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MyBusiness {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private ContactRepository contactRepository;
    private CustomerService service;
    private ContactService contactService;
    @Autowired
    private void setService() {
        service = new DaoCustomer(repository);
        contactService = new DaoContact(contactRepository,repository);
    }

    @Test
    public void creat() {
        Customer customer = new Customer();
        Contact contact = new Contact();
        List<Contact> contactList = new ArrayList<>();

        customer.setCustomerSalary(25000.50F);
        customer.setCustomerFullname("Peter Parkers");
        customer.setCustomerLevel('C');

        contact.setStatus(null);
        contact.setEmail("peter@abcde.com");
        contact.setPhone("0646523213");

        contactList.add(contact);

        customer.setContacts(contactList);

        assertNotNull(service.create(customer));
        /*
        Hibernate: insert into customers (customer_fullname,customer_level,customer_salary,customer_id) values (?,?,?,default)
        Hibernate: insert into contacts (email,status,phone) values (?,?,?)
        Hibernate: update contacts set contact_customer_id=? where phone=?
        */
    }
/*
    @Test
    public void updateContact() {
        Contact contact = new Contact();
        contact.setStatus(null);
        contact.setEmail("mark@abcde.com");
        contact.setPhone("0646523213"); // search by primary key
        assertNotNull(contactService.update(contact,1L));
    }
*/

    @Test
    public void update() {

        Customer customerDefault = (Customer) service.read(1L); // search by primary key

        List<Contact> contactListDefault = customerDefault.getContacts(); // retrieve contact of list contact in default id 1
        Contact contact = contactListDefault.get(0); // get first element

        // set new value
        customerDefault.setCustomerSalary(35000.50F);
        customerDefault.setCustomerFullname("Mark Parkers");
        customerDefault.setCustomerLevel('B');

        contact.setStatus(true);
        contact.setEmail("mark_parker@abcde.com");

        contactListDefault.add(contact); // add new contact with default primary key
        customerDefault.setContacts(contactListDefault); // set new list contact and done!

        assertNotNull(service.update(customerDefault,1L));

    }
    @Test
    public void reads() {
        assertNotNull("shouldn't be null from method reads()",service.reads());
    }
    @Test
    public void read() {
        assertNotNull(service.read(1L));
    }
    @Test
    public void readsContact() {
        assertNotNull("shouldn't be null from method reads()",contactService.reads());
    }
}
