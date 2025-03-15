package com.silencedut.weather_core.entityconverter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.silencedut.weather_core.entity.AqiEntityV7;

import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 空气质量响应数据转换器
 * 用于将API返回的JSON数据手动转换为AqiEntityV7对象
 */
public class AqiResponseConverter implements Converter<ResponseBody, AqiEntityV7> {
    private Gson gson = new Gson();
    
    @Override
    public AqiEntityV7 convert(ResponseBody value) throws IOException {
        // 获取原始JSON字符串
        String json = value.string();
        // 打印原始JSON数据，便于调试
        System.out.println("空气质量原始JSON数据: " + json);
        
        // 将JSON字符串转换为JsonObject
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        
        AqiEntityV7 response = new AqiEntityV7();
        AqiEntityV7.AqiV7Bean bean = new AqiEntityV7.AqiV7Bean();
        
        // 设置基本字段
        if (jsonObject.has("code")) {
            bean.setCode(jsonObject.get("code").getAsString());
        }
        
        if (jsonObject.has("updateTime")) {
            bean.setUpdateTime(jsonObject.get("updateTime").getAsString());
        }
        
        if (jsonObject.has("fxLink")) {
            bean.setFxLink(jsonObject.get("fxLink").getAsString());
        }
        
        // 处理now对象
        if (jsonObject.has("now") && !jsonObject.get("now").isJsonNull()) {
            JsonObject nowJson = jsonObject.getAsJsonObject("now");
            AqiEntityV7.AqiV7Bean.NowBean nowBean = new AqiEntityV7.AqiV7Bean.NowBean();
            
            if (nowJson.has("pubTime")) {
                nowBean.setPubTime(nowJson.get("pubTime").getAsString());
            }
            
            if (nowJson.has("aqi")) {
                nowBean.setAqi(nowJson.get("aqi").getAsString());
            }
            
            if (nowJson.has("level")) {
                nowBean.setLevel(nowJson.get("level").getAsString());
            }
            
            if (nowJson.has("category")) {
                nowBean.setCategory(nowJson.get("category").getAsString());
            }
            
            if (nowJson.has("primary")) {
                nowBean.setPrimary(nowJson.get("primary").getAsString());
            }
            
            if (nowJson.has("pm10")) {
                nowBean.setPm10(nowJson.get("pm10").getAsString());
            }
            
            if (nowJson.has("pm2p5")) {
                nowBean.setPm2p5(nowJson.get("pm2p5").getAsString());
            }
            
            if (nowJson.has("no2")) {
                nowBean.setNo2(nowJson.get("no2").getAsString());
            }
            
            if (nowJson.has("so2")) {
                nowBean.setSo2(nowJson.get("so2").getAsString());
            }
            
            if (nowJson.has("co")) {
                nowBean.setCo(nowJson.get("co").getAsString());
            }
            
            if (nowJson.has("o3")) {
                nowBean.setO3(nowJson.get("o3").getAsString());
            }
            
            bean.setNow(nowBean);
        }
        
        // 处理station数组
        if (jsonObject.has("station") && !jsonObject.get("station").isJsonNull()) {
            JsonArray stationArray = jsonObject.getAsJsonArray("station");
            List<AqiEntityV7.AqiV7Bean.StationBean> stationList = new ArrayList<>();
            
            for (JsonElement element : stationArray) {
                if (!element.isJsonNull()) {
                    JsonObject stationJson = element.getAsJsonObject();
                    AqiEntityV7.AqiV7Bean.StationBean stationBean = new AqiEntityV7.AqiV7Bean.StationBean();
                    
                    if (stationJson.has("pubTime")) {
                        stationBean.setPubTime(stationJson.get("pubTime").getAsString());
                    }
                    
                    if (stationJson.has("name")) {
                        stationBean.setName(stationJson.get("name").getAsString());
                    }
                    
                    if (stationJson.has("id")) {
                        stationBean.setId(stationJson.get("id").getAsString());
                    }
                    
                    if (stationJson.has("aqi")) {
                        stationBean.setAqi(stationJson.get("aqi").getAsString());
                    }
                    
                    if (stationJson.has("level")) {
                        stationBean.setLevel(stationJson.get("level").getAsString());
                    }
                    
                    if (stationJson.has("category")) {
                        stationBean.setCategory(stationJson.get("category").getAsString());
                    }
                    
                    if (stationJson.has("primary")) {
                        stationBean.setPrimary(stationJson.get("primary").getAsString());
                    }
                    
                    if (stationJson.has("pm10")) {
                        stationBean.setPm10(stationJson.get("pm10").getAsString());
                    }
                    
                    if (stationJson.has("pm2p5")) {
                        stationBean.setPm2p5(stationJson.get("pm2p5").getAsString());
                    }
                    
                    if (stationJson.has("no2")) {
                        stationBean.setNo2(stationJson.get("no2").getAsString());
                    }
                    
                    if (stationJson.has("so2")) {
                        stationBean.setSo2(stationJson.get("so2").getAsString());
                    }
                    
                    if (stationJson.has("co")) {
                        stationBean.setCo(stationJson.get("co").getAsString());
                    }
                    
                    if (stationJson.has("o3")) {
                        stationBean.setO3(stationJson.get("o3").getAsString());
                    }
                    
                    stationList.add(stationBean);
                }
            }
            
            bean.setStation(stationList);
        }
        
        // 处理refer对象
        if (jsonObject.has("refer") && !jsonObject.get("refer").isJsonNull()) {
            JsonObject referJson = jsonObject.getAsJsonObject("refer");
            AqiEntityV7.AqiV7Bean.ReferBean referBean = new AqiEntityV7.AqiV7Bean.ReferBean();
            
            // 处理sources数组
            if (referJson.has("sources") && !referJson.get("sources").isJsonNull()) {
                JsonArray sourcesArray = referJson.getAsJsonArray("sources");
                List<String> sourcesList = new ArrayList<>();
                
                for (JsonElement element : sourcesArray) {
                    sourcesList.add(element.getAsString());
                }
                
                referBean.setSources(sourcesList);
            }
            
            // 处理license数组
            if (referJson.has("license") && !referJson.get("license").isJsonNull()) {
                JsonArray licenseArray = referJson.getAsJsonArray("license");
                List<String> licenseList = new ArrayList<>();
                
                for (JsonElement element : licenseArray) {
                    licenseList.add(element.getAsString());
                }
                
                referBean.setLicense(licenseList);
            }
            
            bean.setRefer(referBean);
        }
        
        response.setAqiV7(bean);
        return response;
    }
}