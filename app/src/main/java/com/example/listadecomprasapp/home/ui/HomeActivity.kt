package com.example.listadecomprasapp.home.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listadecomprasapp.databinding.ActivityHomeBinding
import com.example.listadecomprasapp.home.data.ListHomeAdapter
import com.example.listadecomprasapp.home.data.ListModel
import com.example.listadecomprasapp.home.data.OnListClickListener
import com.example.listadecomprasapp.list.ui.EditListActivity
import com.example.listadecomprasapp.list.ui.ListActivity

class HomeActivity : AppCompatActivity(), OnListClickListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var listHomeAdapter: ListHomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemsList.layoutManager = GridLayoutManager(this, 2)
        listHomeAdapter = ListHomeAdapter(this)
        binding.itemsList.adapter = listHomeAdapter

        binding.addButton.setOnClickListener {
            val newIdList = listHomeAdapter.itemCount + 1;
            val newList = ListModel(id = newIdList, name = "Item de Exemplo " + newIdList)
            listHomeAdapter.addItem(newList)
        }

        binding.addList.setOnClickListener {
            val intent = Intent(
                this,
                EditListActivity::class.java
            )

            startActivity(intent)
        }
    }

    override fun onItemClick(list: ListModel) {
        Toast.makeText(this, "Clicou na lista: ${list.name}", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, ListActivity::class.java).apply {
            putExtra("Id", list.id)
        }

        startActivity(intent)
    }
}
