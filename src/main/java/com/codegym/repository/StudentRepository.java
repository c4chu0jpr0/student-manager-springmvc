package com.codegym.repository;

import com.codegym.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student,Long> {
    Iterable<Student> findAllByNameContaining(String name);
}
