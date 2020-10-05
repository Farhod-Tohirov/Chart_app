package com.star.chartapplesson15.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.star.chartapplesson15.data.local.entity.TasksData

@Dao
interface TasksDao : BaseDao<TasksData> {
    @Query("SELECT * FROM TasksData")
    fun getAllProducts(): List<TasksData>

    @Query("DELETE FROM TasksData")
    fun clearDB()
}