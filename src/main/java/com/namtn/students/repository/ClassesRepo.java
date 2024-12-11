package com.namtn.students.repository;

import com.namtn.students.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassesRepo extends JpaRepository<Classes,Long> {


    Optional<Classes> findByClassesCode(String classesCode);
}
