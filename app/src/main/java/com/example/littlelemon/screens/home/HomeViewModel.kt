package com.example.littlelemon.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.local.MenuItem
import com.example.littlelemon.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log


@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: AppRepository): ViewModel() {
    var items by mutableStateOf<List<MenuItem>?>(emptyList())
    fun load(){
        viewModelScope.launch {
            items = try {
                repo.fetchAndSave()
                repo.dbFetch()
            }catch (e: Exception){
                repo.readFetched()
            }
        }
    }
}