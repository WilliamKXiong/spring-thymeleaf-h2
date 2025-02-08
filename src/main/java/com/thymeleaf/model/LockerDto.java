package com.thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LockerDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean locked;
    private Integer usage;
    private Integer capacity;
}
