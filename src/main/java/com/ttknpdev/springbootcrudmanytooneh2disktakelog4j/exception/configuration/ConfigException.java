package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.exception.configuration;

// import com.ttknpdev.mycommonsresponse.common.Constants;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.exception.entity.Warning;
import com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.exception.handler.NotAllowedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConfigException {

    @ExceptionHandler(value = NotAllowedResponse.class)
    public ResponseEntity<Warning> handlerNotAllowedResponse(NotAllowedResponse notAllowedResponse) {
        Warning warn = new Warning();
        warn.setStatus((short)406);
        warn.setCode("not acceptable");
        warn.setCourse(notAllowedResponse.getCurrentCourse());
        ResponseEntity<Warning> response = new ResponseEntity<>(warn, HttpStatus.OK);
        return response;
    }

}
