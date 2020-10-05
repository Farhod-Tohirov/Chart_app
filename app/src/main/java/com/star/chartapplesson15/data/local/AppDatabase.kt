package com.star.chartapplesson15.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.star.chartapplesson15.data.local.dao.*
import com.star.chartapplesson15.data.local.entity.*

@Database(
    entities = [ProductData::class, UsersData::class, BalanceData::class, TasksData::class, WorkersData::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun balanceDao(): BalanceDao
    abstract fun productDao(): ProductDao
    abstract fun taskDao(): TasksDao
    abstract fun userDao(): UserDao
    abstract fun workerDao(): WorkersDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}