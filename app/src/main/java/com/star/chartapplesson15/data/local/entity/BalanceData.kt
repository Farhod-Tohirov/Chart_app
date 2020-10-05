package com.star.chartapplesson15.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BalanceData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val value: Int
)