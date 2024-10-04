package com.example.listadecomprasapp.shoppinglist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listadecomprasapp.account.data.LoginRepository
import com.example.listadecomprasapp.databinding.ActivityEditItemBinding
import com.example.listadecomprasapp.shoppinglist.data.ShoppingListDAO
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingItemModel
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingListModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditItemBinding
    private var listId: Int? = null
    private var itemId: Int? = null

    @Inject
    lateinit var shoppingListDAO: ShoppingListDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listId = intent.getIntExtra("listId", -1)
        itemId = intent.getIntExtra("Id", -1)

        if (itemId != null && itemId != -1) {
            val currentItem = shoppingListDAO.getItem(itemId ?: 0);
            binding.inputNome.setText(currentItem?.name)
            binding.inputDescricao.setText(currentItem?.description)
        }

        if (listId != null && listId != -1) {
            binding.btnSalvar.setOnClickListener() {
                val newItem = ShoppingItemModel(
                    0,
                    listId ?: 0,
                    binding.inputNome.text.toString(),
                    binding.inputDescricao.text.toString()
                )

                val listId = shoppingListDAO.addShoppingItem(newItem)
                Toast.makeText(this, "Item ${listId} criado", Toast.LENGTH_SHORT).show()
            }
        }
    }

}