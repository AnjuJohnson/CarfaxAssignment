package com.example.carfaxassignment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.carfaxassignment.model.Car
import com.example.carfaxassignment.room.dao.CarDao
import com.example.carfaxassignment.util.DealerTypeConverter
import com.example.carfaxassignment.util.TypeConverter


@Database(entities = [Car::class], version = 1)
@TypeConverters(TypeConverter::class,DealerTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao
    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}
