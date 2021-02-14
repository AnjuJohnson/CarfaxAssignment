package com.example.carfaxassignment.repository

import android.app.Application
import com.example.carfaxassignment.api.CarApi
import com.example.carfaxassignment.api.SafeApiRequest
import com.example.carfaxassignment.model.Car
import com.example.carfaxassignment.room.AppDatabase
import com.example.carfaxassignment.room.dao.CarDao
import kotlinx.coroutines.CoroutineScope


class CarRepository(
    private val api: CarApi,
    private val application: Application
) : SafeApiRequest() {
    private var car_Dao: CarDao?

    init {
        val db = AppDatabase.getAppDataBase(application)
        car_Dao = db?.carDao()
    }

    suspend fun fetchQuetionList() = apiRequest { api.fetchQuestions() }

    suspend fun insertcar(car: Car) {
        car_Dao?.insertCar(car)
    }

    suspend fun getAllCars()= car_Dao?.getAll()

}