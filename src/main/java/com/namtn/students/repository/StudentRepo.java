package com.namtn.students.repository;

import com.namtn.students.dto.StudentDTO;
import com.namtn.students.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    @Query(value = "SELECT gender, COUNT(*) FROM student GROUP BY gender", nativeQuery = true)
    List<Object[]> countStudentsByGenderNative();
    @Query(value = "SELECT AGE_TOTAL, COUNT(*) FROM student GROUP BY AGE_TOTAL", nativeQuery = true)
    List<Object[]> countStudentsByAgeNative();
    Page<Student> findByStudentName(String studentName,Pageable pageable);
    Page<Student> findByStudentCode(String studentCode,Pageable pageable);
    Optional<Student> findByStudentCode(String studentCode);

}
