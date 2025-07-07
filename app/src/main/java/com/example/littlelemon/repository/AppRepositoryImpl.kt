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
) : AppRepository {

    override suspend fun fetchAndSave() {
        val items = remote.fetchData()
        val finalData = items.map { it.toMenuItemRoom() }
        dao.saveMenuItemList(finalData)
    }

    override suspend fun readFetched(): List<MenuItem>? {
        val items = remote.fetchData()
        val finalData = items.map { it.toMenuItemRoom() }
        return finalData
    }

    override suspend fun dbFetch(): List<MenuItem>? {
        if (dao.isEmpty()) {
            return dao.getAllItems()?.value
        }else return emptyList()
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

