package com.dicoding.mygithubuser.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.mygithubuser.core.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(githubUseCase: GithubUseCase) : ViewModel() {
    val favoriteGithubUser = githubUseCase.getFavoriteGithubUser().asLiveData()
}