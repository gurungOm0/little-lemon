package com.example.littlelemon.data.remote

import com.example.littlelemon.models.MenuItemNetwork

interface RemoteDataSource {
    suspend fun fetchData():List<MenuItemNetwork>
}