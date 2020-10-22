package com.codegym.controller;

import com.codegym.model.Classes;
import com.codegym.service.ClassesService.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private ClassesService classesService;

    @ModelAttribute("classes")
    public Iterable<Classes> classes(){
        return classesService.findAll();
    }
    @GetMapping("/list")
    public String list(Model model){
        Iterable<Classes> classes = classesService.findAll();
        model.addAttribute("classes",classes);
        return "class/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        model.addAttribute("classes",classesService.findById(id));
        return "class/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateClasses(@ModelAttribute("classes") Classes classes,Model model){
        classesService.save(classes);
        model.addAttribute("message","oke");
        return "class/edit";
    }


    @GetMapping("")
    public ResponseEntity<Iterable<Classes>> getAll(){
        return new ResponseEntity<>(classesService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Classes> create(@RequestBody Classes classes){
        classesService.save(classes);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classes> edit(@PathVariable Long id,@RequestBody Classes classes){
        classesService.save(classes);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Classes> delete(@PathVariable Long id){
        classesService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
