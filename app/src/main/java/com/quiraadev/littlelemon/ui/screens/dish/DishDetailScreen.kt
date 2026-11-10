package com.quiraadev.littlelemon.ui.screens.dish

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.quiraadev.littlelemon.database.MenuEntity
import com.quiraadev.littlelemon.ui.screens.dish.components.DishContent
import com.quiraadev.littlelemon.ui.screens.dish.components.DishTopAppBar

@Composable
fun DishDetailScreen(navController: NavHostController, dish: MenuEntity) {
    Scaffold(
        topBar = {
            DishTopAppBar(navController)
        }
    ) { innerPadding ->
        DishContent(innerPadding, dish)
    }
}
