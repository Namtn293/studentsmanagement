package com.namtn.students.service.implement;

import com.namtn.students.dto.ClassesDTO;
import com.namtn.students.dto.StudentDTO;
import com.namtn.students.entity.Classes;
import com.namtn.students.entity.ClassesAndStudent;
import com.namtn.students.entity.Student;
import com.namtn.students.exception.EntityNotFoundException;
import com.namtn.students.repository.ClassAndStudentRepo;
import com.namtn.students.repository.ClassesRepo;
import com.namtn.students.repository.StudentRepo;
import com.namtn.students.service.ClassesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassesIplm implements ClassesService {
    final ClassesRepo classesRepo;
    final ModelMapper mapper;

    final StudentRepo studentRepo;
    final ClassAndStudentRepo classAndStudentRepo;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createClasses(ClassesDTO dto) {
        Classes classes=mapper.map(dto,Classes.class);
        classes.setId(null);
        classes.setDeleted(false);
        List<String> strings=dto.getStudentCode();
        strings.forEach(s -> {
            if (!classAndStudentRepo.existsByStudentCodeAndClassesCode(dto.getClassesCode(),s)){
                classAndStudentRepo.save(new ClassesAndStudent(null,s, dto.getClassesCode()));
            }
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateClasses(ClassesDTO dto) {
        Classes classes=classesRepo.findByClassesCode(dto.getClassesCode())
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        mapper.map(dto,classes);
        List<String> strings=dto.getStudentCode();
        strings.forEach(s -> {
            if (!classAndStudentRepo.existsByStudentCodeAndClassesCode(dto.getClassesCode(),s)){
                classAndStudentRepo.save(new ClassesAndStudent(null,s, dto.getClassesCode()));
            }
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteClasses(String classCode) {
        classesRepo.findByClassesCode(classCode).ifPresent(
                classes -> {
                    classes.setDeleted(true);
                    classesRepo.save(classes);
                }
        );
    }
}
