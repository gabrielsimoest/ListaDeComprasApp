package com.example.listadecomprasapp.di

import com.example.listadecomprasapp.user.UserDAO
import com.example.listadecomprasapp.user.UserMemoryDAOImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Binds
    @Singleton
    abstract fun bindUserDAO(userDAOImpl: UserMemoryDAOImpl): UserDAO

    companion object {
        @Provides
        @Singleton
        fun provideUserMemoryDAO(): UserMemoryDAOImpl {
            return UserMemoryDAOImpl()
        }
    }
}