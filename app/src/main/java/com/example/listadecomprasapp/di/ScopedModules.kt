package com.example.listadecomprasapp.di

import com.example.listadecomprasapp.account.data.LoginRepository
import com.example.listadecomprasapp.account.ui.LoginViewModel
import com.example.listadecomprasapp.user.UserDAO
import com.example.listadecomprasapp.user.UserMemoryDAOImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ScopedModules {

    companion object {
        @Provides
        @ActivityRetainedScoped
        fun providesLoginViewModel(loginRepository: LoginRepository): LoginViewModel {
            return LoginViewModel(loginRepository)
        }

        @Provides
        @ActivityRetainedScoped
        fun providesLoginRepository(userDAO: UserDAO): LoginRepository {
            return LoginRepository(userDAO)
        }
    }

}