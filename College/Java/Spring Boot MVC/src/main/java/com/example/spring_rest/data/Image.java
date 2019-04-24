package com.example.spring_rest.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.nio.file.attribute.FileTime;
import java.util.Objects;


public class Image {

    private String name,resolution;
    private Long size;
    private FileTime created;

    public Image() {
        name = "Null";
        size = 0L;
        resolution = "Null";
        created = null;
    }

    public Image(String name, Long size, String resolution, FileTime fileTime) {
        this.name = name;
        this.size = size;
        this.resolution = resolution;
        this.created = fileTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }


    public FileTime getCreated() {
        return created;
    }

    public void setCreated(FileTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(name, image.name) &&
                Objects.equals(resolution, image.resolution) &&
                Objects.equals(size, image.size) &&
                Objects.equals(created, image.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, resolution, size, created);
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", resolution='" + resolution + '\'' +
                ", size=" + size +
                ", created=" + created +
                '}';
    }

}
