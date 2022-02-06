package com.example.agile_project.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageSaveService {

    void imageSave(MultipartFile image);
}
