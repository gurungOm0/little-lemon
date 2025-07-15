package com.example.littlelemon.data.sharedprefs

import com.example.littlelemon.models.UserProfile

interface UserPrefData {
    suspend fun saveUser(profile: UserProfile)
    suspend fun getUser(): UserProfile?
    suspend fun clearUser()
    suspend fun userLogged()
}