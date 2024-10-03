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

    override fun addShoppingList(list: ShoppingListModel) : Int {
        val id = shoppingList.size + 1
        val newList = list.copy(id = id)
        shoppingList.add(newList)

        return id;
    }

    override fun addShoppingItem(item: ShoppingItemModel) : Int {
        val id = itemList.size + 1
        val newItem = item.copy(id = id)
        itemList.add(newItem)

        return id;
    }

    override fun getItemsCount(listId: Int): Int {
        return itemList.count { it.listId == listId }
    }

    override fun getListsCount(userId: Int): Int {
        return shoppingList.count { it.userId == userId }
    }
}
