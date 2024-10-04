package com.example.listadecomprasapp.user

import com.example.listadecomprasapp.user.model.User

class UserMemoryDAOImpl : UserDAO {
    private val userList: MutableList<User> = mutableListOf()

    override fun createUser(user: User): Int {
        if (userList.any { it.email == user.email }) {
            throw IllegalArgumentException("Usuário com o email: ${user.email} já existente!.")
        }

        val id = userList.size + 1
        val newUser = user.copy(id = id)
        userList.add(newUser)

        return id
    }

    override fun getUser(id: Int): User? {
        return userList.find { it.id == id }
    }

    override fun getUser(email: String, password: String): User? {
        return userList.find { it.email == email && it.password == password }
    }
}
