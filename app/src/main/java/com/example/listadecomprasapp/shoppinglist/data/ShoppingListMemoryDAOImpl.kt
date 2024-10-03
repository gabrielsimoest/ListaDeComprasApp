package com.example.listadecomprasapp.shoppinglist.data

import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingItemModel
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingListModel

class ShoppingListMemoryDAOImpl : ShoppingListDAO {
    private val shoppingList: MutableList<ShoppingListModel> = mutableListOf()
    private val itemList: MutableList<ShoppingItemModel> = mutableListOf()

    override fun getLists(userId: Int): ArrayList<ShoppingListModel> {
        return shoppingList.filter { it.userId == userId }.toCollection(ArrayList())
    }

    override fun getList(listId: Int): ShoppingListModel? {
        return shoppingList.find { it.id == listId }
    }

    override fun getItems(listId: Int): ArrayList<ShoppingItemModel> {
        return itemList.filter { it.listId == listId }.toCollection(ArrayList())
    }

    override fun getItem(itemId: Int): ShoppingItemModel? {
        return itemList.find { it.id == itemId }
    }

    override fun addShoppingList(list: ShoppingListModel) {
        shoppingList.add(list)
    }

    override fun addShoppingItem(item: ShoppingItemModel) {
        itemList.add(item)
    }
}
