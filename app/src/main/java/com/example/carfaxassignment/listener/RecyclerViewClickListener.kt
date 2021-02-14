package com.example.carfaxassignment.listener

import android.view.View
import com.example.carfaxassignment.model.Car

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, car: Car)

}