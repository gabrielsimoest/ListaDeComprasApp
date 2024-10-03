package com.example.listadecomprasapp.user

import com.example.listadecomprasapp.user.model.User

interface UserDAO {
    fun createUser(user: User) : Int
    fun getUser(id: Int) : User?
}