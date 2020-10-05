package com.star.chartapplesson15.ui.screen.main

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.Utils
import com.star.chartapplesson15.R
import com.star.chartapplesson15.data.local.entity.ProductData
import com.star.chartapplesson15.data.local.entity.UsersData
import com.star.chartapplesson15.model.AgeCount
import com.star.chartapplesson15.model.ManufacturerCount
import com.star.chartapplesson15.model.ValueData
import com.star.chartapplesson15.ui.dialog.ShowFloatDialog
import com.star.chartapplesson15.ui.dialog.ShowProductDialog
import com.star.chartapplesson15.ui.dialog.ShowUserDialog
import com.star.chartapplesson15.ui.dialog.ShowValueDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Contract.View {

    private lateinit var presenter: Contract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        presenter = Presenter(this, Model())
        presenter.init()
        loadViews()
    }

    private fun loadViews() {
        moneyChart.setOnClickListener { presenter.clickMoneyChart() }
        balanceChart.setOnClickListener { presenter.clickBalanceChart() }
        taskChart.setOnClickListener { presenter.clickTaskChart() }
        workersChart.setOnClickListener { presenter.clickWorkersChart() }
        pieAge.setTouchEnabled(false)
        pieAge.setOnClickListener { presenter.clickUserChart() }
        productChart.setTouchEnabled(false)
        productChart.setOnClickListener { presenter.clickProductChart() }
    }

    override fun makeLoadingVisible(status: Boolean) {
        if (status) loading.visibility = View.VISIBLE else loading.visibility = View.GONE
    }

    override fun loadMoneyChart(ls: List<Float>) {
        val list = ArrayList<Entry>()
        for (i in ls.indices) {
            list.add(Entry(i.toFloat(), ls[i]))
        }

        moneyChart.setNoDataText("Please wait")
        moneyChart.isAutoScaleMinMaxEnabled = true
        moneyChart.legend.isEnabled = true
        moneyChart.animateX(1500)

        val lineDataSet = LineDataSet(list, "Daily Money")
        val lineData = LineData(lineDataSet)

        lineDataSet.setDrawFilled(true)
        if (Utils.getSDKInt() >= 18) {
            // drawables only supported on api level 18 and above
            val drawable = ContextCompat.getDrawable(this, R.drawable.color_back_line)
            lineDataSet.fillDrawable = drawable
        } else {
            lineDataSet.fillColor = Color.BLACK
        }
        lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER

        lineDataSet.lineWidth = 9f
        lineDataSet.setDrawValues(false)

        lineDataSet.setDrawCircles(true)

        val paint = moneyChart.renderer.paintRender
        val height = moneyChart.height.toFloat()
        val width = moneyChart.width.toFloat()

        val lindGrad = LinearGradient(0f, 0f, width, height, Color.BLUE, Color.parseColor("#E45CF4"), Shader.TileMode.REPEAT)
        paint.shader = lindGrad

        moneyChart.axisRight.isEnabled = false
        moneyChart.axisRight.setDrawLabels(false)
        moneyChart.axisRight.setDrawAxisLine(false)
        moneyChart.axisRight.setDrawGridLines(false)

        moneyChart.description.isEnabled = false

        moneyChart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        moneyChart.data = lineData
        moneyChart.invalidate()
        moneyChart.resetZoom()
    }

    override fun loadBalanceChart(ls: List<ValueData>) {
        val list = ArrayList<Entry>()
        var max = 0
        for (i in ls.indices) {
            list.add(Entry(i.toFloat(), ls[i].value.toFloat()))
            if (ls[i].value > max) max = ls[i].value
        }

        balanceText.text = max.toString()
        balanceChart.setNoDataText("Please wait")
        balanceChart.isAutoScaleMinMaxEnabled = true
        balanceChart.legend.isEnabled = true
        balanceChart.animateX(1500)

        val lineDataSet = LineDataSet(list, "Balance")
        val lineData = LineData(lineDataSet)

        lineDataSet.setColor(Color.RED)
        lineDataSet.lineWidth = 4f
        lineDataSet.setDrawValues(false)


        lineDataSet.setDrawCircles(false)

        balanceChart.xAxis.isEnabled = false

        balanceChart.axisRight.isEnabled = false
        balanceChart.axisRight.setDrawLabels(false)
        balanceChart.axisRight.setDrawAxisLine(false)
        balanceChart.axisRight.setDrawGridLines(false)

        balanceChart.axisLeft.isEnabled = false
        balanceChart.axisLeft.setDrawLabels(false)
        balanceChart.axisLeft.setDrawAxisLine(false)
        balanceChart.axisLeft.setDrawGridLines(false)

        balanceChart.description.isEnabled = false

        balanceChart.data = lineData
        balanceChart.resetZoom()
    }


    override fun loadTasksChart(ls: List<ValueData>) {
        val list = ArrayList<Entry>()
        var max = 0
        for (i in ls.indices) {
            list.add(Entry(i.toFloat(), ls[i].value.toFloat()))
            if (ls[i].value > max) max = ls[i].value
        }

        maxTaskText.text = max.toString()
        taskChart.setNoDataText("Please wait")
        taskChart.isAutoScaleMinMaxEnabled = true
        taskChart.legend.isEnabled = true
        taskChart.animateX(1500)

        val lineDataSet = LineDataSet(list, "Tasks")
        val lineData = LineData(lineDataSet)

        lineDataSet.setColor(Color.BLUE)
        lineDataSet.lineWidth = 4f
        lineDataSet.setDrawValues(false)


        lineDataSet.setDrawCircles(false)

        taskChart.axisRight.isEnabled = false

        taskChart.axisRight.setDrawLabels(false)
        taskChart.axisRight.setDrawAxisLine(false)
        taskChart.axisRight.setDrawGridLines(false)
        taskChart.description.isEnabled = false

        taskChart.xAxis.isEnabled = false

        taskChart.axisLeft.setDrawLabels(false)
        taskChart.axisLeft.setDrawAxisLine(false)
        taskChart.axisLeft.setDrawGridLines(false)
        taskChart.description.isEnabled = false

        taskChart.data = lineData
        taskChart.resetZoom()
    }

    override fun loadWorkersChart(workers: List<ValueData>) {
        val list = ArrayList<Entry>()
        var max = 0
        for (i in workers.indices) {
            list.add(Entry(i.toFloat(), workers[i].value.toFloat()))
            if (workers[i].value > max) max = workers[i].value
        }

        maxWorkerText.text = max.toString()
        workersChart.setNoDataText("Please wait")
        workersChart.isAutoScaleMinMaxEnabled = true
        workersChart.legend.isEnabled = true
        workersChart.animateX(1500)

        val lineDataSet = LineDataSet(list, "Tasks")
        val lineData = LineData(lineDataSet)

        lineDataSet.setColor(Color.GREEN)
        lineDataSet.lineWidth = 4f
        lineDataSet.setDrawValues(false)


        lineDataSet.setDrawCircles(false)

        workersChart.axisRight.isEnabled = false

        workersChart.axisRight.setDrawLabels(false)
        workersChart.axisRight.setDrawAxisLine(false)
        workersChart.axisRight.setDrawGridLines(false)
        workersChart.description.isEnabled = false

        workersChart.xAxis.isEnabled = false

        workersChart.axisLeft.setDrawLabels(false)
        workersChart.axisLeft.setDrawAxisLine(false)
        workersChart.axisLeft.setDrawGridLines(false)
        workersChart.description.isEnabled = false

        workersChart.data = lineData
        workersChart.resetZoom()
    }

    override fun loadUsersChart(ls: List<AgeCount>) {
        pieAge.setUsePercentValues(true)
        pieAge.description.isEnabled = false

        pieAge.legend.isEnabled = false
        pieAge.animateY(1500)

        pieAge.isDrawHoleEnabled = true
        pieAge.setHoleColor(Color.WHITE)

        val list = ArrayList<PieEntry>()
        var max = 0
        var centerText = ""
        for (age in ls) {
            list.add(PieEntry(age.count.toFloat(), age.age.toString()))
            if (age.age > max) {
                max = age.age
            }
        }
        pieAge.centerText = max.toString()
        pieAge.setCenterTextSize(18f)
        pieAge.setCenterTextColor(Color.BLACK)
        val pieDataSet = PieDataSet(list, "Ages")
        pieDataSet.setAutomaticallyDisableSliceSpacing(true)
        pieDataSet.isUsingSliceColorAsValueLineColor = true
        pieDataSet.setColors(*ColorTemplate.JOYFUL_COLORS)
        val pieData = PieData(pieDataSet)
        pieAge.data = pieData
        pieAge.setDrawEntryLabels(false)
        pieAge.invalidate()


    }


    override fun loadProductsChart(ls: List<ManufacturerCount>) {
        productChart.setUsePercentValues(true)
        productChart.description.isEnabled = false
        productChart.animateY(1500)
        productChart.legend.isEnabled = false

        var centerText = ""
        var max = 0

        productChart.isDrawHoleEnabled = true
        productChart.setHoleColor(Color.WHITE)

        val list = ArrayList<PieEntry>()
        for (product in ls) {
            list.add(PieEntry(product.count.toFloat(), product.manufacturer))
            if (product.count > max) {
                max = product.count
                centerText = product.manufacturer
            }
        }
        productChart.centerText = centerText
        productChart.setCenterTextSize(18f)
        productChart.setCenterTextColor(Color.BLACK)
        val pieDataSet = PieDataSet(list, "Ages")
        pieDataSet.setAutomaticallyDisableSliceSpacing(true)
        pieDataSet.isUsingSliceColorAsValueLineColor = true
        pieDataSet.setColors(*ColorTemplate.JOYFUL_COLORS)
        val pieData = PieData(pieDataSet)
        productChart.data = pieData
        productChart.setDrawEntryLabels(false)

        productChart.invalidate()
    }

    override fun showMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun hideBalanceChartLoader() {
        balanceChartProgressBar.visibility = View.INVISIBLE
    }

    override fun hideTasksChartLoader() {
        taskChartProgressBar.visibility = View.INVISIBLE
    }

    override fun hideWorkersChartProgress() {
        workersChartProgress.visibility = View.INVISIBLE
    }

    override fun hideMoneyChartDialog() {
        moneyChartProgressBar.visibility = View.INVISIBLE
    }

    override fun hideUsersChartLoader() {
        usersChartProgressBar.visibility = View.INVISIBLE
    }


    override fun loadMoneyDialog(ls: List<Float>) {
        val dialog = ShowFloatDialog(this, "Daily Income")
        dialog.setValueData(ls)
        dialog.show()
    }

    override fun loadBalanceDialog(balance: List<ValueData>) {
        val dialog = ShowValueDialog(this, "Balance")
        dialog.setValueData(balance)
        dialog.show()
    }

    override fun loadTasksDialog(tasks: List<ValueData>) {
        val dialog = ShowValueDialog(this, "Tasks")
        dialog.setValueData(tasks)
        dialog.show()
    }

    override fun loadUsersDialog(users: List<UsersData>) {
        val dialog = ShowUserDialog(this, "User Data")
        dialog.setValueData(users)
        dialog.show()
    }

    override fun loadProductDialog(products: List<ProductData>) {
        val dialog = ShowProductDialog(this, "Product Data")
        dialog.setValueData(products)
        dialog.show()
    }

    override fun loadWorkersDialog(workers: List<ValueData>) {
        val dialog = ShowValueDialog(this, "Workers")
        dialog.setValueData(workers)
        dialog.show()
    }
}