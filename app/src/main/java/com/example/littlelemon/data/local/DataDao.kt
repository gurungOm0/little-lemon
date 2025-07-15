package com.example.littlelemon.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuDao{
    @Query("SELECT * FROM MenuItem")
    fun getAllItems():LiveData<List<MenuItem>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMenuItemList(menuItem: List<MenuItem>)

    @Insert
    fun saveMenuItems(menuItem: MenuItem)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItem) == 0")
    fun isEmpty(): Boolean

    @Delete
    fun deleteMenuItems(menuItem: MenuItem)
}