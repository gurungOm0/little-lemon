package com.example.littlelemon.data.sharedprefs

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.littlelemon.models.UserProfile
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.core.content.edit

class UserPrefDataImpl @Inject constructor(
    @ApplicationContext private val ctx: Context
): UserPrefData {
    private val sharedPreferences = ctx.getSharedPreferences("Profile",MODE_PRIVATE)

    override suspend fun saveUser(profile: UserProfile) {
        sharedPreferences.edit {
            putString("fName", profile.fName)
                .putString("lName", profile.lName)
                .putString("email", profile.email)
        }
    }

    override suspend fun getUser(): UserProfile? {
         return UserProfile(
            sharedPreferences.getString("fName","")!!,
            sharedPreferences.getString("lName","")!!,
            sharedPreferences.getString("email","")!!
        )
    }

    override suspend fun clearUser() {
        sharedPreferences.edit { clear().apply() }
    }
}