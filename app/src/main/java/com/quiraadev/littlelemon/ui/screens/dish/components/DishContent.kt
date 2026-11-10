package com.quiraadev.littlelemon.ui.screens.dish.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.quiraadev.littlelemon.database.MenuEntity

@Composable
fun DishContent(paddingValues: PaddingValues, dish: MenuEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            AsyncImage(
                model = dish.image,
                contentDescription = dish.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .aspectRatio(1F),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = dish.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$${dish.price}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = dish.description,
            fontSize = 16.sp,
        )
    }
}