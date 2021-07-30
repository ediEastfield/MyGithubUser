package com.dicoding.mygithubuser.core.domain.usecase

import com.dicoding.mygithubuser.core.domain.model.GithubDetail
import com.dicoding.mygithubuser.core.domain.repository.IGithubRepository
import javax.inject.Inject

class GithubInteractor @Inject constructor(private val githubRepository: IGithubRepository) : GithubUseCase {

    override fun getAllGithubUser(query: String) = githubRepository.getAllGithubUser(query)

    override fun getFavoriteGithubUser() = githubRepository.getFavoriteGithubUser()

    override fun setFavoriteGithubUser(githubDetail: GithubDetail, state: Boolean) = githubRepository.setFavoriteGithubUser(githubDetail, state)

    override fun getDetailGithubUser(query: String) = githubRepository.getDetailGithubUser(query)

    override fun getFollower(query: String) = githubRepository.getFollower(query)

    override fun getFollowing(query: String) = githubRepository.getFollowing(query)

}