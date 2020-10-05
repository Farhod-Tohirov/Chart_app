package com.star.chartapplesson15.ui.screen.main

import com.star.chartapplesson15.data.local.entity.*
import com.star.chartapplesson15.model.AgeCount
import com.star.chartapplesson15.model.ManufacturerCount
import com.star.chartapplesson15.model.ResponseData
import com.star.chartapplesson15.model.ValueData

interface Contract {
    interface Model {
        fun getBalance(block: (ResponseData<List<ValueData>>) -> Unit)
        fun getTasks(block: (ResponseData<List<ValueData>>) -> Unit)
        fun getUsers(block: (ResponseData<List<UsersData>>) -> Unit)
        fun getWorkers(block: (ResponseData<List<ValueData>>) -> Unit)
        fun getProducts(block: (ResponseData<List<ProductData>>) -> Unit)

        fun getAllBalance(block: (ResponseData<List<ValueData>>) -> Unit)
        fun getAllTasks(block: (ResponseData<List<ValueData>>) -> Unit)
        fun getAllUsers(block: (ResponseData<List<UsersData>>) -> Unit)
        fun getAllWorkers(block: (ResponseData<List<ValueData>>) -> Unit)
        fun getAllProducts(block: (ResponseData<List<ProductData>>) -> Unit)

        fun saveBalanceToDB(ls: List<BalanceData>)
        fun saveTasksToDB(ls: List<TasksData>)
        fun saveUsersToDB(ls: List<UsersData>)
        fun saveWorkersToDB(ls: List<WorkersData>)
        fun saveProductsToDB(ls: List<ProductData>)

        fun getBalanceFromDB(block: (List<BalanceData>) -> Unit)
        fun getTasksFromDB(block: (List<TasksData>) -> Unit)
        fun getUsersFromDB(block: (List<UsersData>) -> Unit)
        fun getWorkersFromDB(block: (List<WorkersData>) -> Unit)
        fun getProductsFromDB(block: (List<ProductData>) -> Unit)
    }

    interface View {
        fun loadMoneyChart(ls: List<Float>)
        fun loadBalanceChart(ls: List<ValueData>)
        fun loadTasksChart(ls: List<ValueData>)
        fun loadUsersChart(ls: List<AgeCount>)
        fun loadProductsChart(ls: List<ManufacturerCount>)
        fun showMessage(text: String)
        fun hideBalanceChartLoader()
        fun hideTasksChartLoader()
        fun hideUsersChartLoader()
        fun loadBalanceDialog(balance: List<ValueData>)
        fun loadMoneyDialog(ls: List<Float>)
        fun loadTasksDialog(tasks: List<ValueData>)
        fun loadUsersDialog(users: List<UsersData>)
        fun loadProductDialog(products: List<ProductData>)
        fun loadWorkersChart(workers: List<ValueData>)
        fun hideWorkersChartProgress()
        fun hideMoneyChartDialog()
        fun loadWorkersDialog(workers: List<ValueData>)
        fun makeLoadingVisible(status: Boolean)
    }

    interface Presenter {
        fun init()
        fun clickMoneyChart()
        fun clickTaskChart()
        fun clickBalanceChart()
        fun clickUserChart()
        fun clickProductChart()
        fun clickWorkersChart()
    }
}