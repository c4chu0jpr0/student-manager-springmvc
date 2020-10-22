package com.codegym.controller;

import com.codegym.model.Classes;
import com.codegym.model.Student;
import com.codegym.service.ClassesService.ClassesService;
import com.codegym.service.StudentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private Environment environment;

    @ModelAttribute("classes")
    public Iterable<Classes> classes(){
        return classesService.findAll();
    }

    @GetMapping("")
    public String list(Model model, @PageableDefault(size = 10) Pageable pageable){
        Page<Student> students= studentService.findAllByPage(pageable);
        model.addAttribute("list",students);
        return "student/list";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model){
        model.addAttribute("student",new Student());
        return "/student/create";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student") Student studentForm){
        Student student = new Student(studentForm.getName(), studentForm.getAddress(), studentForm.getClasses());
        MultipartFile file = studentForm.getAvatar();
        String image = file.getOriginalFilename();
        student.setImg(image);
        String fileUpload = environment.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        studentService.save(student);
        return "/student/create";
    }

    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable Long id, Model model){
        model.addAttribute("student",studentService.findById(id));
        return "student/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@ModelAttribute("student") Student studentForm, @PathVariable Long id){
        Student student = studentService.findById(id);
        student.setName(studentForm.getName());
        student.setAddress(studentForm.getAddress());
        student.setClasses(studentForm.getClasses());
        student.setImg(studentForm.getImg());

        if(studentForm.getImg().equals("")){
            MultipartFile file = studentForm.getAvatar();
            String image = file.getOriginalFilename();
            student.setImg(image);
            String fileUpload = environment.getProperty("file_upload").toString();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + image));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        studentService.save(student);
        return "student/edit";
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id){
        studentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<Iterable<Student>> getAll(){
        return new ResponseEntity<>(studentService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Iterable<Student>> findAllByName(@PathVariable String name){
        return new ResponseEntity<>(studentService.findAllByNameContainning(name),HttpStatus.OK);
    }
}
