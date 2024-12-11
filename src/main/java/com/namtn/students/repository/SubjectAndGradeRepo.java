package com.namtn.students.repository;

import com.namtn.students.entity.SubjectAndGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectAndGradeRepo extends JpaRepository<SubjectAndGrade,Long> {
    Optional<SubjectAndGrade> findBySubjectCode(String subjectCode);
}
