package com.star.chartapplesson15.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.star.chartapplesson15.R
import com.star.chartapplesson15.model.ValueData
import com.star.chartapplesson15.ui.adapter.FloatAdapter
import com.star.chartapplesson15.ui.adapter.ValueAdapter
import kotlinx.android.synthetic.main.dialog.view.*

class ShowFloatDialog(context: Context, status: String) : AlertDialog(context) {
    private val view = LayoutInflater.from(context).inflate(R.layout.dialog, null, false)
    private val adapter = FloatAdapter()

    init {
        setView(view)
        view.statusText.text = status
        view.closeButton.setOnClickListener { dismiss() }
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.attributes?.windowAnimations = R.style.Animation_Design_BottomSheetDialog
        view.list.adapter = adapter
        view.list.layoutManager = LinearLayoutManager(context)
    }

    fun setValueData(ls: List<Float>) {
        adapter.submitList(ls)
    }
}