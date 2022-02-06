package com.example.agile_project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.agile_project.resultvo.ResultVO;
import com.example.agile_project.service.ImageDetectionService;
import com.example.agile_project.utils.Base64Util;
import com.example.agile_project.utils.FileUtil;
import com.example.agile_project.utils.HttpUtil;
import org.springframework.stereotype.Service;


import java.net.URLEncoder;
import java.util.HashMap;

@Service
public class ImageDetectionServiceImpl implements ImageDetectionService {

    @Override
    public  JSONObject ImageDetection(String token){
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/driver_behavior";
        try {
            // 本地文件路径
            String filePath = "/Users/venus/Desktop/Agile_Project/src/main/resources/images/image1.png";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = token;
            System.out.println("accessToken is " + accessToken);
            String result = HttpUtil.post(url, accessToken, param);
            System.out.println("------ 以下是获取返回结果JSON值 ------");
            System.out.println(result);

            System.out.println("------ 以下是处理JSON值,将其转换为JSONObject,并处理 ------");
            JSONObject resultJSONObject = JSON.parseObject(result);
            int personNum = resultJSONObject.getInteger("person_num");
            System.out.println("检测到的总人数（包括驾驶员和乘客），0代表未监测到驾驶员：" + personNum);
            int driveNum = resultJSONObject.getInteger("driver_num");
            System.out.println("检测到的驾驶员数目。若大于1，则综合考虑人体框尺寸和位置，选取最佳驾驶员目标框进行属性分析，默认取画面中右侧最大的人体作为驾驶员（普遍适用于中国大陆地区）:" + driveNum);
            JSONArray personArray = resultJSONObject.getJSONArray("person_info");
            JSONObject attributesArray = personArray.getJSONObject(0).getJSONObject("attributes");
            JSONObject locationArray = personArray.getJSONObject(0).getJSONObject("location");
            int top = locationArray.getInteger("top");
            int left = locationArray.getInteger("left");
            int width = locationArray.getInteger("width");
            int height = locationArray.getInteger("height");
            System.out.println("驾驶员的位置信息是:" + "\n" + "top = " + top + "\n" + "left = " + left + "\n" + "width = " + width + "\n" + "height = " + height);
            JSONObject both_hands_leaving_wheel = attributesArray.getJSONObject("both_hands_leaving_wheel");
            float both_hands_leaving_wheelFloat_threshold = both_hands_leaving_wheel.getFloat("threshold");
            System.out.println("驾驶员双手离开方向盘的概率是：" + both_hands_leaving_wheelFloat_threshold);
            JSONObject eyes_closed = attributesArray.getJSONObject("eyes_closed");
            float eyes_closed_threshold = eyes_closed.getFloat("threshold");
            System.out.println("驾驶员眼睛未睁开的概率是：" + eyes_closed_threshold);
            JSONObject no_face_mask = attributesArray.getJSONObject("no_face_mask");
            float no_face_mask_threshold = no_face_mask.getFloat("threshold");
            System.out.println("驾驶员没有带口罩的概率是：" + no_face_mask_threshold);
            JSONObject not_buckling_up = attributesArray.getJSONObject("not_buckling_up");
            float not_bucking_up_threshold = not_buckling_up.getFloat("threshold");
            System.out.println("驾驶员没有带口罩的概率是: " + not_bucking_up_threshold);
            JSONObject smoke = attributesArray.getJSONObject("smoke");
            float smoke_threshold = smoke.getFloat("threshold");
            System.out.println("驾驶员吸烟的概率是: " + smoke_threshold);
            JSONObject cellphone = attributesArray.getJSONObject("cellphone");
            float cellphone_threshold = cellphone.getFloat("threshold");
            System.out.println("驾驶员在打电话的概率是: " + cellphone_threshold);
            JSONObject not_facing_front = attributesArray.getJSONObject("not_facing_front");
            float not_facing_front_threshold = not_facing_front.getFloat("threshold");
            System.out.println("驾驶员视角未朝前方的概率是: " + not_facing_front_threshold);
            JSONObject yawning = attributesArray.getJSONObject("yawning");
            float yawning_threshold = yawning.getFloat("threshold");
            System.out.println("驾驶员打哈欠的概率是: " + yawning_threshold);
            JSONObject head_lowered = attributesArray.getJSONObject("head_lowered");
            float head_lowered_threshold = head_lowered.getFloat("threshold");
            System.out.println("驾驶员低头的概率是: " + head_lowered_threshold);

            HashMap<String,Object> ResultMap = new HashMap<String,Object>();
            ResultMap.put("person_num",personNum);
            ResultMap.put("driver_num",driveNum);
            ResultMap.put("location",locationArray);
            ResultMap.put("both_hands_leaving_wheelFloat",both_hands_leaving_wheelFloat_threshold);
            ResultMap.put("eyes_closed",eyes_closed_threshold);
            ResultMap.put("no_face_mask",no_face_mask_threshold);
            ResultMap.put("not_bucking_up",not_bucking_up_threshold);
            ResultMap.put("smoke",smoke_threshold);
            ResultMap.put("not_facing_front",not_facing_front_threshold);
            ResultMap.put("cellphone",cellphone_threshold);
            ResultMap.put("yawning",yawning_threshold);
            ResultMap.put("head_lowered",head_lowered_threshold);

            JSONObject ResultJson = new JSONObject(ResultMap);
            System.out.println(ResultJson);

            return ResultJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
