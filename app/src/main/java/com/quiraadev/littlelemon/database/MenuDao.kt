package com.quiraadev.littlelemon.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MenuDao {

    @Query("SELECT * FROM MenuEntity")
    fun getAll(): LiveData<List<MenuEntity>>

    @Insert
    fun insertAll(vararg menuEntities: MenuEntity)

    @Query("SELECT (SELECT COUNT(*) FROM MenuEntity) == 0")
    fun isEmpty(): Boolean
}