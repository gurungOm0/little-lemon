package com.example.littlelemon.repository


import com.example.littlelemon.data.local.MenuDao
import com.example.littlelemon.data.local.MenuItem
import com.example.littlelemon.data.remote.RemoteDataSourceImpl
import com.example.littlelemon.data.sharedprefs.UserPrefDataImpl
import com.example.littlelemon.models.UserProfile
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSourceImpl,
    private val dao: MenuDao,
    private val userPrefs: UserPrefDataImpl
): AppRepository{

    override suspend fun fetchAndSave(): List<MenuItem> {
        val items = remote.fetchData()
        val finalData = items.map { it.toMenuItemRoom() }
        dao.saveMenuItemList(finalData)
        return finalData
    }

    override suspend fun readFetched(): List<MenuItem> {
        val finalData = dao.getAllItems().value!!
        return finalData
    }

    override suspend fun dbFetch(): List<MenuItem> {
        return dao.getAllItems().value!!
    }

    override suspend fun sharedPrefSaveUserData(profile: UserProfile) {
        userPrefs.saveUser(profile)
    }

    override suspend fun sharedPrefGetUserData(): UserProfile {
        return userPrefs.getUser()!!
    }

    override suspend fun sharedPrefClearData() {
        userPrefs.clearUser()
    }
}

