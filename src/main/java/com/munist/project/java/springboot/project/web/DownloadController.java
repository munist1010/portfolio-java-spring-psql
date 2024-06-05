package com.munist.project.java.springboot.project.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.munist.project.java.springboot.project.model.Image;
import com.munist.project.java.springboot.project.service.munistService;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class DownloadController {
    private final munistService munistService;

    public DownloadController(munistService munistService) {
        this.munistService = munistService;
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {
        Image image = munistService.get(id);
        if (image == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        byte[] data = image.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(image.getContentType()));
        ContentDisposition build = ContentDisposition.builder("attachment")
        .filename(image.getFileName())
        .build();
        headers.setContentDisposition(build);

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
    
}
