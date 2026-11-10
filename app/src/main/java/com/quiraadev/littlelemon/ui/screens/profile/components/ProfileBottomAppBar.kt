package com.quiraadev.littlelemon.ui.screens.profile.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavHostController
import com.quiraadev.littlelemon.ui.theme.GoldenCorn
import com.quiraadev.littlelemon.utils.Onboarding
import com.quiraadev.littlelemon.utils.PreferencesKeys
import com.quiraadev.littlelemon.utils.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ProfileBottomAppBar(
    navController: NavHostController,
    profileCoroutineScope: CoroutineScope,
    ctx: Context,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = {
                profileCoroutineScope.launch {
                    ctx.dataStore.edit { preferences ->
                        preferences[PreferencesKeys.FIRST_NAME] = ""
                        preferences[PreferencesKeys.LAST_NAME] = ""
                        preferences[PreferencesKeys.EMAIL] = ""
                    }
                }
                navController.navigate(Onboarding.route) {
                    popUpTo(0) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = GoldenCorn
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Log out", fontSize = 16.sp)
        }
    }
}