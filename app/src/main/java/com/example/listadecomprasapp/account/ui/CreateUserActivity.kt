package com.example.listadecomprasapp.account.ui

import android.os.Bundle
import android.util.Patterns
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

        binding.buttonCreateAccount.setOnClickListener {
            val name = binding.editTextName.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            if (validateInput(name, email, password, confirmPassword)) {
                val user = User(0, name, email, password)
                val userId = userDAO.createUser(user)
                showToast("Usuário de Id: $userId criado com sucesso!")
                finish()
            }
        }
    }

    private fun validateInput(name: String, email: String, password: String, confirmPassword: String): Boolean {
        if (name.isEmpty()) {
            showToast("Por favor, preencha o campo Nome.")
            return false
        }
        if (email.isEmpty()) {
            showToast("Por favor, preencha o campo E-mail.")
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Por favor, insira um e-mail válido.")
            return false
        }
        if (password.isEmpty()) {
            showToast("Por favor, preencha o campo Senha.")
            return false
        }
        if (confirmPassword.isEmpty()) {
            showToast("Por favor, preencha o campo Confirmação de Senha.")
            return false
        }
        if (password != confirmPassword) {
            showToast("A senha e a confirmação de senha não coincidem.")
            return false
        }
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
