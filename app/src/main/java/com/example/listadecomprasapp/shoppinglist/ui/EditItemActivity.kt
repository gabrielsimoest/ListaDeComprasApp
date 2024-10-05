package com.example.listadecomprasapp.shoppinglist.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listadecomprasapp.R
import com.example.listadecomprasapp.databinding.ActivityEditItemBinding
import com.example.listadecomprasapp.shoppinglist.data.ShoppingListDAO
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingItemModel
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
            binding.btnExcluir.visibility = View.VISIBLE
            binding.btnExcluir.setOnClickListener {
                shoppingListDAO.removeShoppingItem(itemId ?: 0)
                finish()
            }

            val currentItem = shoppingListDAO.getItem(itemId ?: 0)
            currentItem?.let {
                binding.inputNome.setText(it.name)
                binding.inputQuantidade.setText(it.quantity.toString())
                binding.inputCategoria.setSelection(getCategoryPosition(it.category))
                binding.inputUnidade.setSelection(getCategoryPosition(it.unit))
            }
        }

        binding.btnSalvar.setOnClickListener {
            if (listId != null && listId != -1) {
                val name = binding.inputNome.text.toString()
                val quantity = binding.inputQuantidade.text.toString().toDoubleOrNull() ?: 0.0
                val unit = binding.inputUnidade.selectedItem.toString()
                val category = binding.inputCategoria.selectedItem.toString()

                if (name.isNotEmpty() && unit.isNotEmpty() && category.isNotEmpty()) {
                    val newItem = ShoppingItemModel(
                        id = itemId ?: 0,
                        listId = listId ?: 0,
                        name = name,
                        quantity = quantity,
                        unit = unit,
                        category = category,
                        isChecked = false
                    )

                    if (itemId == null || itemId == -1) {
                        shoppingListDAO.addShoppingItem(newItem)
                        Toast.makeText(this, "Item criado", Toast.LENGTH_SHORT).show()
                    } else {
                        shoppingListDAO.updateShoppingItem(newItem)
                        Toast.makeText(this, "Item atualizado", Toast.LENGTH_SHORT).show()
                    }

                    finish()
                } else {
                    Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getCategoryPosition(category: String): Int {
        val categories = resources.getStringArray(R.array.categorias_array)
        return categories.indexOf(category).takeIf { it != -1 } ?: 0
    }
}
