package com.example.agile_project.service.impl;

import com.example.agile_project.service.ImageSaveService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageSaveServiceImpl implements ImageSaveService {

    @Override
    public void imageSave(MultipartFile image) {
        String imagePath = "/Users/venus/Desktop/Agile_Project/src/main/resources/images/";
        File file = new File(imagePath);
        if(!file.exists()){
            file.mkdirs();
        }

        String newFileName = "image1.png";
        String newFilePath = imagePath + newFileName;
        try {
            image.transferTo(new File(newFilePath));
            System.out.println("上传图片成功");
        }catch (IllegalStateException e){

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
