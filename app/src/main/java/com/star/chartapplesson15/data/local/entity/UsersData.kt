package com.star.chartapplesson15.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsersData(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val age: Int,
    val lastName: String,
    val firstName: String
)