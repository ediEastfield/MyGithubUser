package com.dicoding.mygithubuser.di

import com.dicoding.mygithubuser.core.domain.usecase.GithubInteractor
import com.dicoding.mygithubuser.core.domain.usecase.GithubUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideGithubUseCase(githubInteractor: GithubInteractor): GithubUseCase

}