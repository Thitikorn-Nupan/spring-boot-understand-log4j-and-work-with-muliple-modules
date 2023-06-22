package com.ttknpdev.mycommonsresponse.common;


import org.springframework.http.HttpStatus;

/* this class for telling user status code */
public class Constants {
    public static final Short STATUS_ACCEPT = (short) HttpStatus.ACCEPTED.value();
    public static final Short STATUS_OK = (short) HttpStatus.OK.value();
    public static final Short STATUS_UN_ACCEPT = (short) HttpStatus.NOT_ACCEPTABLE.value();
    public static final String STATUS_ACCEPT_CODE = "accepted response";
    public static final String STATUS_OK_CODE = "ok response";
    public static final String STATUS_UN_ACCEPT_CODE = "unaccepted response";

}
