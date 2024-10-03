package com.example.listadecomprasapp.account.data

import com.example.listadecomprasapp.account.data.model.LoggedInUser
import com.example.listadecomprasapp.user.UserDAO

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(private val userDAO: UserDAO) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
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
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}