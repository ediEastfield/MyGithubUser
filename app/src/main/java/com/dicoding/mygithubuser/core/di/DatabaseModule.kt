package com.dicoding.mygithubuser.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.mygithubuser.core.data.source.local.room.GithubDao
import com.dicoding.mygithubuser.core.data.source.local.room.GithubDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GithubDatabase = Room.databaseBuilder(
        context,
        GithubDatabase::class.java,
        "Github.db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideGithubDao(database: GithubDatabase): GithubDao = database.githubDao()

}