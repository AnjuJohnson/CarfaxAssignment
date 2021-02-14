package com.example.carfaxassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carfaxassignment.R
import com.example.carfaxassignment.listener.RecyclerViewClickListener
import com.example.carfaxassignment.model.Car
import kotlinx.android.synthetic.main.item_carlist.view.*


class CarlistAdapter(
    private val context: Context,
    private val mCarlist: List<Car>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<CarlistAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carlist, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.cartitles?.text = mCarlist[position].year + " " + mCarlist[position].make + " " +
                mCarlist[position].model + " " + mCarlist[position].trim
        holder.carotherdetails?.text =
            "$ " + mCarlist[position].currentPrice + "  |  " + mCarlist[position].mileage + " k mi "
        holder.carlocation.text =
            mCarlist[position].dealer?.city + "," + mCarlist[position].dealer?.state


        Glide.with(holder.image)
            .load(mCarlist[position].images?.firstPhoto?.large)
            .into(holder.image)
        holder.callButton.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.callButton, mCarlist[position])

        }
        holder.cardView.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.cardView, mCarlist[position])

        }
    }

    override fun getItemCount(): Int {
        return mCarlist.size
    }

    class QuestionViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        val cartitles = containerView.cartitles
        val carotherdetails = containerView.carotherdetails
        val carlocation = containerView.carlocation
        val callButton = containerView.callButton

        val image = containerView.CarimageView
        val cardView = containerView.carCardview
    }
}