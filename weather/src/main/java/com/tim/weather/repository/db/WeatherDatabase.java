package com.tim.weather.repository.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by SilenceDut on 2018/1/18 .
 */
@Database(entities = {Weather.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
