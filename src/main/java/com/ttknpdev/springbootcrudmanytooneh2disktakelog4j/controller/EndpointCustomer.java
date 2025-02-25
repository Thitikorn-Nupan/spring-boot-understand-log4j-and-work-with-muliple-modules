package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.controller;

import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.entities.Customer;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import other my modules
//import com.ttknpdev.mycommonsresponse.response.ResponseObject;
//import com.ttknpdev.mycommonsresponse.common.Constants;

import java.util.List;

/* This Controller we use response pojo from other modules
*  And Client can see just status Ok / 200
*  Did Great*/
@RestController
@RequestMapping(value = "/api-customer")
public class EndpointCustomer {
    private CustomerService customerService;
    @Autowired
    public EndpointCustomer(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping(value = "/reads")
    private ResponseEntity<?> reads() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(customerService.reads());
        /*// @Builder in lombok work
        return ResponseEntity.ok(ResponseObject.<List<Customer>>builder()
                .status(Constants.STATUS_ACCEPT)
                .code(Constants.STATUS_ACCEPT_CODE)
                .object(customerService.reads())
                .build()); // don't forget build()*/
    }

    @GetMapping(value = "/read/{id}")
    private ResponseEntity<Object> read(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(customerService.read(id));
       /* return ResponseEntity.ok(ResponseObject.<Customer>builder()
                .status(Constants.STATUS_ACCEPT)
                .code(Constants.STATUS_ACCEPT_CODE)
                .object((Customer) customerService.read(id))
                .build());*/
    }

    @PostMapping(value = "/create")
    private ResponseEntity<Object> create(@RequestBody Customer customer) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.create(customer));
        /*return ResponseEntity.ok(ResponseObject.<Customer>builder()
                .status(Constants.STATUS_ACCEPT)
                .code(Constants.STATUS_ACCEPT_CODE)
                .object((Customer) customerService.create(customer))
                .build());*/
    }
    @PutMapping(value = "/update/{id}")
    private ResponseEntity<Object> update(@PathVariable Long id,@RequestBody Customer customer) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(customerService.update(customer,id));
        /*return ResponseEntity.ok(ResponseObject.<Customer>builder()
                .status(Constants.STATUS_OK)
                .code(Constants.STATUS_OK_CODE)
                .object((Customer) customerService.update(customer,id))
                .build());*/
    }
    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<Object> delete(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.delete(id));
        /*return ResponseEntity.ok(ResponseObject.<Customer>builder()
                .status(Constants.STATUS_OK)
                .code(Constants.STATUS_OK_CODE)
                .object((Customer) customerService.delete(id))
                .build());*/
    }


}
