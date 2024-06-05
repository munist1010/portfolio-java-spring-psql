package com.munist.project.java.springboot.project.web;

import java.io.IOException;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.munist.project.java.springboot.project.model.Image;
import com.munist.project.java.springboot.project.service.munistService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;


@RestController
public class munistController {

    private final munistService munistService;

    public munistController(munistService munistService) {
        this.munistService = munistService;
    }
    @GetMapping("/")
    public String get() {
        return "The start of the REST service";
    }

    @GetMapping("/images") 
        public Collection<Image> getImages() {
            return munistService.get();
        };
        
    @GetMapping("/images/{id}")
        public Image getImageById(@PathVariable String id) {
            Image image = munistService.get(id);
            if (image == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            return image;

        }
    
    @DeleteMapping("/images{id}")
    public void deleteImage(@PathVariable String id) {
        Image image = munistService.remove(id);
        if (image == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/images") 
    public Image postImage(@RequestPart("data") MultipartFile file) throws IOException {
        return munistService.save(file.getOriginalFilename(), file.getContentType() ,file.getBytes());
    }
    
}
