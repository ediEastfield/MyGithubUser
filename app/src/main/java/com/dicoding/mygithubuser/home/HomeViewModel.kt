package com.dicoding.mygithubuser.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.mygithubuser.core.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val githubUseCase: GithubUseCase) : ViewModel() {
    fun githubSearch(query: String) = githubUseCase.getAllGithubUser(query).asLiveData()
}