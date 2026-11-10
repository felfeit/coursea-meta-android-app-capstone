package com.quiraadev.littlelemon.ui.screens.home.components

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.quiraadev.littlelemon.database.MenuEntity
import com.quiraadev.littlelemon.utils.DetailDish
import kotlinx.serialization.json.Json


@Composable
fun MenuDishCard(dish: MenuEntity, navController: NavHostController) {
    Card(
        onClick = {
            val dishJson = Uri.encode(Json.encodeToString(dish))
            navController.navigate(DetailDish.route + "/$dishJson")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.7F)

            ) {
                Text(
                    text = dish.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
                Text(
                    text = dish.description,
                    fontSize = 16.sp,
                )
                Text(
                    text = "$${dish.price}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            AsyncImage(
                model = dish.image,
                contentDescription = dish.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
                    .aspectRatio(1F),
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 2.dp),
        thickness = 2.dp,
        color = Color(0xFFEDEFEE)
    )
}