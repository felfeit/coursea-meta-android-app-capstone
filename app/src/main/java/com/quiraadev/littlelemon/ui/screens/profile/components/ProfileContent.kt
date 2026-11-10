package com.quiraadev.littlelemon.ui.screens.profile.components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.quiraadev.littlelemon.R
import com.quiraadev.littlelemon.utils.PreferencesKeys
import com.quiraadev.littlelemon.utils.dataStore
import kotlinx.coroutines.flow.map


@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun ProfileContent(paddingValues: PaddingValues) {
    val ctx: Context = LocalContext.current
    val firstName by ctx.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.FIRST_NAME] ?: ""
    }.collectAsStateWithLifecycle(initialValue = "")

    val lastName by ctx.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.LAST_NAME] ?: ""
    }.collectAsStateWithLifecycle(initialValue = "")

    val email by ctx.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.EMAIL] ?: ""
    }.collectAsStateWithLifecycle(initialValue = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 48.dp),
            text = stringResource(R.string.profile_information),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 48.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Profile Picture",
                )
            }
            Text(text = stringResource(R.string.first_name), fontSize = 16.sp)
            OutlinedTextField(
                value = firstName,
                onValueChange = { /** Not editable */ },
                readOnly = true,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textStyle = TextStyle(fontSize = 16.sp),
            )

            Text(
                text = stringResource(R.string.last_name), fontSize = 16.sp,
                modifier = Modifier.padding(top = 24.dp)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { /** Not editable */ },
                readOnly = true,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textStyle = TextStyle(fontSize = 16.sp)
            )

            Text(
                text = stringResource(R.string.email), fontSize = 16.sp,
                modifier = Modifier.padding(top = 24.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { /** Not editable */ },
                readOnly = true,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textStyle = TextStyle(fontSize = 16.sp)
            )
        }
    }
}
