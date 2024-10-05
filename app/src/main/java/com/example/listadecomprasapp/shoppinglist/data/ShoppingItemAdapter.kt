package com.example.listadecomprasapp.shoppinglist.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
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
        val currentItem = filteredItems[position]
        holder.textView.text = currentItem.name
        holder.textDescription.text = "${currentItem.quantity} ${currentItem.unit}"

        holder.checkBox.isChecked = currentItem.isChecked

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            currentItem.isChecked = isChecked
            shoppingListDAO.updateShoppingItem(currentItem)
            updateItems()
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return filteredItems.size
    }

    fun updateItems() {
        val newItems = shoppingListDAO.getItems(listId)
        val diffResult = DiffUtil.calculateDiff(ShoppingItemDiffCallback(items, newItems))
        items = newItems
        filteredItems = items
        diffResult.dispatchUpdatesTo(this)
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

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textViewName)
        var textDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        var checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }

    class ShoppingItemDiffCallback(
        private val oldList: List<ShoppingItemModel>,
        private val newList: List<ShoppingItemModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
