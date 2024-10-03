package com.example.listadecomprasapp.shoppinglist.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecomprasapp.R
import com.example.listadecomprasapp.account.data.LoginRepository
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingListModel

class ShoppingListAdapter(private val shoppingListDAO: ShoppingListDAO, private val loginRepository: LoginRepository, private val listener: OnListClickListener) : RecyclerView.Adapter<ShoppingListAdapter.ItemViewHolder>() {
    fun addItem(list: ShoppingListModel) {
        val id = shoppingListDAO.addShoppingList(list)
        notifyItemInserted(id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, id: Int) {
        val currentItem = shoppingListDAO.getList(id)
        holder.textView.text = currentItem?.name

        holder.itemView.setOnClickListener {
            if(currentItem != null)
                listener.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return shoppingListDAO.getListsCount(loginRepository.user?.userId ?: 0)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.item_text)
    }
}
