package com.example.listadecomprasapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listadecomprasapp.databinding.ActivityMainBinding
import com.example.listadecomprasapp.home.data.ListaDeCompras
import com.example.listadecomprasapp.home.data.ListasAdapter
import com.example.listadecomprasapp.login.ui.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listasAdapter: ListasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

        // RecyclerView para exibir as listas de compras
        binding.listsRecyclerView.layoutManager = GridLayoutManager(this, 2)

        val listasDeCompras = listOf(
            ListaDeCompras("Semanal", R.drawable.ic_placeholder_image),
            ListaDeCompras("Supermercado", R.drawable.ic_placeholder_image),
            ListaDeCompras("Feira", R.drawable.ic_placeholder_image),
            ListaDeCompras("Título", R.drawable.ic_placeholder_image),
            ListaDeCompras("Title", R.drawable.ic_placeholder_image),
            ListaDeCompras("Title", R.drawable.ic_placeholder_image)
        )

        listasAdapter = ListasAdapter(listasDeCompras)
        binding.listsRecyclerView.adapter = listasAdapter

        // Configura o botão flutuante para adicionar uma nova lista
        binding.fab.setOnClickListener {

        }
    }
}

/* o que estava antes:
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(
            this,
            LoginActivity::class.java
        )

        startActivity(intent)
    }

}
 */