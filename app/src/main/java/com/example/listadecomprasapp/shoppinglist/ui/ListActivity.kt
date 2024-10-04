package com.example.listadecomprasapp.shoppinglist.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecomprasapp.databinding.ActivityListsHomeBinding
import com.example.listadecomprasapp.shoppinglist.data.ShoppingItemAdapter
import com.example.listadecomprasapp.shoppinglist.data.OnItemClickListener
import com.example.listadecomprasapp.shoppinglist.data.ShoppingListDAO
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingItemModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListActivity : AppCompatActivity(), OnItemClickListener {
    private var listId: Int? = null
    private lateinit var binding: ActivityListsHomeBinding
    private lateinit var shoppingItemAdapter: ShoppingItemAdapter

    @Inject
    lateinit var shoppingListDAO: ShoppingListDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListsHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemsList.layoutManager = LinearLayoutManager(this)

        binding.addList.setOnClickListener {
            val intent = Intent(
                this,
                EditItemActivity::class.java
            ).apply {
                putExtra("listId", listId)
            }

            startActivity(intent)
        }

        listId = intent.getIntExtra("listId", -1)

        if (listId != null && listId != -1) {
            shoppingItemAdapter = ShoppingItemAdapter(shoppingListDAO, listId!!, this)
            binding.itemsList.adapter = shoppingItemAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        shoppingItemAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(item: ShoppingItemModel) {
        Toast.makeText(this, "Clicou no item: ${item.name}", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, EditItemActivity::class.java).apply {
            putExtra("Id", item.id)
            putExtra("listId", listId)
        }

        startActivity(intent)
    }
}