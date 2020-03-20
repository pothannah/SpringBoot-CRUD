package com.student.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.student.demo.model.StudentEntity;
import com.student.demo.service.StudentService;
 
@RestController
@RequestMapping("/student")
public class StudentController
{
    @Autowired
    StudentService service;
 
    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllEmployees() {
        List<StudentEntity> list = service.getAllStudents();
 
        return new ResponseEntity<List<StudentEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        StudentEntity entity = service.getEmployeeById(id);
 
        return new ResponseEntity<StudentEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<StudentEntity> createOrUpdateEmployee(StudentEntity employee)
                                                    throws RecordNotFoundException {
        StudentEntity updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<StudentEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
 
}