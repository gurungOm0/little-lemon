package com.example.littlelemon


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.http.ContentType
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private lateinit var database: MenuDatabase

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"), json = Json)
        }
    }


    // SharedPreference initialization
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("SharedPref", MODE_PRIVATE)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Log", "Running")
        // Database initialization
        database =
            Room.databaseBuilder(applicationContext, MenuDatabase::class.java, "menu.db").build()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                val dbFetch by database.menuDao().getAllItems().observeAsState(emptyList())

                NavigationComposable(sharedPreferences, dbFetch)
                Log.i("DB Start",database.menuDao().getAllItems().value.toString())
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuDao().isEmpty()) {
                Log.i("Scope", "Database empty")
                saveDataToDb(fetchData())
                Log.i("DB",database.menuDao().getAllItems().value.toString())
            }
        }
    }


    private suspend fun fetchData(): List<MenuItemNetwork> {
        val data: MenuNetworkData =
            client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .body()
        Log.i("Fetch",data.menu.toString())
        return data.menu
    }

    private fun saveDataToDb(menuItemNetwork: List<MenuItemNetwork>) {
        val roomData = menuItemNetwork.map { it.toMenuItemRoom() }
        database.menuDao().saveMenuItemList(roomData)
        Log.i("DbSave",roomData.toString())
    }

}
