package com.example.listadecomprasapp.account.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listadecomprasapp.databinding.ActivityAccountBinding
import com.example.listadecomprasapp.user.UserDAO
import com.example.listadecomprasapp.user.model.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding

    @Inject
    lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener(){
            finish()
        }

        binding.buttonCreateAccount.setOnClickListener(){
            val user = User(
                0,
                binding.editTextName.text.toString(),
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString()
            )

            val userId = userDAO.createUser(user)
            Toast.makeText(this, "Usuario de Id: ${userId} criado com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }

}