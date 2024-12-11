package com.namtn.students.dto;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class StudentDTO {
    Long id;

    String studentCode;


    String studentName;

    Date birth;

    String gender;

    String address;

    @Email
    String mail;

    String phoneNumber;

    List<String> classCode=new ArrayList<>();
}
