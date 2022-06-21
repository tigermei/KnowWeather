package com.silencedut.city.repository.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.silencedut.weather_core.api.cityprovider.City;

import java.util.List;

/**
 * Created by SilenceDut on 2018/1/4 .
 */
@Dao
public interface CityDao {
    @Query("SELECT * FROM city ")
    List<City> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCities(List<City> cities);

    @Query("SELECT * FROM city WHERE cityName LIKE :cityName AND country LIKE :country LIMIT 1")
    City searchCity(String cityName,String  country);

    @Query("SELECT * FROM city WHERE cityId LIKE :cityId  LIMIT 1")
    City searchCity(String cityId);

    @Query("SELECT * FROM city WHERE cityId LIKE  :key || '%' OR country LIKE  :key || '%' OR countryEn LIKE  :key || '%' ")
    List<City> matchCity(String key);

}
