package com.codegym.service.ClassesService;

import com.codegym.model.Classes;

public interface ClassesService {
    Iterable<Classes> findAll();

    Classes findById(Long id);
    void save(Classes classes);
    void remove(Long id);
}
