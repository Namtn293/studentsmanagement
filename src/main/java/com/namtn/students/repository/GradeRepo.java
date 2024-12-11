package com.namtn.students.repository;

import com.namtn.students.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface GradeRepo extends JpaRepository<Grade,Long> {
    Optional<Grade> findByGradeCode(String gradeCode);

    @Query("SELECT sg.studentCode, AVG(g.score) " +
            "FROM StudentAndGrade sg " +
            "JOIN Grade g ON sg.gradeCode = g.gradeCode " +
            "GROUP BY sg.studentCode")
    List<Object[]> calculateAverage();
}
