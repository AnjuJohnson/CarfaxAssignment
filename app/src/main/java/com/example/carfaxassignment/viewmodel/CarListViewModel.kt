package com.example.carfaxassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carfaxassignment.model.Car
import com.example.carfaxassignment.model.Carlist
import com.example.carfaxassignment.repository.CarRepository
import kotlinx.coroutines.*
import net.simplifiedcoding.util.Coroutines

class CarListViewModel(private val repository: CarRepository) : ViewModel() {
    var carlist = MutableLiveData<Carlist>()
    private lateinit var job: Job
    val carlist_data: LiveData<Carlist>
        get() = carlist
     var carlistItem:Carlist?=null

    fun getAllCar(callback: ((List<Car>)->Unit)) {
        CoroutineScope(Dispatchers.IO).launch {
          var list=  repository.getAllCars()

            if (list != null) {
                callback(list)

               /* carlistItem?.let {
                    carlist.postValue(carlistItem)
                }*/
               // carlistItem?.let { callback(it) }
            }
        }

    }
    fun getCarList() {

        job = Coroutines.ioThenMain(
            { repository.fetchQuetionList() },
            {
                carlist.value = it
                it?.listings?.let { c1 ->
                    for (car1 in c1) {
                        savecar(car1)
                    }

                }
            }


        )


        /* GlobalScope.launch(Dispatchers.IO) {
             try {
                 var list = repository.fetchQuetionList() // do on IO thread and back to UI Thread
                 carlist.value = list

                 //insert
                 for (car in list.listings) {
                     repository.insertcar(car = car)
                 }

             } catch (exception: Exception) {
                 Log.d("Exception", "$exception handled !")
             }
         }
 */

    }

    private fun savecar(car: Car) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertcar(car)
        }
    }
}