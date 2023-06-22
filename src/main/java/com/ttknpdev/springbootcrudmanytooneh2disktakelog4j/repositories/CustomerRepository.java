package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.repositories;

import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer , Long> { }
