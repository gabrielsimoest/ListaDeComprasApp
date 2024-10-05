package com.example.listadecomprasapp.shoppinglist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listadecomprasapp.account.data.LoginRepository
import com.example.listadecomprasapp.databinding.ActivityEditListBinding
import com.example.listadecomprasapp.shoppinglist.data.ShoppingListDAO
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingListModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditListBinding

    @Inject
    lateinit var shoppingListDAO: ShoppingListDAO
    @Inject
    lateinit var loginRepository: LoginRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener(){
            finish()
        }

        binding.btnSalvar.setOnClickListener(){
            val newList = ShoppingListModel(
                0,
                loginRepository.user?.userId ?: 0,
                binding.inputNome.text.toString()
            )

            val listId = shoppingListDAO.addShoppingList(newList)
            Toast.makeText(this, "lista: ${listId} criada", Toast.LENGTH_SHORT).show()

        }
    }

}