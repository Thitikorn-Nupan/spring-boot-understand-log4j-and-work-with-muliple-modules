package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.service;

import java.util.List;
import java.util.Map;

public interface ContactService <T>{
    List<T> reads();
    T read (String phone);
    T delete (String phone);
    Map<?,?> deleteAll (Long id);
}
