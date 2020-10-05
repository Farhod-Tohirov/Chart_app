package com.star.chartapplesson15.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.star.chartapplesson15.R
import com.star.chartapplesson15.data.local.entity.ProductData
import com.star.chartapplesson15.data.local.entity.UsersData
import com.star.chartapplesson15.model.ValueData
import com.star.chartapplesson15.utils.inflate
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    private val ls = ArrayList<UsersData>()

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(){
            val data = ls[adapterPosition]
            itemView.userAge.text = "${data.age} years old"
            itemView.userFirstName.text = data.firstName
            itemView.userLastName.text = data.lastName
            itemView.number.text = "${adapterPosition + 1}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.user_item))
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()
    override fun getItemCount(): Int = ls.size

    fun submitList(ls: List<UsersData>) {
        this.ls.clear()
        this.ls.addAll(ls)
        notifyDataSetChanged()
    }

}