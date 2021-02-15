package com.example.carfaxassignment.view

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor =resources.getColor(R.color.transgrey)
        }
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

        cartitles.text=  car.year + " " + car.make + " " +
                car.model + " " + car.trim
        carotherdetails.text= "$ " + car.currentPrice + "  |  " + car.mileage + " k mi "
        carinfolocation.text= car.dealer?.city + "," + car.dealer?.state

        carinfoExterior.text=car.exteriorColor
        carinfoInterior.text=car.interiorColor
        carinfoDrive.text=car.drivetype
        carinfoTransmission.text=car.transmission
        carinfoBody.text=car.bodytype
        carinfoEngine.text=car.engine
        carinfoFuel.text=car.fuel

        callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${car.dealer?.phone}"))
            startActivity(intent)
        }
    }

}