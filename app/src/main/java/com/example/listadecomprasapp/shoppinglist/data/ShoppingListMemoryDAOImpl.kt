package com.example.listadecomprasapp.shoppinglist.data

import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingItemModel
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingListModel

class ShoppingListMemoryDAOImpl : ShoppingListDAO {
    private val shoppingList: MutableList<ShoppingListModel> = mutableListOf()
    private var shoppingListSequence: Int = 1

    private val itemList: MutableList<ShoppingItemModel> = mutableListOf()
    private var itemListSequence: Int = 1

    init {
        val defaultShoppingList = ShoppingListModel(
            id = shoppingListSequence++,
            userId = 1,
            name = "Supermercado",
            imageUrl = null
        )
        shoppingList.add(defaultShoppingList)

        val defaultShoppingItem = ShoppingItemModel(
            id = itemListSequence++,
            listId = defaultShoppingList.id,
            name = "Leite",
            quantity = 2.0,
            unit = "L",
            category = "Bebidas",
            isChecked = false
        )
        itemList.add(defaultShoppingItem)

        val defaultShoppingItem2 = ShoppingItemModel(
            id = itemListSequence++,
            listId = defaultShoppingList.id,
            name = "Agua",
            quantity = 5.0,
            unit = "L",
            category = "Bebidas",
            isChecked = true
        )
        itemList.add(defaultShoppingItem2)
    }

    override fun getLists(userId: Int): ArrayList<ShoppingListModel> {
        return shoppingList.filter { it.userId == userId }
            .sortedBy { it.name  }
            .toCollection(ArrayList())
    }

    override fun getList(listId: Int): ShoppingListModel? {
        return shoppingList.find { it.id == listId }
    }

    override fun getItems(listId: Int): ArrayList<ShoppingItemModel> {
        return itemList.filter { it.listId == listId }
            .sortedBy { it.isChecked }
            .sortedBy { it.category }
            .sortedBy { it.name }
            .toCollection(ArrayList())
    }

    override fun getItem(itemId: Int): ShoppingItemModel? {
        return itemList.find { it.id == itemId }
    }

    override fun addShoppingList(list: ShoppingListModel) : Int {
        val id = shoppingListSequence++
        val newList = list.copy(id = id)
        shoppingList.add(newList)

        return id
    }

    override fun addShoppingItem(item: ShoppingItemModel) : Int {
        val id = itemListSequence++
        val newItem = item.copy(id = id)
        itemList.add(newItem)

        return id
    }

    override fun removeShoppingList(listId: Int): Boolean {
        val listToRemove = shoppingList.find { it.id == listId }
        return if (listToRemove != null) {
            shoppingList.remove(listToRemove)
            itemList.removeIf { it.listId == listId }
            true
        } else {
            false
        }
    }

    override fun removeShoppingItem(itemId: Int): Boolean {
        val itemToRemove = itemList.find { it.id == itemId }
        return if (itemToRemove != null) {
            itemList.remove(itemToRemove)
            true
        } else {
            false
        }
    }

    override fun getItemsCount(listId: Int): Int {
        return itemList.count { it.listId == listId }
    }

    override fun getListsCount(userId: Int): Int {
        return shoppingList.count { it.userId == userId }
    }

    override fun updateShoppingList(list: ShoppingListModel): Boolean {
        val index = shoppingList.indexOfFirst { it.id == list.id }
        return if (index != -1) {
            shoppingList[index] = list
            true
        } else {
            false
        }
    }

    override fun updateShoppingItem(item: ShoppingItemModel): Boolean {
        val index = itemList.indexOfFirst { it.id == item.id }
        return if (index != -1) {
            itemList[index] = item
            true
        } else {
            false
        }
    }
}
