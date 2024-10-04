package com.example.listadecomprasapp.shoppinglist.data

import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingItemModel

interface OnItemClickListener {
    fun onItemClick(item: ShoppingItemModel)
}