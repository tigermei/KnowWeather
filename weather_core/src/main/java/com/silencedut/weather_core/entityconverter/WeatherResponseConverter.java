package com.silencedut.weather_core.entityconverter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.silencedut.weather_core.entity.HeWeatherV7;

import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 天气响应数据转换器
 * 用于将API返回的JSON数据手动转换为HeWeatherV7对象
 */
public class WeatherResponseConverter implements Converter<ResponseBody, HeWeatherV7> {
    private Gson gson = new Gson();
    
    @Override
    public HeWeatherV7 convert(ResponseBody value) throws IOException {
        // 获取原始JSON字符串
        String json = value.string();
        // 打印原始JSON数据，便于调试
        System.out.println("原始JSON数据: " + json);
        
        // 将JSON字符串转换为JsonObject
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        
        HeWeatherV7 response = new HeWeatherV7();
        HeWeatherV7.HeWeather7Bean bean = new HeWeatherV7.HeWeather7Bean();
        
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
            HeWeatherV7.HeWeather7Bean.NowBean nowBean = new HeWeatherV7.HeWeather7Bean.NowBean();
            
            if (nowJson.has("obsTime")) {
                nowBean.setObsTime(nowJson.get("obsTime").getAsString());
            }
            
            if (nowJson.has("temp")) {
                nowBean.setTemp(nowJson.get("temp").getAsString());
            }
            
            if (nowJson.has("feelsLike")) {
                nowBean.setFeelsLike(nowJson.get("feelsLike").getAsString());
            }
            
            if (nowJson.has("icon")) {
                nowBean.setIcon(nowJson.get("icon").getAsString());
            }
            
            if (nowJson.has("text")) {
                nowBean.setText(nowJson.get("text").getAsString());
            }
            
            if (nowJson.has("wind360")) {
                nowBean.setWind360(nowJson.get("wind360").getAsString());
            }
            
            if (nowJson.has("windDir")) {
                nowBean.setWindDir(nowJson.get("windDir").getAsString());
            }
            
            if (nowJson.has("windScale")) {
                nowBean.setWindScale(nowJson.get("windScale").getAsString());
            }
            
            if (nowJson.has("windSpeed")) {
                nowBean.setWindSpeed(nowJson.get("windSpeed").getAsString());
            }
            
            if (nowJson.has("humidity")) {
                nowBean.setHumidity(nowJson.get("humidity").getAsString());
            }
            
            if (nowJson.has("precip")) {
                nowBean.setPrecip(nowJson.get("precip").getAsString());
            }
            
            if (nowJson.has("pressure")) {
                nowBean.setPressure(nowJson.get("pressure").getAsString());
            }
            
            if (nowJson.has("vis")) {
                nowBean.setVis(nowJson.get("vis").getAsString());
            }
            
            if (nowJson.has("cloud")) {
                nowBean.setCloud(nowJson.get("cloud").getAsString());
            }
            
            if (nowJson.has("dew")) {
                nowBean.setDew(nowJson.get("dew").getAsString());
            }
            
            bean.setNow(nowBean);
        }
        
        // 处理refer对象
        if (jsonObject.has("refer") && !jsonObject.get("refer").isJsonNull()) {
            JsonObject referJson = jsonObject.getAsJsonObject("refer");
            HeWeatherV7.HeWeather7Bean.ReferBean referBean = new HeWeatherV7.HeWeather7Bean.ReferBean();
            
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
        
        response.setHeWeather7(bean);
        return response;
    }
}