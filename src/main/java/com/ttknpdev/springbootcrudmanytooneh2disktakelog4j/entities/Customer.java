package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = Customer.TABLE)
public class Customer {

    protected final static String TABLE = "customers";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "customer_fullname")
    private String customerFullname;
    @Column(name = "customer_salary")
    private Float customerSalary;
    @Column(name = "customer_level")
    private Character customerLevel;

    // One to Manny
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Contact.class)
    @JoinColumn(name = "contact_customer_id" , referencedColumnName = "customer_id")
    private List<Contact> contacts;

    public Customer(String customerFullname, Float customerSalary, Character customerLevel) {
        this.customerFullname = customerFullname;
        this.customerSalary = customerSalary;
        this.customerLevel = customerLevel;
    }

}
