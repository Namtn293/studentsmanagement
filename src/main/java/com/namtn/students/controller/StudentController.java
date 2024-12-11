package com.namtn.students.controller;



import com.namtn.students.dto.StudentDTO;
import com.namtn.students.dto.StudentSearchDTO;
import com.namtn.students.reponse.ResponseApi;
import com.namtn.students.service.StudentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/v1/student")
public class StudentController {
    final StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseApi> create(@Valid @RequestBody StudentDTO dto){
        studentService.createStudent(dto);
        return ResponseEntity.ok(new ResponseApi(null,"Create success",201));
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseApi> update(@Valid @RequestBody StudentDTO dto){
        studentService.updateStudent(dto);
        return ResponseEntity.ok(new ResponseApi(null,"Update success",201));
    }

    @PostMapping("delete/{code}")
    public ResponseEntity<ResponseApi> delete(@PathVariable String code){
        studentService.deleteStudent(code);
        return ResponseEntity.ok(new ResponseApi(null,"Delete success",201));
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseApi> search(@Valid @RequestBody StudentSearchDTO dto){
        return ResponseEntity.ok(new ResponseApi(studentService.searchStudent(dto),"Search success",201));
    }
}
