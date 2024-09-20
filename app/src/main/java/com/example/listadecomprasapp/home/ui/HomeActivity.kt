package com.example.listadecomprasapp.home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecomprasapp.databinding.ActivityHomeBinding
import com.example.listadecomprasapp.home.data.ItemAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemsList.layoutManager = LinearLayoutManager(this)
        itemAdapter = ItemAdapter()
        binding.itemsList.adapter = itemAdapter

        binding.addButton.setOnClickListener {
            val newItem = "Item de Exemplo " + (itemAdapter.itemCount + 1)
            itemAdapter.addItem(newItem)
        }
    }
}
