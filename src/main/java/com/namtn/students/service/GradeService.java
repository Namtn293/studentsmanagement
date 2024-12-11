package com.namtn.students.service;

import com.namtn.students.dto.*;

import java.util.List;

public interface GradeService {
    void addGrade(GradeDTO dto);

    void update(GradeDTO dto);

    List<GradeDTO> getAll();

    void averageAndRating();

    List<GenderDTO> reportGender();

    List<ClassesReportDTO> reportClasses();

    List<AgeDTO> reportAge();

    List<RatingDTO> getRatingStudent();

}
