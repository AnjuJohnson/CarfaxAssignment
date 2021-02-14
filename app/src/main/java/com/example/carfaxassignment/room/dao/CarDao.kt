package com.example.carfaxassignment.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.carfaxassignment.model.Car

@Dao
interface CarDao {

    @Query("SELECT * FROM car_table")
    suspend fun getAll():List<Car>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertCar(car: Car)

}