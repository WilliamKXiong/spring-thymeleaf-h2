package com.thymeleaf.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LockerDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean locked;
    private Integer usage;
    private Integer capacity;
}
