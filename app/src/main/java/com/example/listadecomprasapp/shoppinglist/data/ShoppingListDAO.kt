package com.example.listadecomprasapp.shoppinglist.data

import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingItemModel
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingListModel

interface ShoppingListDAO {
    fun getLists(userId: Int): ArrayList<ShoppingListModel>

    fun getList(listId: Int): ShoppingListModel?

    fun getItems(listId: Int): ArrayList<ShoppingItemModel>

    fun getItem(itemId: Int): ShoppingItemModel?

    fun addShoppingList(list: ShoppingListModel)

    fun addShoppingItem(item: ShoppingItemModel)
}
