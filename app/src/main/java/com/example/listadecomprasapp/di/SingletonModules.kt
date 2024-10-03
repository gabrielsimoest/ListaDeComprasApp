package com.example.listadecomprasapp.di

import com.example.listadecomprasapp.account.data.LoginRepository
import com.example.listadecomprasapp.shoppinglist.data.ShoppingListDAO
import com.example.listadecomprasapp.shoppinglist.data.ShoppingListMemoryDAOImpl
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
abstract class SingletonModules {

    @Binds
    @Singleton
    abstract fun bindUserDAO(userDAOImpl: UserMemoryDAOImpl): UserDAO

    @Binds
    @Singleton
    abstract fun bindShoppingListDAO(shoppingListDAO: ShoppingListMemoryDAOImpl): ShoppingListDAO

    companion object {
        @Provides
        @Singleton
        fun provideUserMemoryDAO(): UserMemoryDAOImpl {
            return UserMemoryDAOImpl()
        }

        @Provides
        @Singleton
        fun providesLoginRepository(userDAO: UserDAO): LoginRepository {
            return LoginRepository(userDAO)
        }

        @Provides
        @Singleton
        fun provideShoppingListMemoryDAOImpl(): ShoppingListMemoryDAOImpl {
            return ShoppingListMemoryDAOImpl()
        }
    }

}