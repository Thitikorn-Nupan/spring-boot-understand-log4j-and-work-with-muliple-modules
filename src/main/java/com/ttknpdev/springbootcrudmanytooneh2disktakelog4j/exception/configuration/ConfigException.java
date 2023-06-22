package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.exception.configuration;

import com.ttknpdev.mycommonsresponse.common.Constants;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.exception.entity.MyWarn;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.exception.handler.NotAllowedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConfigException {
    @ExceptionHandler(value = NotAllowedResponse.class)
    public ResponseEntity<MyWarn> handlerNotAllowedResponse(NotAllowedResponse notAllowedResponse) {
        MyWarn warn = new MyWarn();
        warn.setStatus(Constants.STATUS_UN_ACCEPT);
        warn.setCode(Constants.STATUS_UN_ACCEPT_CODE);
        warn.setCourse(notAllowedResponse.getCurrentCourse());
        ResponseEntity<MyWarn> myWarnResponseEntity = new ResponseEntity<>(warn, HttpStatus.OK);
        return myWarnResponseEntity;
    }
}
