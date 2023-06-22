package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.dao;

import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.exception.handler.NotAllowedResponse;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.entities.Contact;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.entities.Customer;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.repositories.CustomerRepository;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DaoCustomer implements CustomerService<Customer> {
    private final static Logger daoCustomerLogger = Logger.getLogger(DaoCustomer.class);
    private CustomerRepository customerRepository;
    private Boolean validate(Customer customer) {
        Boolean bool = null;
        if (customer.getCustomerFullname().trim().isEmpty()
                || customer.getCustomerLevel() == ' '
                || customer.getCustomerSalary() <= 0F) {
            daoCustomerLogger.debug("there were empty properties of customer");
            bool = false;
        }
        else {
            for (int i = 0; i < customer.getContacts().size(); i++) {
                Contact contact = customer.getContacts().get(i);
                if (contact.getStatus() == null
                        || contact.getEmail().trim().isEmpty()
                        || contact.getPhone().trim().isEmpty()) {
                    daoCustomerLogger.debug("there were empty properties of contact");
                    bool = false;
                    break;
                }
                else {
                    bool = true;
                }
            } // ended for
        } // ended else
        return bool;
    }
    @Autowired
    public DaoCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer create(Customer obj) {
        if (!validate(obj)) {
            throw new NotAllowedResponse("unacceptable properties of customer , some value were null");
        }
        return customerRepository.save(obj);
    }


    @Override
    public List<Customer> reads() {
        List<Customer> customers = new ArrayList<>();
        customerRepository
                .findAll()
                .forEach(customer -> {
                    customers.add(customer);
                });
        if (customers.size() == 0) {
            daoCustomerLogger.warn("there were no rows in your customers table");
            throw new NotAllowedResponse("unacceptable to reads method of customer , no rows in your table");
        }
        else {
            return customers;
        }
    }

    @Override
    public Customer read(Long id) {
        return customerRepository
                .findById(id)
                .orElseThrow(()->{
                    daoCustomerLogger.warn("there was no customer id "+id+"");
                    throw new NotAllowedResponse("unacceptable to method read , no customer id");
                });
    }

    @Override
    public Customer update(Customer object, Long id) {
        return customerRepository
                .findById(id)
                .map(customer -> {
                    if (validate(object)){
                        customer.setContacts(object.getContacts());
                        customer.setCustomerLevel(object.getCustomerLevel());
                        customer.setCustomerSalary(object.getCustomerSalary());
                        customer.setCustomerFullname(object.getCustomerFullname());
                        // add any new properties to pojo (with-out id) than save
                        return customerRepository.save(customer);
                    }
                    else {
                        throw new NotAllowedResponse("unacceptable properties of customer , some value were null");
                    }
                })
                .orElseThrow(()-> {
                    daoCustomerLogger.warn("there was no customer id "+id+"");
                    throw new NotAllowedResponse("unacceptable to method update , no customer id");
                });
    }

    @Override
    public Customer delete(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return customer;
                }).orElseThrow(()->{
                    daoCustomerLogger.warn("there was no customer id "+id+"");
                    throw new NotAllowedResponse("unacceptable to method delete , no customer id");
                });
    }
}
