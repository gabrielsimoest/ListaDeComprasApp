package com.example.listadecomprasapp.shoppinglist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listadecomprasapp.databinding.ActivityEditListBinding

class EditListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}