package com.example.agile_project.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.agile_project.resultvo.ResultVO;
import com.example.agile_project.service.BaiduAccessTokenService;
import com.example.agile_project.service.ImageDetectionService;
import com.example.agile_project.service.ImageSaveService;
import com.example.agile_project.service.impl.BaiduAccessTokenServiceImpl;
import com.example.agile_project.service.impl.ImageDetectionServiceImpl;
import com.example.agile_project.service.impl.ImageSaveServiceImpl;
import com.example.agile_project.utils.ResultVOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UserController {

    @Autowired
    ImageSaveService imageSaveService;

    @Autowired
    BaiduAccessTokenService baiduAccessTokenService;

    @Autowired
    ImageDetectionService imageDetectionService;

    @RequestMapping("/login")
    public String Login(){
        return "login";
    }


    @RequestMapping (value = "/imageDetection",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ResultVO<Object> ImageDetection(@RequestParam("exampleInputFile") MultipartFile image){
        imageSaveService.imageSave(image);
        String token = baiduAccessTokenService.getToken();
        JSONObject resultVO = imageDetectionService.ImageDetection(token);
        return ResultVOUtils.ok(resultVO);
    }
}
