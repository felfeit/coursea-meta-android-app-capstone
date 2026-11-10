package com.quiraadev.littlelemon.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.quiraadev.littlelemon.database.MenuEntity
import com.quiraadev.littlelemon.ui.screens.dish.DishDetailScreen
import com.quiraadev.littlelemon.ui.screens.home.HomeScreen
import com.quiraadev.littlelemon.ui.screens.onboarding.OnboardingScreen
import com.quiraadev.littlelemon.ui.screens.profile.ProfileScreen
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun NavigationComposable(navController: NavHostController, menus: List<MenuEntity>) {
    val ctx: Context = LocalContext.current

    val email by ctx.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.EMAIL] ?: ""
    }.collectAsStateWithLifecycle(initialValue = "")

    val startDestination = if (email.isEmpty()) Onboarding.route else Home.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Home.route) {
            HomeScreen(navController, menus)
        }
        composable(Profile.route) {
            ProfileScreen(navController)
        }
        composable(Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(
            DetailDish.route + "/{${DetailDish.argDish}}",
            arguments = listOf(navArgument(DetailDish.argDish) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val dishJson = backStackEntry.arguments?.getString(DetailDish.argDish)
            val dish = try {
                dishJson?.let { Json.decodeFromString<MenuEntity>(Uri.decode(it)) }
            } catch (_: Exception) {
                val id = dishJson?.toIntOrNull()
                menus.firstOrNull { it.id == id }
            } ?: navController.previousBackStackEntry?.savedStateHandle?.get<MenuEntity>(DetailDish.argDish)

            if (dish != null) {
                DishDetailScreen(navController, dish = dish)
            }
        }
    }
}