package com.dicoding.mygithubuser.detail.follower

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.mygithubuser.core.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel @Inject constructor(private val githubUseCase: GithubUseCase) : ViewModel() {
    fun getFollowers(query: String) = githubUseCase.getFollower(query).asLiveData()
}