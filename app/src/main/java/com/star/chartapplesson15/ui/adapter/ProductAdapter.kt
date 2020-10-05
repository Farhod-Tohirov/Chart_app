package com.star.chartapplesson15.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.star.chartapplesson15.R
import com.star.chartapplesson15.data.local.entity.ProductData
import com.star.chartapplesson15.utils.inflate
import kotlinx.android.synthetic.main.product_item.view.*

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    private val ls = ArrayList<ProductData>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            val data = ls[adapterPosition]
            itemView.number.text = (adapterPosition+ 1).toString()
            itemView.productManufacturer.text = data.manufacturer
            itemView.productName.text = "Name: ${data.modelName}"
            itemView.productSerial.text = "Serial: ${data.serial}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.product_item))
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()
    override fun getItemCount(): Int = ls.size

    fun submitList(ls: List<ProductData>) {
        this.ls.clear()
        this.ls.addAll(ls)
        notifyDataSetChanged()
    }


}