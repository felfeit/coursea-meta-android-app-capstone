package com.quiraadev.littlelemon.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MenuEntity::class], version = 1, exportSchema = false)
abstract class RestaurantDatabase: RoomDatabase() {
    abstract fun menuDao(): MenuDao
}