package com.star.chartapplesson15.ui.screen.main

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.star.chartapplesson15.app.App
import com.star.chartapplesson15.data.local.AppDatabase
import com.star.chartapplesson15.data.local.entity.*
import com.star.chartapplesson15.data.restApi.ApiClient
import com.star.chartapplesson15.data.restApi.StatisticsDataApi
import com.star.chartapplesson15.model.ResponseData
import com.star.chartapplesson15.model.ValueData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class Model : Contract.Model {

    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())
    private val api = ApiClient.createService(StatisticsDataApi::class.java)
    private val appDataBase = AppDatabase.getDatabase(App.instance)


    override fun getBalance(block: (ResponseData<List<ValueData>>) -> Unit) {
        api.getBalance().enqueue(object : Callback<ResponseData<List<ValueData>>> {
            override fun onFailure(call: Call<ResponseData<List<ValueData>>>, t: Throwable) {
                block(ResponseData("FAILURE", t.toString()))
            }

            override fun onResponse(
                call: Call<ResponseData<List<ValueData>>>,
                response: Response<ResponseData<List<ValueData>>>
            ) {
                if (!response.isSuccessful) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                if (response.code() >= 500 || response.code() == 404) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                val res = response.body()
                when {
                    res == null -> block(ResponseData("FAILURE", "Null data came from server"))
                    res.status == "ERROR" -> block(ResponseData("FAILURE", res.message))
                    res.status == "OK" -> block(ResponseData("OK", res.message, res.data))
                }
            }
        })
    }

    override fun getTasks(block: (ResponseData<List<ValueData>>) -> Unit) {
        api.getTasks().enqueue(object : Callback<ResponseData<List<ValueData>>> {
            override fun onFailure(call: Call<ResponseData<List<ValueData>>>, t: Throwable) {
                block(ResponseData("FAILURE", t.toString()))
            }

            override fun onResponse(
                call: Call<ResponseData<List<ValueData>>>,
                response: Response<ResponseData<List<ValueData>>>
            ) {
                if (!response.isSuccessful) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                if (response.code() >= 500 || response.code() == 404) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                val res = response.body()
                when {
                    res == null -> block(ResponseData("FAILURE", "Null data came from server"))
                    res.status == "ERROR" -> block(ResponseData("FAILURE", res.message))
                    res.status == "OK" -> block(ResponseData("OK", res.message, res.data))
                }
            }
        })
    }

    override fun getUsers(block: (ResponseData<List<UsersData>>) -> Unit) {
        api.getUsers().enqueue(object : Callback<ResponseData<List<UsersData>>> {
            override fun onFailure(call: Call<ResponseData<List<UsersData>>>, t: Throwable) {
                block(ResponseData("FAILURE", t.toString()))
            }

            override fun onResponse(
                call: Call<ResponseData<List<UsersData>>>,
                response: Response<ResponseData<List<UsersData>>>
            ) {
                if (!response.isSuccessful) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                if (response.code() >= 500 || response.code() == 404) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                val res = response.body()
                when {
                    res == null -> block(ResponseData("FAILURE", "Null data came from server"))
                    res.status == "ERROR" -> block(ResponseData("FAILURE", res.message))
                    res.status == "OK" -> block(ResponseData("OK", res.message, res.data))
                }
            }
        })
    }

    override fun getWorkers(block: (ResponseData<List<ValueData>>) -> Unit) {
        api.getTasks().enqueue(object : Callback<ResponseData<List<ValueData>>> {
            override fun onFailure(call: Call<ResponseData<List<ValueData>>>, t: Throwable) {
                block(ResponseData("FAILURE", t.toString()))
            }

            override fun onResponse(
                call: Call<ResponseData<List<ValueData>>>,
                response: Response<ResponseData<List<ValueData>>>
            ) {
                if (!response.isSuccessful) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                if (response.code() >= 500 || response.code() == 404) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                val res = response.body()
                when {
                    res == null -> block(ResponseData("FAILURE", "Null data came from server"))
                    res.status == "ERROR" -> block(ResponseData("FAILURE", res.message))
                    res.status == "OK" -> block(ResponseData("OK", res.message, res.data))
                }
            }
        })
    }

    override fun getProducts(block: (ResponseData<List<ProductData>>) -> Unit) {
        api.getProducts().enqueue(object : Callback<ResponseData<List<ProductData>>> {
            override fun onFailure(call: Call<ResponseData<List<ProductData>>>, t: Throwable) {
                block(ResponseData("FAILURE", t.toString()))
            }

            override fun onResponse(
                call: Call<ResponseData<List<ProductData>>>,
                response: Response<ResponseData<List<ProductData>>>
            ) {
                if (!response.isSuccessful) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                if (response.code() >= 500 || response.code() == 404) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                val res = response.body()
                when {
                    res == null -> block(ResponseData("FAILURE", "Null data came from server"))
                    res.status == "ERROR" -> block(ResponseData("FAILURE", res.message))
                    res.status == "OK" -> block(ResponseData("OK", res.message, res.data))
                }
            }
        })
    }

    override fun getAllBalance(block: (ResponseData<List<ValueData>>) -> Unit) {
        api.getAllBalance().enqueue(object : Callback<ResponseData<List<ValueData>>> {
            override fun onFailure(call: Call<ResponseData<List<ValueData>>>, t: Throwable) {
                block(ResponseData("FAILURE", t.toString()))
            }

            override fun onResponse(
                call: Call<ResponseData<List<ValueData>>>,
                response: Response<ResponseData<List<ValueData>>>
            ) {
                if (!response.isSuccessful) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                if (response.code() >= 500 || response.code() == 404) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                val res = response.body()
                when {
                    res == null -> block(ResponseData("FAILURE", "Null data came from server"))
                    res.status == "ERROR" -> block(ResponseData("FAILURE", res.message))
                    res.status == "OK" -> block(ResponseData("OK", res.message, res.data))
                }
            }
        })
    }

    override fun getAllTasks(block: (ResponseData<List<ValueData>>) -> Unit) {
        api.getAllTasks().enqueue(object : Callback<ResponseData<List<ValueData>>> {
            override fun onFailure(call: Call<ResponseData<List<ValueData>>>, t: Throwable) {
                block(ResponseData("FAILURE", t.toString()))
            }

            override fun onResponse(
                call: Call<ResponseData<List<ValueData>>>,
                response: Response<ResponseData<List<ValueData>>>
            ) {
                if (!response.isSuccessful) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                if (response.code() >= 500 || response.code() == 404) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                val res = response.body()
                when {
                    res == null -> block(ResponseData("FAILURE", "Null data came from server"))
                    res.status == "ERROR" -> block(ResponseData("FAILURE", res.message))
                    res.status == "OK" -> block(ResponseData("OK", res.message, res.data))
                }
            }
        })
    }

    override fun getAllUsers(block: (ResponseData<List<UsersData>>) -> Unit) {
        api.getAllUsers().enqueue(object : Callback<ResponseData<List<UsersData>>> {
            override fun onFailure(call: Call<ResponseData<List<UsersData>>>, t: Throwable) {
                block(ResponseData("FAILURE", t.toString()))
            }

            override fun onResponse(
                call: Call<ResponseData<List<UsersData>>>,
                response: Response<ResponseData<List<UsersData>>>
            ) {
                if (!response.isSuccessful) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                if (response.code() >= 500 || response.code() == 404) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                val res = response.body()
                when {
                    res == null -> block(ResponseData("FAILURE", "Null data came from server"))
                    res.status == "ERROR" -> block(ResponseData("FAILURE", res.message))
                    res.status == "OK" -> block(ResponseData("OK", res.message, res.data))
                }
            }
        })
    }

    override fun getAllWorkers(block: (ResponseData<List<ValueData>>) -> Unit) {
        api.getAllWorkers().enqueue(object : Callback<ResponseData<List<ValueData>>> {
            override fun onFailure(call: Call<ResponseData<List<ValueData>>>, t: Throwable) {
                block(ResponseData("FAILURE", t.toString()))
            }

            override fun onResponse(
                call: Call<ResponseData<List<ValueData>>>,
                response: Response<ResponseData<List<ValueData>>>
            ) {
                if (!response.isSuccessful) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                if (response.code() >= 500 || response.code() == 404) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                val res = response.body()
                when {
                    res == null -> block(ResponseData("FAILURE", "Null data came from server"))
                    res.status == "ERROR" -> block(ResponseData("FAILURE", res.message))
                    res.status == "OK" -> block(ResponseData("OK", res.message, res.data))
                }
            }
        })
    }

    override fun getAllProducts(block: (ResponseData<List<ProductData>>) -> Unit) {
        api.getAllProducts().enqueue(object : Callback<ResponseData<List<ProductData>>> {
            override fun onFailure(call: Call<ResponseData<List<ProductData>>>, t: Throwable) {
                block(ResponseData("FAILURE", t.toString()))
            }

            override fun onResponse(
                call: Call<ResponseData<List<ProductData>>>,
                response: Response<ResponseData<List<ProductData>>>
            ) {
                if (!response.isSuccessful) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                if (response.code() >= 500 || response.code() == 404) {
                    block(ResponseData("FAILURE", "Server is not found"))
                    return
                }
                val res = response.body()
                when {
                    res == null -> block(ResponseData("FAILURE", "Null data came from server"))
                    res.status == "ERROR" -> block(ResponseData("FAILURE", res.message))
                    res.status == "OK" -> block(ResponseData("OK", res.message, res.data))
                }
            }
        })
    }

    override fun saveBalanceToDB(ls: List<BalanceData>) {
        runOnWorkerThread {
            appDataBase.balanceDao().clearDB()
            appDataBase.balanceDao().insertAll(ls)
        }
    }

    override fun saveTasksToDB(ls: List<TasksData>) {
        runOnWorkerThread {
            appDataBase.taskDao().clearDB()
            appDataBase.taskDao().insertAll(ls)
        }
    }

    override fun saveUsersToDB(ls: List<UsersData>) {
        runOnWorkerThread {
            appDataBase.userDao().clearDB()
            appDataBase.userDao().insertAll(ls)
        }
    }

    override fun saveWorkersToDB(ls: List<WorkersData>) {
        runOnWorkerThread {
            appDataBase.workerDao().clearDB()
            appDataBase.workerDao().insertAll(ls)
        }
    }

    override fun saveProductsToDB(ls: List<ProductData>) {
        runOnWorkerThread {
            appDataBase.productDao().clearDB()
            appDataBase.productDao().insertAll(ls)
        }
    }

    override fun getBalanceFromDB(block: (List<BalanceData>) -> Unit) {
        runOnWorkerThread {
            val list = appDataBase.balanceDao().getAllProducts()
            runOnUIThread {
                block(list)
            }
        }
    }

    override fun getTasksFromDB(block: (List<TasksData>) -> Unit) {
        runOnWorkerThread {
            val list = appDataBase.taskDao().getAllProducts()
            runOnUIThread {
                block(list)
            }
        }
    }

    override fun getUsersFromDB(block: (List<UsersData>) -> Unit) {
        runOnWorkerThread {
            val list = appDataBase.userDao().getAllProducts()
            runOnUIThread {
                block(list)
            }
        }
    }

    override fun getWorkersFromDB(block: (List<WorkersData>) -> Unit) {
        runOnWorkerThread {
            val list = appDataBase.workerDao().getAllProducts()
            runOnUIThread {
                block(list)
            }
        }
    }

    override fun getProductsFromDB(block: (List<ProductData>) -> Unit) {
        runOnWorkerThread {
            val list = appDataBase.productDao().getAllProducts()
            runOnUIThread {
                block(list)
            }
        }
    }

    private fun runOnUIThread(f: () -> Unit) {
        if (Thread.currentThread() == Looper.getMainLooper().thread) {
            f()
        } else {
            handler.post(f)
        }
    }

    private fun runOnWorkerThread(f: () -> Unit) {
        executor.execute(f)
    }

}