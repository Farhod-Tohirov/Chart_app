package com.star.chartapplesson15.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.star.chartapplesson15.data.local.entity.UsersData

@Dao
interface UserDao : BaseDao<UsersData> {
    @Query("SELECT * FROM UsersData")
    fun getAllProducts(): List<UsersData>

    @Query("DELETE FROM UsersData")
    fun clearDB()
}