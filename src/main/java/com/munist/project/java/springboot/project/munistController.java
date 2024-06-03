package com.munist.project.java.springboot.project;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class munistController {

    private Map<String, Image> db = new HashMap<>() {{
        put("1", new Image("1", "test.jpg"));
    }};
    @GetMapping("/")
    public String get() {
        return "The start of the REST service";
    }

    @GetMapping("/images") 
        public Collection<Image> getImages() {
            return db.values();
        };
        
    @GetMapping("/images/{id}")
        public Image getImageById(@PathVariable String id) {
            Image image = db.get(id);
            if (image == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            return image;

        }
    
    @DeleteMapping("/images")
    public void deleteImage(@PathVariable String id) {
        Image image = db.remove(id);
        if (image == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/images/")
    public Image postImage(Image image) {
        image.setId(UUID.randomUUID().toString());
        db.put(image.getId(), image);
        return image;
    }
    
}
