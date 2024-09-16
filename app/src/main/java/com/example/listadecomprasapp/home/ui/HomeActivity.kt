package com.example.listadecomprasapp.home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.listadecomprasapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityHomeBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }
}