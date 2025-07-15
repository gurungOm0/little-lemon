package com.example.littlelemon.data.remote

import com.example.littlelemon.models.MenuItemNetwork
import com.example.littlelemon.models.MenuNetworkData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Named

class RemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient,
    @Named("baseUrl") private val baseUrl: String
) : RemoteDataSource {
    override suspend fun fetchData(): List<MenuItemNetwork> {
        val data: MenuNetworkData = client.get(baseUrl).body()
        return data.menu
    }
}