package com.namtn.students.service;

import com.namtn.students.dto.ClassesDTO;

public interface ClassesService {
    void createClasses(ClassesDTO dto);

    void updateClasses(ClassesDTO dto);

    void deleteClasses(String classesCode);
}
