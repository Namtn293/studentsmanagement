package com.namtn.students.repository;

import com.namtn.students.entity.StudentAndGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentAndGradeRepo extends JpaRepository<StudentAndGrade,Long> {
    Optional<StudentAndGrade> findByStudentCode(String studentCode);

}
