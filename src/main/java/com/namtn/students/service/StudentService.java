package com.namtn.students.service;


import com.namtn.students.dto.StudentDTO;
import com.namtn.students.dto.StudentSearchDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    void createStudent(StudentDTO dto);

    void updateStudent(StudentDTO dto);

    void deleteStudent(String studentCode);

    Page<StudentDTO> searchStudent(StudentSearchDTO dto);

}
