package com.student.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.repository.StudentRepository;
import com.student.demo.model.StudentEntity;
 
@Service
public class StudentService {
     
    @Autowired
    StudentRepository repository;
     
    public List<StudentEntity> getAllStudents()
    {
        List<StudentEntity> employeeList = repository.findAll();
         
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<StudentEntity>();
        }
    }
     
    public StudentEntity getEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<StudentEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No student record exist for given id");
        }
    }
     
    public StudentEntity createOrUpdateEmployee(StudentEntity entity) throws RecordNotFoundException
    {
        Optional<StudentEntity> employee = repository.findById(entity.getId());
         
        if(employee.isPresent())
        {
            StudentEntity newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setAddress(entity.getAddress());
            newEntity.setMarks(entity.getMarks());
            newEntity.setAge(entity.getAge());
            newEntity.setPhone(entity.getPhone());

            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
}