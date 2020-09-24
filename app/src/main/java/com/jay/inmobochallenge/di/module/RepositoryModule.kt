package com.jay.inmobochallenge.di.module


import com.jay.inmobochallenge.data.database.PostDatabaseDao
import com.jay.inmobochallenge.repository.Repository
import com.jay.inmobochallenge.repository.RepositoryImp
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(retrofit: Retrofit,postDatabaseDao: PostDatabaseDao):Repository {
        return RepositoryImp(retrofit,postDatabaseDao)
    }
}