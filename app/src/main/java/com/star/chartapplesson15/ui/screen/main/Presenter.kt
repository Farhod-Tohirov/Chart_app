package com.star.chartapplesson15.ui.screen.main

import com.star.chartapplesson15.data.local.entity.BalanceData
import com.star.chartapplesson15.data.local.entity.TasksData
import com.star.chartapplesson15.data.local.entity.WorkersData
import com.star.chartapplesson15.model.AgeCount
import com.star.chartapplesson15.model.ManufacturerCount
import com.star.chartapplesson15.model.ValueData

class Presenter(private val view: Contract.View, private val model: Contract.Model) :
    Contract.Presenter {

    private var balance = ArrayList<ValueData>()
    private var tasks = ArrayList<ValueData>()

    override fun init() {
        model.getBalance {
            view.hideBalanceChartLoader()
            if (it.status == "FAILURE") {
                view.hideBalanceChartLoader()
                view.showMessage(it.message)
                model.getBalanceFromDB {
                    val list = ArrayList(it)
                    val ls = ArrayList<ValueData>()
                    list.forEach {
                        ls.add(ValueData(it.value))
                    }
                    this.balance = ArrayList(ls)
                    view.loadBalanceChart(ls)
                }
            } else {
                val ls = ArrayList<BalanceData>()
                it.data?.forEach { valueData ->
                    ls.add(BalanceData(value = valueData.value))
                }
                model.saveBalanceToDB(ls)
                this.balance = ArrayList(it.data ?: emptyList())
                view.loadBalanceChart(it.data ?: emptyList())
            }


            model.getTasks {
                view.hideTasksChartLoader()
                if (it.status == "FAILURE") {
                    view.showMessage(it.message)
                    model.getTasksFromDB {
                        val list = ArrayList(it)
                        val ls = ArrayList<ValueData>()
                        list.forEach {
                            ls.add(ValueData(it.value))
                        }
                        this.tasks = ArrayList(ls)
                        view.loadTasksChart(ls)
                    }
                } else {
                    val tasks = it.data ?: emptyList()
                    val taskList = ArrayList<TasksData>()
                    tasks.forEach { valueData ->
                        taskList.add(TasksData(value = valueData.value))
                    }
                    this.tasks = ArrayList(tasks)
                    model.saveTasksToDB(taskList)
                    view.loadTasksChart(tasks)
                }



                model.getUsers {
                    view.hideUsersChartLoader()
                    if (it.status == "FAILURE") {
                        view.showMessage(it.message)
                        model.getUsersFromDB {
                            val users = it
                            val list = ArrayList<AgeCount>()
                            for (user in users) {
                                var isThere = false
                                for (i in list)
                                    if (user.age == i.age) {
                                        i.count++
                                        isThere = true
                                        break
                                    }
                                if (!isThere) list.add(AgeCount(user.age, 1))
                            }
                            view.loadUsersChart(list)
                        }
                    } else {
                        val users = it.data ?: emptyList()
                        val list = ArrayList<AgeCount>()
                        for (user in users) {
                            var isThere = false
                            for (i in list)
                                if (user.age == i.age) {
                                    i.count++
                                    isThere = true
                                    break
                                }
                            if (!isThere) list.add(AgeCount(user.age, 1))
                        }
                        model.saveUsersToDB(users)
                        view.loadUsersChart(list)
                    }


                    model.getProducts {
                        view.hideUsersChartLoader()
                        if (it.status == "FAILURE") {
                            view.showMessage(it.message)
                            model.getProductsFromDB {
                                val products = it
                                val list = ArrayList<ManufacturerCount>()
                                for (product in products) {
                                    var isThere = false
                                    for (i in list)
                                        if (product.manufacturer == i.manufacturer) {
                                            i.count++
                                            isThere = true
                                            break
                                        }
                                    if (!isThere) list.add(ManufacturerCount(product.manufacturer, 1))
                                }
                                view.loadProductsChart(list)
                            }
                        } else {
                            val products = it.data ?: emptyList()
                            val list = ArrayList<ManufacturerCount>()
                            for (product in products) {
                                var isThere = false
                                for (i in list)
                                    if (product.manufacturer == i.manufacturer) {
                                        i.count++
                                        isThere = true
                                        break
                                    }
                                if (!isThere) list.add(ManufacturerCount(product.manufacturer, 1))
                            }
                            model.saveProductsToDB(products)
                            view.loadProductsChart(list)
                        }



                        model.getWorkers {
                            view.hideWorkersChartProgress()
                            val list = ArrayList<Float>()
                            if (it.status == "FAILURE") {
                                view.showMessage(it.message)
                                model.getWorkersFromDB { workers ->
                                    val ls = ArrayList<ValueData>()
                                    workers.forEach { ls.add(ValueData(it.value)) }
                                    view.loadWorkersChart(ls)
                                    for (i in workers.indices) {
                                        if (i > tasks.size || i > balance.size) break
                                        val taskForPerson = tasks[i].value.toFloat() / workers[i].value.toFloat()
                                        val moneyForTask = balance[i].value.toFloat() / tasks[i].value.toFloat()
                                        val dailyMoney = taskForPerson * moneyForTask
                                        list.add(dailyMoney)
                                    }
                                    view.hideMoneyChartDialog()
                                    view.loadMoneyChart(list)
                                }
                            } else {
                                val workers = it.data ?: emptyList()
                                view.loadWorkersChart(workers)
                                for (i in workers.indices) {
                                    if (i < tasks.size && i < balance.size) {
                                        val taskForPerson = tasks[i].value.toFloat() / workers[i].value.toFloat()
                                        val moneyForTask = balance[i].value.toFloat() / tasks[i].value.toFloat()
                                        val dailyMoney = taskForPerson * moneyForTask
                                        list.add(dailyMoney)
                                    }
                                }
                                val workerDataList = ArrayList<WorkersData>()
                                workers.forEach { valueData ->
                                    workerDataList.add(WorkersData(value = valueData.value))
                                }
                                model.saveWorkersToDB(workerDataList)
                                view.hideMoneyChartDialog()
                                view.loadMoneyChart(list)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun clickMoneyChart() {
        view.makeLoadingVisible(true)
        val list = ArrayList<Float>()
        model.getAllWorkers { data ->
            if (data.status == "FAILURE") {
                view.showMessage(data.message)
                view.makeLoadingVisible(false)
                return@getAllWorkers
            }
            val workers = ArrayList(data.data ?: emptyList())
            model.getAllTasks { taskData ->
                if (taskData.status == "FAILURE") {
                    view.showMessage(taskData.message)
                    view.makeLoadingVisible(false)
                    return@getAllTasks
                }
                val tasks = ArrayList(taskData.data ?: emptyList())
                model.getAllBalance { balanceData ->
                    if (balanceData.status == "FAILURE") {
                        view.showMessage(balanceData.message)
                        view.makeLoadingVisible(false)
                        return@getAllBalance
                    }
                    val balance = ArrayList(balanceData.data ?: emptyList())
                    for (i in workers.indices) {
                        if (i < tasks.size && i < balance.size) {
                            val taskForPerson = tasks[i].value.toFloat() / workers[i].value.toFloat()
                            val moneyForTask = balance[i].value.toFloat() / tasks[i].value.toFloat()
                            val dailyMoney = taskForPerson * moneyForTask
                            list.add(dailyMoney)
                        }
                    }
                    view.makeLoadingVisible(false)
                    view.loadMoneyDialog(list)
                }
            }
        }
    }

    override fun clickTaskChart() {
        view.makeLoadingVisible(true)
        model.getAllTasks {
            val tasks = if (it.message == "FAILURE") emptyList() else it.data ?: emptyList()
            if (tasks.isEmpty()) view.showMessage(it.message) else
                view.loadTasksDialog(tasks)
            view.makeLoadingVisible(false)
        }
    }

    override fun clickBalanceChart() {
        view.makeLoadingVisible(true)
        model.getAllBalance {
            val balance = if (it.message == "FAILURE") emptyList() else it.data ?: emptyList()
            if (balance.isEmpty()) view.showMessage(it.message) else
                view.loadBalanceDialog(balance)
            view.makeLoadingVisible(false)
        }
    }

    override fun clickUserChart() {
        view.makeLoadingVisible(true)
        model.getAllUsers {
            val users = if (it.message == "FAILURE") emptyList() else it.data ?: emptyList()
            if (users.isEmpty()) view.showMessage(it.message) else
                view.loadUsersDialog(users)
            view.makeLoadingVisible(false)
        }
    }

    override fun clickProductChart() {
        view.makeLoadingVisible(true)
        model.getAllProducts {
            val products = if (it.message == "FAILURE") emptyList() else it.data ?: emptyList()
            if (products.isEmpty()) view.showMessage(it.message) else
                view.loadProductDialog(products)
            view.makeLoadingVisible(false)
        }
    }

    override fun clickWorkersChart() {
        view.makeLoadingVisible(true)
        model.getAllWorkers {
            val workers = if (it.message == "FAILURE") emptyList() else it.data ?: emptyList()
            if (workers.isEmpty()) view.showMessage(it.message) else
                view.loadWorkersDialog(workers)
            view.makeLoadingVisible(false)
        }
    }
}