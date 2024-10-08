package com.example.listadecomprasapp.shoppinglist.data

import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingItemModel
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingListModel

interface ShoppingListDAO {
    fun getLists(userId: Int): ArrayList<ShoppingListModel>

    fun getList(listId: Int): ShoppingListModel?

    fun getItems(listId: Int): ArrayList<ShoppingItemModel>

    fun getItem(itemId: Int): ShoppingItemModel?

    fun addShoppingList(list: ShoppingListModel) : Int

    fun addShoppingItem(item: ShoppingItemModel) : Int

    fun removeShoppingList(listId: Int): Boolean

    fun removeShoppingItem(itemId: Int): Boolean

    fun updateShoppingList(list: ShoppingListModel): Boolean

    fun updateShoppingItem(item: ShoppingItemModel): Boolean

    fun getItemsCount(listId: Int) : Int

    fun getListsCount(userId: Int) : Int
}
