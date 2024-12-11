package com.namtn.students.controller;

import com.namtn.students.dto.ClassesDTO;
import com.namtn.students.reponse.ResponseApi;
import com.namtn.students.service.ClassesService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassesController {
    final ClassesService classesService;

    @PostMapping("/create")
    public ResponseEntity<ResponseApi> create(@Valid @RequestBody ClassesDTO dto){
        classesService.createClasses(dto);
        return ResponseEntity.ok(new ResponseApi(null,"Success",201));
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseApi> update(@Valid @RequestBody ClassesDTO dto){
        classesService.updateClasses(dto);
        return ResponseEntity.ok(new ResponseApi(null,"Success",201));
    }

    @PostMapping("/delete/{classescode}")
    public ResponseEntity<ResponseApi> delete(@PathVariable String classescode){
        classesService.deleteClasses(classescode);

        return ResponseEntity.ok(new ResponseApi(null,"Success",201));
    }
}
