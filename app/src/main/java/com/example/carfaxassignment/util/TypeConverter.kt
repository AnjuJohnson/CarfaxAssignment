package com.example.carfaxassignment.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.room.TypeConverter
import com.example.carfaxassignment.model.Car
import com.example.carfaxassignment.model.image

class TypeConverter {
    @TypeConverter
    fun fromImage(value: image): String {
        val gson = Gson()
        val type = object : TypeToken<image>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toImage(value: String): image {
        val gson = Gson()
        val type = object : TypeToken<image>() {}.type
        return gson.fromJson(value, type)
    }
}