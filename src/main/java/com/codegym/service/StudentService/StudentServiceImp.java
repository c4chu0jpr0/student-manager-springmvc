package com.codegym.service.StudentService;

import com.codegym.model.Student;
import com.codegym.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class StudentServiceImp implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findOne(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void remove(Long id) {
        studentRepository.delete(id);
    }

    @Override
    public Page<Student> findAllByPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Iterable<Student> findAllByNameContainning(String name) {
        return studentRepository.findAllByNameContaining(name);
    }
}
