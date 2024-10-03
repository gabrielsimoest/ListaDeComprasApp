package com.example.listadecomprasapp.shoppinglist.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecomprasapp.databinding.ActivityListsHomeBinding
import com.example.listadecomprasapp.shoppinglist.data.ItemAdapter
import com.example.listadecomprasapp.shoppinglist.data.OnItemClickListener
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingItemModel

class ListActivity : AppCompatActivity(), OnItemClickListener {
    private var id: Int? = null
    private lateinit var binding: ActivityListsHomeBinding
    private lateinit var itemAdapter: ItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListsHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemsList.layoutManager = LinearLayoutManager(this)
        itemAdapter = ItemAdapter(this)
        binding.itemsList.adapter = itemAdapter

        binding.addButton.setOnClickListener {
            val newIdList = itemAdapter.itemCount + 1;
            val newList = ShoppingItemModel(id = newIdList, name = "Item de Exemplo " + newIdList, description = "Sem descricao")
            itemAdapter.addItem(newList)
        }

        binding.addList.setOnClickListener {
            val intent = Intent(
                this,
                EditItemActivity::class.java
            )

            startActivity(intent)
        }

        id = intent.getIntExtra("Id", 0)

        if (id != null && id != 0) {

        }
    }

    override fun onItemClick(item: ShoppingItemModel) {
        Toast.makeText(this, "Clicou no item: ${item.name}", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, EditItemActivity::class.java).apply {
            putExtra("Id", item.id)
        }

        startActivity(intent)
    }
}