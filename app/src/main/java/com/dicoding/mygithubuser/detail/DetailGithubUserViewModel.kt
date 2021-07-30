package com.dicoding.mygithubuser.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.mygithubuser.core.domain.model.GithubDetail
import com.dicoding.mygithubuser.core.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailGithubUserViewModel @Inject constructor(private val githubUseCase: GithubUseCase) : ViewModel() {

    fun getDetailGithubUser(query: String) = githubUseCase.getDetailGithubUser(query).asLiveData()

    fun setFavoriteGithubUser(githubDetail: GithubDetail, newStatus: Boolean) =
        githubUseCase.setFavoriteGithubUser(githubDetail, newStatus)
}