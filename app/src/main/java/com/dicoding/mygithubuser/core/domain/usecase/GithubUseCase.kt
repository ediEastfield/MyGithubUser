package com.dicoding.mygithubuser.core.domain.usecase

import com.dicoding.mygithubuser.core.data.Resource
import com.dicoding.mygithubuser.core.domain.model.GithubDetail
import com.dicoding.mygithubuser.core.domain.model.GithubSearch
import kotlinx.coroutines.flow.Flow

interface GithubUseCase {

    fun getAllGithubUser(query: String): Flow<Resource<List<GithubSearch>>>

    fun getFavoriteGithubUser(): Flow<List<GithubSearch>>

    fun setFavoriteGithubUser(githubDetail: GithubDetail, state: Boolean)

    fun getDetailGithubUser(query: String): Flow<Resource<GithubDetail>>

    fun getFollower(query: String): Flow<Resource<List<GithubSearch>>>

    fun getFollowing(query: String): Flow<Resource<List<GithubSearch>>>

}