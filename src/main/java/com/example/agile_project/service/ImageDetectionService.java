package com.example.agile_project.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

public interface ImageDetectionService {
    JSONObject ImageDetection(String token);
}
