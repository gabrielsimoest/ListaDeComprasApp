package com.example.listadecomprasapp.shoppinglist.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecomprasapp.R
import com.example.listadecomprasapp.account.data.LoginRepository
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingItemModel

class ShoppingItemAdapter(private val shoppingListDAO: ShoppingListDAO, private val listId: Int, private val listener: OnItemClickListener) : RecyclerView.Adapter<ShoppingItemAdapter.ItemViewHolder>() {

    fun addItem(item: ShoppingItemModel) {

        val id = shoppingListDAO.addShoppingItem(item);
        notifyItemInserted(id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, id: Int) {
        val currentItem = shoppingListDAO.getItem(id)
        holder.textView.text = currentItem?.name

        holder.itemView.setOnClickListener {
            if(currentItem != null)
                listener.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return shoppingListDAO.getItemsCount(listId)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textViewName)
    }
}
