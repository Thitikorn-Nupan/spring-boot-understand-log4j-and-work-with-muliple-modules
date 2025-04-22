package com.ttknpdev.springbootcrudmanytooneh2disktakelog4j.exception.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Warning {
    private Short status;
    private String code;
    private String course;
}
