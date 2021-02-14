package com.example.carfaxassignment.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.room.TypeConverter
import com.example.carfaxassignment.model.Car
import com.example.carfaxassignment.model.dealer
import com.example.carfaxassignment.model.image

class DealerTypeConverter {
    @TypeConverter
    fun fromImage(value: dealer): String {
        val gson = Gson()
        val type = object : TypeToken<dealer>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toImage(value: String): dealer {
        val gson = Gson()
        val type = object : TypeToken<dealer>() {}.type
        return gson.fromJson(value, type)
    }
}