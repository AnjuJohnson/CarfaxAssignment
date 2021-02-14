package com.example.carfaxassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.carfaxassignment.repository.CarRepository

/*ViewModel factory is to instantiate the ViewModel.
 This prevents the app from crashing in case an activity is not found.*/
@Suppress("UNCHECKED_CAST")
class CarViewmodelFactory(
    private val repository: CarRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarListViewModel(repository) as T
    }

}