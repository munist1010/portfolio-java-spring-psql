package com.munist.project.java.springboot.project;

public class Image {

    private String id;

    private String filename;

    public Image() {

    }

    public Image(String id, String filename) {
        this.id = id;
        this.filename = filename;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
