package com.quiraadev.littlelemon

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.quiraadev.littlelemon.database.RestaurantDatabase
import com.quiraadev.littlelemon.network.MenuDto
import com.quiraadev.littlelemon.network.MenuListResponse
import com.quiraadev.littlelemon.ui.theme.LittleLemonTheme
import com.quiraadev.littlelemon.utils.NavigationComposable
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType(contentType = "text", contentSubtype = "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = RestaurantDatabase::class.java,
            name = "menuDatabase"
        ).build()
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContent {

            LittleLemonTheme {
                val navController = rememberNavController()
                val menus by database.menuDao().getAll()
                    .observeAsState(emptyList())
                NavigationComposable(navController, menus)
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuDao().isEmpty()) {
                saveMenuToDatabase(fetchMenu())
            }
        }
    }

    private fun saveMenuToDatabase(response: List<MenuDto>) {
        val menuEntities = response.map { it.toMenuEntity() }
        database.menuDao().insertAll(*menuEntities.toTypedArray())
    }

    private suspend fun fetchMenu(): List<MenuDto> {
        val url =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
        val response = client.get(url).body<MenuListResponse>().menu
        return response
    }

}