package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.repositories;

import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.entities.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends CrudRepository<Contact,String> {
    // when update/delete by native query don't forget use @Modifying , @Transactional
    @Modifying
    @Transactional
    @Query(value = "delete from contacts where contact_customer_id=:id",nativeQuery = true)
    int deleteByForeignKey(@Param("id") Long id);
}
