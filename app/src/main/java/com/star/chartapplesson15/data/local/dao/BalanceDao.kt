package com.star.chartapplesson15.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.star.chartapplesson15.data.local.entity.BalanceData

@Dao
interface BalanceDao : BaseDao<BalanceData> {
    @Query("SELECT * FROM BalanceData")
    fun getAllProducts(): List<BalanceData>

    @Query("DELETE FROM BalanceData")
    fun clearDB()
}