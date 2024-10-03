package com.example.listadecomprasapp.user

import com.example.listadecomprasapp.user.model.User

class UserMemoryDAOImpl : UserDAO {
    private val userList: MutableList<User> = ArrayList()

    override fun createUser(user: User) : Int {
        val id = userList.size + 1;
        val newUser = user.copy(id = id)
        userList.add(newUser)

        return id
    }

    override fun getUser(id: Int) : User? {
        return userList.find { it.id == id }
    }
}