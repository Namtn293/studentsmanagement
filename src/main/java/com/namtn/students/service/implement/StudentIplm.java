package com.namtn.students.service.implement;


import com.namtn.students.dto.StudentDTO;
import com.namtn.students.dto.StudentSearchDTO;
import com.namtn.students.entity.ClassesAndStudent;
import com.namtn.students.entity.Student;
import com.namtn.students.exception.EntityNotFoundException;
import com.namtn.students.repository.ClassAndStudentRepo;
import com.namtn.students.repository.StudentRepo;
import com.namtn.students.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentIplm implements StudentService {
    final StudentRepo studentRepo;
    final ModelMapper mapper;
    final ClassAndStudentRepo classAndStudentRepo;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createStudent(StudentDTO dto) {
        Student student=mapper.map(dto,Student.class);
        student.setId(null);
        student.setDeleted(false);
        List<String> strings=dto.getClassCode();
        strings.forEach(s->{
            if (!classAndStudentRepo.existsByStudentCodeAndClassesCode(s, dto.getStudentCode())) {
                classAndStudentRepo.save(new ClassesAndStudent(null, dto.getStudentCode(), s));
            }
        });
        studentRepo.save(student);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStudent(StudentDTO dto) {
        Student student=studentRepo.findByStudentCode(dto.getStudentCode())
                    .orElseThrow(() -> new EntityNotFoundException("not found"));
        mapper.map(dto,student);
        List<String> strings=dto.getClassCode();
        strings.forEach(s->{
            if (!classAndStudentRepo.existsByStudentCodeAndClassesCode(s, dto.getStudentCode())) {
                classAndStudentRepo.save(new ClassesAndStudent(null, dto.getStudentCode(), s));
            }
        });
        studentRepo.save(student);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStudent(String studentCode) {
        studentRepo.findByStudentCode(studentCode).ifPresent(
                student -> {
                    student.setDeleted(true);
                    studentRepo.save(student);
                }
        );
    }

    @Override
    public Page<StudentDTO> searchStudent(StudentSearchDTO dto) {
        if (dto.getPage()==null) dto.setPage(0);
        if (dto.getSize()==null) dto.setSize(5);
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(), Sort.by(Sort.Order.asc("id")));
        Page<Student> students1=studentRepo.findByStudentCode(dto.getStudentCode(),pageable);
        Page<Student> students2=studentRepo.findByStudentName(dto.getStudentName(),pageable);
        List<Student> students=new ArrayList<>();
        students.addAll(students1.getContent());
        students.addAll(students2.getContent());
        List<StudentDTO> studentDTOS=students.stream()
                .map(student -> mapper.map(student,StudentDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(studentDTOS);
    }

}
