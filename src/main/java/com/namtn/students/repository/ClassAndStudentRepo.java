package com.namtn.students.repository;

import com.namtn.students.entity.Classes;
import com.namtn.students.entity.ClassesAndStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ClassAndStudentRepo extends JpaRepository<ClassesAndStudent,Long> {
    Boolean existsByStudentCodeAndClassesCode(String classesCode, String studentCode);

    @Query("SELECT c.classesCode, " +
            "COUNT(c.studentCode) " +
            "FROM ClassesAndStudent c " +
            "GROUP BY c.classesCode")
    List<Object[]> countStudentsByClassroom();

}
