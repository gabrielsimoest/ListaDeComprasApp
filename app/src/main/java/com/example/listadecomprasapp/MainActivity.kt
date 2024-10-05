package com.example.listadecomprasapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listadecomprasapp.account.ui.LoginActivity
import com.example.listadecomprasapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        val intent = Intent(
            this,
            LoginActivity::class.java
        )

        startActivity(intent)
    }
}