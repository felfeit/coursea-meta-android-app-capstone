package com.quiraadev.littlelemon.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuListResponse(
    @SerialName(value = "menu")
    val menu: MutableList<MenuDto>
)
