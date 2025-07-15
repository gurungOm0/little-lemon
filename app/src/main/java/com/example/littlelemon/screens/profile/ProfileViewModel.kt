package com.example.littlelemon.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.models.UserProfile
import com.example.littlelemon.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: AppRepository
): ViewModel() {
    var profile by mutableStateOf(UserProfile("","",""))
    var alertDialog by mutableStateOf(false)
    fun load(){
        viewModelScope.launch {
            profile = repo.sharedPrefGetUserData()
        }
    }
    fun logout(){
        viewModelScope.launch {
            repo.sharedPrefClearData()
        }
    }
}