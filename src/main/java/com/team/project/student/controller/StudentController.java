package com.team.project.student.controller;

import com.team.project.student.dto.StudentDTO;
import com.team.project.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    @Value("${paging.size}")
    private int pageSize;

    @GetMapping()
    public ResponseEntity<List<StudentDTO>> findPaginated(@RequestParam(name = "page") int pageNumber) {
        List<StudentDTO> students = studentService.findPaginated(pageNumber,pageSize);
        HttpHeaders headers = studentService.getNumberOfPagesAndElementsSum(pageNumber,pageSize);
        return new ResponseEntity<>(students, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable(value = "id") Long id) {
        StudentDTO studentDTO = studentService.findById(id);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO studentDTO){
        StudentDTO studentReturned = studentService.save(studentDTO);
        return new ResponseEntity<>(studentReturned, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentDTO> update(@RequestBody StudentDTO studentDTO){
        StudentDTO studentReturned = studentService.update(studentDTO);
        return new ResponseEntity<>(studentReturned, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDTO> deleteById(@PathVariable(value = "id") Long id){
        studentService.deteleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
