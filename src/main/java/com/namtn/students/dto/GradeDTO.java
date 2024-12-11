package com.namtn.students.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    Long id;
    String studentCode;
    String nameStudent;
    String subjectCode;
    String nameSubject;
    String gradeCode;
    Double score;
}
