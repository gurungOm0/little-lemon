package com.example.littlelemon.di

import android.content.Context
import androidx.room.Room
import com.example.littlelemon.data.local.MenuDao
import com.example.littlelemon.data.local.MenuDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context) = Room.databaseBuilder(ctx, MenuDatabase::class.java, "menu.db").build()


    @Provides
    fun providesDao(db: MenuDatabase): MenuDao = db.menuDao()
}