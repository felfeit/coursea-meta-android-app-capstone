package com.quiraadev.littlelemon.ui.screens.onboarding

import android.content.Context
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.quiraadev.littlelemon.ui.screens.onboarding.components.OnboardingBottomBar
import com.quiraadev.littlelemon.ui.screens.onboarding.components.OnboardingContent
import com.quiraadev.littlelemon.ui.screens.onboarding.components.OnboardingTopAppBar

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val emailError = remember { mutableStateOf<String?>(null) }
    val ctx: Context = LocalContext.current

    Scaffold(
        topBar = {
            OnboardingTopAppBar()
        },
        bottomBar = {
            OnboardingBottomBar(
                firstName = firstName, lastName = lastName,
                email = email, emailError = emailError,
                navController = navController, ctx = ctx
            )
        }
    ) {
        OnboardingContent(firstName, lastName, email, emailError, it)
    }
}
