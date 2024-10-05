package com.example.listadecomprasapp.shoppinglist.data.model

data class ShoppingItemModel(
    val id: Int,
    val listId: Int,
    var name: String,
    var quantity: Double,
    var unit: String,
    var category: String,
    var isChecked: Boolean
)
