package com.example.littlelemon.di

import com.example.littlelemon.repository.AppRepository
import com.example.littlelemon.repository.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds abstract fun bindRepo(
        impl: AppRepositoryImpl
    ): AppRepository
}