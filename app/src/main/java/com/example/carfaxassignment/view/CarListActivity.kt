package com.example.carfaxassignment.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carfaxassignment.R
import com.example.carfaxassignment.adapter.CarlistAdapter
import com.example.carfaxassignment.api.CarApi
import com.example.carfaxassignment.listener.RecyclerViewClickListener
import com.example.carfaxassignment.model.Car
import com.example.carfaxassignment.repository.CarRepository
import com.example.carfaxassignment.util.IntentKeys
import com.example.carfaxassignment.viewmodel.CarListViewModel
import com.example.carfaxassignment.viewmodel.CarViewmodelFactory
import kotlinx.android.synthetic.main.activity_carlist.*
import net.simplifiedcoding.util.Coroutines

class CarListActivity : AppCompatActivity(), RecyclerViewClickListener {
    private lateinit var factory: CarViewmodelFactory
    private lateinit var viewModel: CarListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carlist)

        val api = CarApi()
        val repository = CarRepository(api, application)
        factory = CarViewmodelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(CarListViewModel::class.java)

        //  viewModel.getCarList()
        try {
            viewModel.getAllCar {
                if (it.isEmpty()) {
                    if(Coroutines.isNetworkAvailable(this)) viewModel.getCarList() else {
                        runOnUiThread {
                            Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                    } else {
                    setadapter(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        viewModel.carlist_data.observe(this, Observer { cars ->
            setadapter(cars.listings)
        })
    }

    private fun setadapter(carlist: List<Car>) {
        runOnUiThread {
            recycler_view_car.also {
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter = CarlistAdapter(this, carlist, this)
            }
        }

    }

    override fun onRecyclerViewItemClick(view: View, car: Car) {
        when (view.id) {
            R.id.callButton -> {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${car.dealer?.phone}"))
                startActivity(intent)
            }
            R.id.carCardview -> {
                val intent = Intent(this, CarProfileActivity::class.java)
                intent.putExtra(IntentKeys.CAR_ITEM, car)
                startActivity(intent)
            }

        }
    }


}