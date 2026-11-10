package com.quiraadev.littlelemon.network

import com.quiraadev.littlelemon.database.MenuEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable




@Serializable
data class MenuDto(
    @SerialName(value = "id")
    val id : Int,
    @SerialName(value = "title")
    val title: String,
    @SerialName(value = "description")
    val description: String,
    @SerialName(value = "price")
    val price: Double,
    @SerialName(value = "image")
    val image: String,
    @SerialName(value = "category")
    val category: String,
) {
    fun toMenuEntity() = MenuEntity(
        id,
        title,
        description,
        price,
        image,
        category,
    )
}
