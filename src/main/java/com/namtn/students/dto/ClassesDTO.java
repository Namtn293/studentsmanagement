package com.namtn.students.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ClassesDTO {
    Long id;

    String classesCode;

    String classesName;

    String describe;

    List<String> studentCode;
}
