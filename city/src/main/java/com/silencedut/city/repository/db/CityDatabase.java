package com.silencedut.city.repository.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.silencedut.weather_core.api.cityprovider.City;

/**
 * Created by SilenceDut on 2018/1/5 .
 */

@Database(entities = {City.class}, version = 1,exportSchema = false)
public abstract class CityDatabase extends RoomDatabase {
    public abstract CityDao cityDao();
}
