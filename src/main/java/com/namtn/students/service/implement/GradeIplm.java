package com.namtn.students.service.implement;

import com.namtn.students.dto.*;
import com.namtn.students.entity.*;
import com.namtn.students.exception.EntityNotFoundException;
import com.namtn.students.repository.*;
import com.namtn.students.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeIplm implements GradeService {

    final GradeRepo gradeRepo;
    final StudentRepo studentRepo;
    final StudentAndGradeRepo studentAndGradeRepo;
    final SubjectAndGradeRepo subjectAndGradeRepo;
    final ClassAndStudentRepo classAndStudentRepo;
    final ModelMapper mapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGrade(GradeDTO dto) {
        Grade grade=new Grade();
        grade.setId(null);
        grade.setScore(dto.getScore());
        grade.setGradeCode(dto.getGradeCode());
        studentAndGradeRepo.save(new StudentAndGrade(null,dto.getGradeCode(),dto.getStudentCode()));
        subjectAndGradeRepo.save(new SubjectAndGrade(null,dto.getGradeCode(),dto.getSubjectCode()));
        gradeRepo.save(grade);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(GradeDTO dto) {
        Grade grade=gradeRepo.findByGradeCode(dto.getGradeCode())
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        grade.setScore(dto.getScore());
        StudentAndGrade grade1=studentAndGradeRepo.findByStudentCode(dto.getStudentCode())
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        grade1.setGradeCode(dto.getGradeCode());
        SubjectAndGrade grade2=subjectAndGradeRepo.findBySubjectCode(dto.getSubjectCode())
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        grade2.setGradeCode(dto.getGradeCode());
        gradeRepo.save(grade);
    }

    @Override
    public List<GradeDTO> getAll() {
        List<Grade> gradeList=gradeRepo.findAll();
        List<GradeDTO> dtos=new ArrayList<>();

        gradeList.forEach(
                grade -> {
            //
//            GradeDTO gradeDTO=new GradeDTO();
//            gradeDTO.setId(grade.getId());
//            gradeDTO.setScore(grade.getScore());
//            gradeDTO.setNameStudent(grade.getStudent().getStudentName());
//            gradeDTO.setNameSubject(grade.getSubject().getSubjectName());
//            gradeDTO.setSubjectId(grade.getSubject().getId());
//            gradeDTO.setStudentId(grade.getStudent().getId());
          GradeDTO gradeDTO=mapper.map(grade,GradeDTO.class);
            dtos.add(gradeDTO);
        });
        return dtos;
    }

    @Override
    public void averageAndRating() {
        List<Object[]> objects=gradeRepo.calculateAverage();
        for (Object[] row:objects){
            String studentCode=(String) row[0];
            Student student=studentRepo.findByStudentCode(studentCode).orElse(null);
            Double average=(Double) row[1];
            student.setAverage(average);
            if (average>=8.0) student.setRating("Good");
            else if (average>=6.0) student.setRating("Fair");
            else student.setRating("Poor");
            studentRepo.save(student);
        }
    }
    @Override
    public List<GenderDTO> reportGender() {
        List<GenderDTO> genderDTOList=new ArrayList<>();
        List<Object[]> results=studentRepo.countStudentsByGenderNative();
        for (Object[] a : results) {
            String gender = (String) a[0];
            Integer count = ((Number) a[1]).intValue();
            genderDTOList.add(new GenderDTO(count, gender));
        }
        return genderDTOList;
    }

    @Override
    public List<ClassesReportDTO> reportClasses() {
        List<ClassesReportDTO> classesReportDTOList=new ArrayList<>();
        List<Object[]> results=classAndStudentRepo.countStudentsByClassroom();
        for (Object[] a:results){
            Long id=(Long) a[0];
            Integer count=((Number) a[1]).intValue();
            classesReportDTOList.add(new ClassesReportDTO(count,id));
        }
        return classesReportDTOList;
    }

    @Override
    public List<AgeDTO> reportAge() {
        List<Student> students=studentRepo.findAll();
        Calendar today=Calendar.getInstance();
        today.setTime(new Date());
        for (Student student:students){
            Calendar birth=Calendar.getInstance();
            birth.setTime(student.getBirth());
            int age=today.get(Calendar.YEAR)-birth.get(Calendar.YEAR);
            student.setAgeTotal(age);
            studentRepo.save(student);
        }
        List<AgeDTO> ageDTOList=new ArrayList<>();
        List<Object[]> results=studentRepo.countStudentsByAgeNative();
        for (Object[] a:results){
            Integer age = ((Number) a[0]).intValue();
            Integer total=((Number)a[1]).intValue();
            ageDTOList.add(new AgeDTO(total,age));
        }
        return ageDTOList;
    }

    @Override
    public List<RatingDTO> getRatingStudent() {
        List<RatingDTO> dtoList=new ArrayList<>();
        List<StudentDTO> dtos1=new ArrayList<>();
        List<StudentDTO> dtos2=new ArrayList<>();
        List<StudentDTO> dtos3=new ArrayList<>();

        List<Student> students=studentRepo.findAll();
        students.forEach(student -> {
            if ("Good".equals(student.getRating())) dtos1.add(mapper.map(student,StudentDTO.class));
            else if ("Fair".equals(student.getRating())) dtos2.add(mapper.map(student,StudentDTO.class));
            else dtos3.add(mapper.map(student,StudentDTO.class));
        });
        RatingDTO dto1=new RatingDTO();
        dto1.setStatus("Good");
        RatingDTO dto2=new RatingDTO();
        dto2.setStatus("Fair");
        RatingDTO dto3=new RatingDTO();
        dto3.setStatus("Bad");

        dto1.setStudentDTOS(dtos1);
        dto2.setStudentDTOS(dtos2);
        dto3.setStudentDTOS(dtos3);

        dtoList.add(dto1);
        dtoList.add(dto2);
        dtoList.add(dto3);
        return dtoList;
    }
}
