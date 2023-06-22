package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.exception.handler;

public class NotAllowedResponse extends RuntimeException {
    private String currentCourse;

    public NotAllowedResponse(String message) {
        super(message);
        currentCourse = message;
    }

    public String getCurrentCourse() {
        return currentCourse;
    }
}
