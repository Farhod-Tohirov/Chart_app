package com.star.chartapplesson15.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductData(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val modelName: String,
    val serial: String,
    val manufacturer: String
)