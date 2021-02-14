package com.example.carfaxassignment.api

import com.example.carfaxassignment.model.Carlist
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {
    @GET("assignment.json")
   suspend fun fetchQuestions(): Response<Carlist>

    companion object{
        operator fun invoke() : CarApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://carfax-for-consumers.firebaseio.com/")
                .build()
                .create(CarApi::class.java)
        }
    }

}