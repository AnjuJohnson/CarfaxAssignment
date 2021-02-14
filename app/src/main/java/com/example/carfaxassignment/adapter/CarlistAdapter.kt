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


class CarlistAdapter (
    private val context: Context,
    private val mCarlist: List<Car>,
    private val listener: RecyclerViewClickListener
)
    : RecyclerView.Adapter<CarlistAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carlist, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.year?.text = mCarlist[position].year
        holder.make?.text = mCarlist[position].make
        holder.model?.text = mCarlist[position].model
        Glide.with(holder.image)
            .load(mCarlist[position].images?.firstPhoto?.medium)
            .into(holder.image)
        holder.model.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.model, mCarlist[position])

        }
    }

    override fun getItemCount(): Int {
        return mCarlist.size
    }

    class QuestionViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {
        val year = containerView.yearText;
        val make = containerView.makeTextview;
        val model = containerView.modelTextview;
        val image = containerView.CarimageView;
    }
}