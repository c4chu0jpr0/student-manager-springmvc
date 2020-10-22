package com.codegym.service.StudentService;

import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Iterable<Student> findAll();

    Student findById(Long id);
    void save(Student student);
    void remove(Long id);

    Page<Student> findAllByPage(Pageable pageable);
    Iterable<Student> findAllByNameContainning(String name);

}
