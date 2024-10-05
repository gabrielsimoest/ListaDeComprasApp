package com.example.listadecomprasapp.shoppinglist.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecomprasapp.R
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingItemModel

class ShoppingItemAdapter(
    private val shoppingListDAO: ShoppingListDAO,
    private val listId: Int,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ShoppingItemAdapter.ItemViewHolder>() {

    private var items: List<ShoppingItemModel> = shoppingListDAO.getItems(listId)
    private var filteredItems: List<ShoppingItemModel> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.textView.text = currentItem.name
        holder.textDescription.text = "${currentItem.quantity} ${currentItem.unit}"

        holder.checkBox.isChecked = currentItem.isChecked

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            currentItem?.isChecked = isChecked
            shoppingListDAO.updateShoppingItem(currentItem)
            updateItems()
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun filter(query: String) {
        filteredItems = if (query.isEmpty()) {
            items
        } else {
            items.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    fun updateItems() {
        items = shoppingListDAO.getItems(listId)
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textViewName)
        var textDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        var checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }
}
