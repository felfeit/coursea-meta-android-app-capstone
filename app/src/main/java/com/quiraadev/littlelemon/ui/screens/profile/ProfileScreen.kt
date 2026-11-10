package com.quiraadev.littlelemon.ui.screens.profile

import android.content.Context
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.quiraadev.littlelemon.ui.screens.profile.components.ProfileBottomAppBar
import com.quiraadev.littlelemon.ui.screens.profile.components.ProfileContent
import com.quiraadev.littlelemon.ui.screens.profile.components.ProfileTopAppBar

@Composable
fun ProfileScreen(navController: NavHostController) {
    val ctx: Context = LocalContext.current
    val profileCoroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            ProfileTopAppBar(navController)
        },
        bottomBar = {
            ProfileBottomAppBar(navController, profileCoroutineScope, ctx)
        }
    ) {
        ProfileContent(paddingValues = it)
    }
}
