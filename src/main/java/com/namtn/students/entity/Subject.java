package com.namtn.students.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "SUBJECT_NAME")
    String subjectName;

    @Column(name = "DESCRIBE")
    String describe;

    @Column(name = "SUBJECT_CODE")
    String subjectCode;
}
