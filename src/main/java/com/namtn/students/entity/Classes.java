package com.namtn.students.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "CLASSES_CODE")
    String classesCode;

    @Column(name = "CLASSES_NAME")
    String classesName;

    @Column(name = "DESCRIBE")
    String describe;

    @Column(name = "DELETED")
    Boolean deleted;
}
