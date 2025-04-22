package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.exception.handler;

import lombok.Getter;


public class NotAllowedResponse extends RuntimeException {

    @Getter
    private String currentCourse;

    public NotAllowedResponse(String message) {
        super(message); // you can call getMessage() 
        currentCourse = message;
    }

}
