package com.namtn.students.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class RatingDTO {
    String status;
    List<StudentDTO> studentDTOS;
}
