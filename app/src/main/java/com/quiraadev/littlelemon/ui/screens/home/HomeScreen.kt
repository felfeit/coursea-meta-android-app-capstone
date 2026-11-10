package com.quiraadev.littlelemon.ui.screens.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.quiraadev.littlelemon.database.MenuEntity
import com.quiraadev.littlelemon.ui.screens.home.components.HomeContent
import com.quiraadev.littlelemon.ui.screens.home.components.HomeTopAppBar

@Composable
fun HomeScreen(navController: NavHostController, menus: List<MenuEntity>) {
    Scaffold (
        topBar = {
            HomeTopAppBar(navController)
        },
    ){ innerPadding ->
        HomeContent(innerPadding, navController, menus)
    }
}
