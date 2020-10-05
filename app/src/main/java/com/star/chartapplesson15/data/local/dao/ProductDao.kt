package com.star.chartapplesson15.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.star.chartapplesson15.data.local.entity.ProductData

@Dao
interface ProductDao : BaseDao<ProductData> {
    @Query("SELECT * FROM ProductData")
    fun getAllProducts(): List<ProductData>

    @Query("DELETE FROM ProductData")
    fun clearDB()
}