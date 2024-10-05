package com.example.listadecomprasapp.shoppinglist.data.model

data class ShoppingListModel(
    val id: Int,
    val userId: Int,
    var name: String,
    var imageUrl: String? = null
)