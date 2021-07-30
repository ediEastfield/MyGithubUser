package com.dicoding.mygithubuser.detail.following

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.mygithubuser.core.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(private val githubUseCase: GithubUseCase) : ViewModel() {
    fun getFollowings(query: String) = githubUseCase.getFollowing(query).asLiveData()
}