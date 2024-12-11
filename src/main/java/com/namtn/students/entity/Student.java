package com.namtn.students.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.Date;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "STUDENT_CODE")
    String studentCode;

    @Column(name = "STUDENT_NAME")
    String studentName;

    @Column(name = "BIRTH")
    Date birth;

    @Column(name = "GENDER")
    String gender;

    @Column(name = "ADDRESS")
    String address;

    @Column(name = "EMAIL")
    @Email String mail;

    @Column(name = "PHONE_NUMBER")
    String phoneNumber;


    @Column(name = "AVERAGE")
    Double average;

    @Column(name = "RATING")
    String rating;

    @Column(name = "AGE_TOTAL")
    Integer ageTotal;

    @Column(name = "DELETED")
    boolean deleted = false;

}
