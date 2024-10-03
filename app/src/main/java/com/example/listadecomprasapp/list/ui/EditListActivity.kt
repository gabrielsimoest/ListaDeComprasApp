package com.example.listadecomprasapp.list.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.listadecomprasapp.R
import com.example.listadecomprasapp.databinding.ActivityEditListBinding

class EditListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}