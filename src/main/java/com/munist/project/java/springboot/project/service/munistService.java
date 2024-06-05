package com.munist.project.java.springboot.project.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.munist.project.java.springboot.project.model.Image;

@Service
public class munistService {
    private Map<String, Image> db = new HashMap<>() {{
        put("1", new Image("1", "first.jpg"));
        put("2", new Image("2", "second.jpg"));
    }};

    public Collection<Image> get() {
        return db.values();
    }

    public Image get(String id) {
        return db.get(id);
    }

    public Image remove(String id) {
        return db.remove(id);
    }

    public Image save(String fileName, String contentType, byte[] data) {
        Image image = new Image();
        image.setId(UUID.randomUUID().toString());
        image.setContentType(contentType);
        image.setFileName(fileName);
        image.setData(data);
        db.put(image.getId(), image);
        return image;
    }
}
