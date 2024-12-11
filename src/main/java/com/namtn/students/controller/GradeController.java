package com.namtn.students.controller;

import com.namtn.students.dto.*;
import com.namtn.students.reponse.ResponseApi;
import com.namtn.students.service.GradeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/grade")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class GradeController {
    final GradeService gradeService;

    @PostMapping("/create")
    public ResponseEntity<ResponseApi> create(@Valid @RequestBody GradeDTO dto){
        gradeService.addGrade(dto);
        return ResponseEntity.ok(new ResponseApi(null,"Success",201));
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseApi> update(@Valid @RequestBody GradeDTO dto){
        gradeService.update(dto);
        return ResponseEntity.ok(new ResponseApi(null,"success",201));
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseApi> getAll(){
        List<GradeDTO> dtos=gradeService.getAll();
        return ResponseEntity.ok( new ResponseApi(dtos,"Success",201));
    }

    @GetMapping("/average")
    public ResponseEntity<ResponseApi> averageAndRating(){
        gradeService.averageAndRating();
        return ResponseEntity.ok(new ResponseApi(null,"Success",201));
    }


    @GetMapping("/gender")
    public ResponseEntity<ResponseApi> getGender(){
        List<GenderDTO> genderDTOList=gradeService.reportGender();
        return ResponseEntity.ok(new ResponseApi(genderDTOList,"Success",201));
    }

    @GetMapping("/classes")
    public ResponseEntity<ResponseApi> getClasses(){
        List<ClassesReportDTO> dtos=gradeService.reportClasses();
        return ResponseEntity.ok(new ResponseApi(dtos,"Success",201));
    }

    @GetMapping("/age")
    public ResponseEntity<ResponseApi> getAge(){
        List<AgeDTO> dtos=gradeService.reportAge();
        return ResponseEntity.ok(new ResponseApi(dtos,"Success",201));
    }

    @GetMapping("/rating")
    public ResponseEntity<ResponseApi> getRating(){
        List<RatingDTO> dtoList=gradeService.getRatingStudent();
        return ResponseEntity.ok(new ResponseApi(dtoList,"Success",201));
    }

}
