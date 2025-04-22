package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.service;

import java.util.List;

public interface CustomerService<T> {
    T create(T obj);
    T read(Long id);
    List<T> reads();
    T update (T object , Long id);
    T delete (Long id);
}
