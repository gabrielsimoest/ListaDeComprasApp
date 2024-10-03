package com.example.listadecomprasapp.list.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listadecomprasapp.databinding.ActivityEditItemBinding

class EditItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}