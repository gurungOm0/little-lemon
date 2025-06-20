package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.StateFlow


@Database(entities = [MenuItem::class], version = 1)
abstract class MenuDatabase: RoomDatabase(){
    abstract fun menuDao():MenuDao
}

@Entity
data class MenuItem(
    @PrimaryKey
    val id:Int,
    val title:String,
    val description:String,
    val price:Double,
    val image:String,
    val category:String
)

@Dao
interface MenuDao{
    @Query("SELECT * FROM MenuItem")
    fun getAllItems():LiveData<List<MenuItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMenuItemList(menuItem: List<MenuItem>)

    @Insert
    fun saveMenuItems(menuItem: MenuItem)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItem) == 0")
    fun isEmpty(): Boolean

    @Delete
    fun deleteMenuItems(menuItem: MenuItem)
}