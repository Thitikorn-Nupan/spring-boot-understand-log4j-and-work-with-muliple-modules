package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = Contact.TABLE)
public class Contact {

    protected final static String TABLE = "contacts";

    @Id
    private String phone;
    private String email;
    private Boolean status;

}
