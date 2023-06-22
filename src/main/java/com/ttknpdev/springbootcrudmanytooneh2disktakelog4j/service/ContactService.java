package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.service;

import java.util.List;
import java.util.Map;

public interface ContactService <T>{
    // T create(T obj , Long id);
    List<T> reads();
    // List<T> update(List<T> obj , Long id);
    T read (String phone);
    T delete (String phone);
    Map<?,?> deleteAll (Long id);
}
