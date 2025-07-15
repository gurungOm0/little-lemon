package com.example.littlelemon.di

import com.example.littlelemon.data.sharedprefs.UserPrefData
import com.example.littlelemon.data.sharedprefs.UserPrefDataImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SharedPrefsModule{
    @Binds
    abstract fun bindUserPrefs(impl: UserPrefDataImpl): UserPrefData
}