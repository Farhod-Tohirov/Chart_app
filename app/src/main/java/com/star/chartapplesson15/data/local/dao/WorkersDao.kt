package com.star.chartapplesson15.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.star.chartapplesson15.data.local.entity.WorkersData

@Dao
interface WorkersDao : BaseDao<WorkersData> {
    @Query("SELECT * FROM WorkersData")
    fun getAllProducts(): List<WorkersData>

    @Query("DELETE FROM WorkersData")
    fun clearDB()
}