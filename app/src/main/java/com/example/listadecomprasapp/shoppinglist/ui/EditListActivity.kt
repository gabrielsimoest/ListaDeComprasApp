package com.example.listadecomprasapp.shoppinglist.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.listadecomprasapp.account.data.LoginRepository
import com.example.listadecomprasapp.databinding.ActivityEditListBinding
import com.example.listadecomprasapp.shoppinglist.data.ShoppingListDAO
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingListModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditListActivity : AppCompatActivity() {

    private var listId: Int? = null
    private lateinit var binding: ActivityEditListBinding
    private var selectedImageUri: Uri? = null

    @Inject
    lateinit var shoppingListDAO: ShoppingListDAO
    @Inject
    lateinit var loginRepository: LoginRepository

    private val IMAGE_PICK_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listId = intent.getIntExtra("listId", -1)

        binding.btnSelecionarImagem.setOnClickListener {
            pickImageFromGallery()
        }

        if (listId != null && listId != -1) {
            val currentList = shoppingListDAO.getList(listId ?: 0)
            binding.inputNome.setText(currentList?.name)

            if (currentList?.imageUrl != null) {
                Glide.with(binding.selectedImage.context)
                    .load(currentList.imageUrl)
                    .into(binding.selectedImage)
            } else if (selectedImageUri != null) {
                binding.selectedImage.setImageURI(selectedImageUri)
            }

            binding.btnExcluir.visibility = View.VISIBLE

            binding.btnExcluir.setOnClickListener {
                shoppingListDAO.removeShoppingList(listId ?: 0)
                finish()
            }
        } else {
            binding.btnSalvar.setOnClickListener {
                val newList = ShoppingListModel(
                    0,
                    loginRepository.user?.userId ?: 0,
                    binding.inputNome.text.toString(),
                    selectedImageUri?.toString()
                )

                val listId = shoppingListDAO.addShoppingList(newList)
                Toast.makeText(this, "Lista: ${listId} criada", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            selectedImageUri = data?.data

            selectedImageUri?.let { uri ->
                Glide.with(binding.selectedImage.context)
                    .load(uri)
                    .into(binding.selectedImage)
            }
        }
    }
}
