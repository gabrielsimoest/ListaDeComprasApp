package com.example.listadecomprasapp.shoppinglist.data

interface ShoppingListDAO {
    fun getLists(userId: Int)
    fun getItens(listId: Int)
}