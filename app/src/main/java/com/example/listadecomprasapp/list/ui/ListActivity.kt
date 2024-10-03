package com.example.listadecomprasapp.list.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecomprasapp.R
import com.example.listadecomprasapp.databinding.ActivityHomeBinding
import com.example.listadecomprasapp.home.data.ListHomeAdapter
import com.example.listadecomprasapp.home.data.ListModel
import com.example.listadecomprasapp.list.data.ItemAdapter
import com.example.listadecomprasapp.list.data.ItemModel
import com.example.listadecomprasapp.list.data.OnItemClickListener

class ListActivity : AppCompatActivity(), OnItemClickListener {
    private var id: Int? = null
    private lateinit var binding: ActivityHomeBinding
    private lateinit var itemAdapter: ItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemsList.layoutManager = LinearLayoutManager(this)
        itemAdapter = ItemAdapter(this)
        binding.itemsList.adapter = itemAdapter

        binding.addButton.setOnClickListener {
            val newIdList = itemAdapter.itemCount + 1;
            val newList = ItemModel(id = newIdList, name = "Item de Exemplo " + newIdList, description = "Sem descricao")
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

    override fun onItemClick(item: ItemModel) {
        Toast.makeText(this, "Clicou no item: ${item.name}", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, EditItemActivity::class.java).apply {
            putExtra("Id", item.id)
        }

        startActivity(intent)
    }
}