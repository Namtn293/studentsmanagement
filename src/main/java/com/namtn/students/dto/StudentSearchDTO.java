package com.namtn.students.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentSearchDTO {
    @NotNull
    @Min(value = 0, message = "Page must be greater than 0")
    private Integer page;

    @NotNull
    @Min(value = 0, message = "Size must be greater than 0")
    private Integer size;

    @Size(min=3,message = "Student code must be at least 5 characters long")
    String studentCode;

    @Size(min=3,message = "The name is not in the correct format")
    String studentName;

    Long id;
}
