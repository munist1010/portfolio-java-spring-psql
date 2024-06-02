package com.munist.project.java.springboot.project;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class munistController {

    private List<Image> db = List.of(new Image("1", "test.jpg"));

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/images") 
        public List<Image> get() {
            return db;
        };
    
}
