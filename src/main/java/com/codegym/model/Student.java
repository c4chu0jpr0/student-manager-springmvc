package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name, address, img;

    @Transient
    private MultipartFile avatar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("{student}")
    @JoinColumn(name="classes_id")
    private Classes classes;

    public Student() {
    }

    public Student(String name, String address, String img, Classes classes) {
        this.name = name;
        this.address = address;
        this.img = img;
        this.classes = classes;
    }

    public Student(String name, String address, Classes classes) {
        this.name = name;
        this.address = address;
        this.classes = classes;
    }

    public Student(Long id, String name, String address, String img, MultipartFile avatar, Classes classes) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.img = img;
        this.avatar = avatar;
        this.classes = classes;
    }
    public Student(String name, String address, MultipartFile avatar, Classes classes) {
        this.name = name;
        this.address = address;
        this.avatar = avatar;
        this.classes = classes;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
