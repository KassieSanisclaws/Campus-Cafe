package com.raywenderlich.campuscafe.ui.dataclasses

import com.raywenderlich.campuscafe.ui.model.Category

data class MenuItem(
    val id: Int,
    val name: String,
    val price: Double,
    val category: Category
)