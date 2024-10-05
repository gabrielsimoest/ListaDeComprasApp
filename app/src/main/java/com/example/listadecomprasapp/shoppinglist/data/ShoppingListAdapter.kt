package com.example.listadecomprasapp.shoppinglist.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listadecomprasapp.R
import com.example.listadecomprasapp.account.data.LoginRepository
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingListModel

class ShoppingListAdapter(
    private val shoppingListDAO: ShoppingListDAO,
    private val loginRepository: LoginRepository,
    private val listener: OnListClickListener
) : RecyclerView.Adapter<ShoppingListAdapter.ItemViewHolder>() {

    private var shoppingLists: List<ShoppingListModel> = shoppingListDAO.getLists(loginRepository.user?.userId ?: 0)
    private var filteredShoppingLists: List<ShoppingListModel> = shoppingLists

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = filteredShoppingLists[position]
        holder.textView.text = currentItem.name

        currentItem.imageUrl?.let { imageUrl ->
            Glide.with(holder.itemView.context)
                .load(imageUrl)
                .into(holder.imageView)
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return filteredShoppingLists.size
    }

    fun updateLists() {
        val newLists = shoppingListDAO.getLists(loginRepository.user?.userId ?: 0)
        shoppingLists = newLists.sortedByDescending { it.name }
        filteredShoppingLists = shoppingLists.distinctBy { it.id }
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredShoppingLists = if (query.isEmpty()) {
            shoppingLists
        } else {
            shoppingLists.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.item_text)
        var imageView: ImageView = itemView.findViewById(R.id.list_image)
    }
}
