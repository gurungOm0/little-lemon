package com.example.littlelemon.repository

import com.example.littlelemon.data.local.MenuItem
import com.example.littlelemon.models.UserProfile

interface AppRepository {
    suspend fun fetchAndSave()
    suspend fun readFetched(): List<MenuItem>?
    suspend fun dbFetch(): List<MenuItem>?
    suspend fun sharedPrefSaveUserData(profile: UserProfile)
    suspend fun sharedPrefGetUserData(): UserProfile
    suspend fun sharedPrefClearData()
}
