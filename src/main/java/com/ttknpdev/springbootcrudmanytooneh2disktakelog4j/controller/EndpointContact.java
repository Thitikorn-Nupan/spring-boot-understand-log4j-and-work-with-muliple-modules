package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.controller;

import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.entities.Contact;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api-contact")
public class EndpointContact {
    private ContactService contactService;
    @Autowired
    public EndpointContact(ContactService contactService) {
        this.contactService = contactService;
    }
    @GetMapping(value = "/reads")
    private ResponseEntity<List> reads() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(contactService.reads());
    }
    @GetMapping(value = "/read/{phone}")
    private ResponseEntity<Object> read(@PathVariable String phone) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(contactService.read(phone));
    }

    @DeleteMapping(value = "/delete/{phone}")
    private ResponseEntity<Object> delete(@PathVariable String phone) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contactService.delete(phone));
    }
/*
    @PutMapping(value = "/{id}/update")
    private ResponseEntity<Object> update(@PathVariable Long id , @RequestBody List<Contact> contact) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contactService.update(contact,id));
    }
*/
    @DeleteMapping(value = "/deleteAll/{id}")
    private ResponseEntity<Object> deleteAll(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contactService.deleteAll(id));
    }

}
