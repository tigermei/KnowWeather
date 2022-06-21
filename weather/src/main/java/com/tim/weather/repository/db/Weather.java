package com.tim.weather.repository.db;

import androidx.room.Entity;
import androidx.annotation.NonNull;

/**
 * Created by SilenceDut on 2018/1/15 .
 */
@Entity(tableName = "weather",primaryKeys = {"cityId"})
public class Weather {

    @NonNull
    public String cityId ="";
    public String weatherJson;

}
