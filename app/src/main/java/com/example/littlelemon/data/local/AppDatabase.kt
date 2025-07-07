package com.example.littlelemon.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MenuItem::class], version = 1, exportSchema = false)
abstract class MenuDatabase: RoomDatabase(){
    abstract fun menuDao():MenuDao
}