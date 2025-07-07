package com.example.littlelemon.di

import android.util.Log
import com.example.littlelemon.data.remote.RemoteDataSource
import com.example.littlelemon.data.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    @Provides @Singleton @Named("baseUrl")
    fun provideBaseUrl() = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

    @Provides @Singleton
    fun provideHttpClient() = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"), json = Json)
        }
    }

    @Provides
    fun provideRemoteDataSource(
        client: HttpClient, @Named("baseUrl") baseUrl:String
    ): RemoteDataSource = RemoteDataSourceImpl(client,baseUrl)
}