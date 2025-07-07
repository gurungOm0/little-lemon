package com.example.littlelemon.screens.onboarding

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
class OnBoardingViewModel @Inject constructor(
    private val repo: AppRepository
): ViewModel(){
    var fName by mutableStateOf("")
    var lName by mutableStateOf("")
    var email by mutableStateOf("")
    fun submit(){
        viewModelScope.launch {
            repo.sharedPrefSaveUserData(UserProfile(fName,lName,email))
        }
    }
}