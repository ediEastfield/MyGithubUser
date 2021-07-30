package com.dicoding.mygithubuser.core.di

import com.dicoding.mygithubuser.core.data.GithubRepository
import com.dicoding.mygithubuser.core.domain.repository.IGithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(githubRepository: GithubRepository): IGithubRepository

}