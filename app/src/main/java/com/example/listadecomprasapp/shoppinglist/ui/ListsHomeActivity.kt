package com.example.listadecomprasapp.shoppinglist.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listadecomprasapp.databinding.ActivityListsHomeBinding
import com.example.listadecomprasapp.shoppinglist.data.ShoppingListAdapter
import com.example.listadecomprasapp.shoppinglist.data.OnListClickListener
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingListModel

class ListsHomeActivity : AppCompatActivity(), OnListClickListener {

    private lateinit var binding: ActivityListsHomeBinding
    private lateinit var shoppingListAdapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListsHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemsList.layoutManager = GridLayoutManager(this, 2)
        shoppingListAdapter = ShoppingListAdapter(this)
        binding.itemsList.adapter = shoppingListAdapter

        binding.addButton.setOnClickListener {
            val newIdList = shoppingListAdapter.itemCount + 1;
            val newList = ShoppingListModel(id = newIdList, name = "Item de Exemplo " + newIdList)
            shoppingListAdapter.addItem(newList)
        }

        binding.addList.setOnClickListener {
            val intent = Intent(
                this,
                EditListActivity::class.java
            )

            startActivity(intent)
        }
    }

    override fun onItemClick(list: ShoppingListModel) {
        Toast.makeText(this, "Clicou na lista: ${list.name}", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, ListActivity::class.java).apply {
            putExtra("Id", list.id)
        }

        startActivity(intent)
    }
}
