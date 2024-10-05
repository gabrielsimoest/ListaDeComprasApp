package com.example.listadecomprasapp.shoppinglist.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listadecomprasapp.account.data.LoginRepository
import com.example.listadecomprasapp.databinding.ActivityListsHomeBinding
import com.example.listadecomprasapp.shoppinglist.data.ShoppingListAdapter
import com.example.listadecomprasapp.shoppinglist.data.OnListClickListener
import com.example.listadecomprasapp.shoppinglist.data.ShoppingListDAO
import com.example.listadecomprasapp.shoppinglist.data.model.ShoppingListModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListsHomeActivity : AppCompatActivity(), OnListClickListener {

    private lateinit var binding: ActivityListsHomeBinding
    private lateinit var shoppingListAdapter: ShoppingListAdapter

    @Inject
    lateinit var shoppingListDAO: ShoppingListDAO

    @Inject
    lateinit var loginRepository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListsHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoutButton.setOnClickListener(){
            loginRepository.logout()
            finish()
        }

        binding.itemsList.layoutManager = GridLayoutManager(this, 2)
        shoppingListAdapter = ShoppingListAdapter(shoppingListDAO, loginRepository, this)
        binding.itemsList.adapter = shoppingListAdapter

        binding.addList.setOnClickListener {
            val intent = Intent(
                this,
                EditListActivity::class.java
            )

            startActivity(intent)
        }

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                shoppingListAdapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    override fun onResume() {
        super.onResume()
        shoppingListAdapter.updateLists()
    }

    override fun onItemClick(list: ShoppingListModel) {
        Toast.makeText(this, "Clicou na lista: ${list.name}", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, ListActivity::class.java).apply {
            putExtra("listId", list.id)
        }

        startActivity(intent)
    }
}
