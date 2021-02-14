package com.example.carfaxassignment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.carfaxassignment.R
import com.example.carfaxassignment.model.Car
import com.example.carfaxassignment.util.IntentKeys
import kotlinx.android.synthetic.main.activity_carprofile.*

class CarProfileActivity : AppCompatActivity() {
    private var carItem: Car? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carprofile)
        if (intent.hasExtra(IntentKeys.CAR_ITEM)) {
            carItem =
                intent.getSerializableExtra(IntentKeys.CAR_ITEM) as Car?
            carItem?.let { loadCardUi(it) }
        } else {

            finish()
        }
    }
    private fun loadCardUi(car:Car){
        Glide.with(CarimageView)
            .load(car.images?.firstPhoto?.large)
            .into(CarimageView)

        yearText.text=car.year
        makeTextview.text=car.make
        modelTextview.text=car.model
    }

}