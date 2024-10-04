package com.example.listadecomprasapp.shoppinglist.data

import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingListModel

interface OnListClickListener {
    fun onItemClick(item: ShoppingListModel)
}