package com.silencedut.weather_core.repository;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.annotation.MainThread;

import com.silencedut.weather_core.CoreManager;

/**
 * Created by SilenceDut on 2018/1/15 .
 */

public class DBHelper {


    @MainThread
    public static <T extends RoomDatabase >  T provider(Class<T> dbCls , String dbName) {
         /*
        使用fallbackToDestructiveMigration暴力升级数据库，
        更多升级方式 see{@link # http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0728/8278.html}
         */
        return Room.databaseBuilder(CoreManager.getContext(),
                dbCls, dbName).fallbackToDestructiveMigration().build();
    }
}
