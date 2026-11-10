package com.quiraadev.littlelemon.ui.screens.onboarding.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavHostController
import com.quiraadev.littlelemon.R
import com.quiraadev.littlelemon.ui.theme.GoldenCorn
import com.quiraadev.littlelemon.utils.Home
import com.quiraadev.littlelemon.utils.Onboarding
import com.quiraadev.littlelemon.utils.PreferencesKeys
import com.quiraadev.littlelemon.utils.Validator
import com.quiraadev.littlelemon.utils.dataStore
import kotlinx.coroutines.launch


@Composable
fun OnboardingBottomBar(
    firstName: MutableState<String>,
    lastName: MutableState<String>,
    email: MutableState<String>,
    emailError: MutableState<String?>,
    navController: NavHostController,
    ctx: Context
) {
    val buttonCoroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.padding(top = 48.dp)
    ) {
        Button(
            onClick = {
                if (firstName.value.isBlank()) {
                    Toast.makeText(ctx, "First name cannot be empty", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (lastName.value.isBlank()) {
                    Toast.makeText(ctx, "Last name cannot be empty", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                emailError.value = Validator.validateEmail(email.value)
                if (firstName.value.isNotBlank() && lastName.value.isNotBlank() && emailError.value == null) {
                    buttonCoroutineScope.launch {
                        ctx.dataStore.edit { preferences ->
                            preferences[PreferencesKeys.FIRST_NAME] = firstName.value
                            preferences[PreferencesKeys.LAST_NAME] = lastName.value
                            preferences[PreferencesKeys.EMAIL] = email.value
                        }
                    }
                    navController.navigate(Home.route) {
                        popUpTo(Onboarding.route) { inclusive = true }
                    }
                }
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black, containerColor = GoldenCorn
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 24.dp, end = 16.dp),
        ) {
            Text(
                fontSize = 16.sp,
                text = stringResource(id = R.string.register),
            )
        }
    }
}