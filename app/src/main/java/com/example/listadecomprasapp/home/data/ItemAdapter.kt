package com.example.listadecomprasapp.home.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecomprasapp.R
import com.example.listadecomprasapp.home.data.ItemAdapter.ItemViewHolder

class ItemAdapter : RecyclerView.Adapter<ItemViewHolder>() {
    private val itemList: MutableList<String> =
        ArrayList() // Inicializar a lista de itens

    // Adiciona um item à lista e notifica o RecyclerView
    fun addItem(item: String) {
        itemList.add(item)
        notifyItemInserted(itemList.size - 1) // Notifica a inserção de um item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.textView.text = currentItem
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.item_text)
    }
}