package com.namtn.students.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Data
public class ClassesReportDTO {
    int total;
    Long classesId;
}
