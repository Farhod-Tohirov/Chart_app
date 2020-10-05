package com.star.chartapplesson15.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.star.chartapplesson15.R
import com.star.chartapplesson15.model.ValueData
import com.star.chartapplesson15.utils.inflate
import kotlinx.android.synthetic.main.value_item.view.*

class FloatAdapter : RecyclerView.Adapter<FloatAdapter.ViewHolder>() {

    val ls = ArrayList<Float>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            itemView.textValue.text = "${adapterPosition + 1}. Value = " + ls[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.value_item))
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()
    override fun getItemCount(): Int = ls.size

    fun submitList(ls: List<Float>) {
        this.ls.clear()
        this.ls.addAll(ls)
        notifyDataSetChanged()
    }

}