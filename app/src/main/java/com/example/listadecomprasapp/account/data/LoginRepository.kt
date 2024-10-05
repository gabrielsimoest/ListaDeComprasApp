package com.example.listadecomprasapp.account.data

import com.example.listadecomprasapp.account.data.model.LoggedInUser
import com.example.listadecomprasapp.user.UserDAO

class LoginRepository(private val userDAO: UserDAO) {

    var user: LoggedInUser? = null
        private set

    init {
        user = null
    }

    fun logout() {
        user = null
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        val user = userDAO.getUser(username, password)

        return if (user != null) {
            val loggedInUser = LoggedInUser(
                userId = user.id,
                displayName = user.name
            )

            setLoggedInUser(loggedInUser)
            Result.Success(loggedInUser)
        } else {
            Result.Error(exception = Exception("Usuário ou senha inválidos"))
        }
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }
}