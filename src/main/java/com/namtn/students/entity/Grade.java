package com.namtn.students.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "SCORE")
    Double score;
    @Column(name = "GRADE_CODE")
    String gradeCode;
}
