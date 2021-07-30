package com.dicoding.mygithubuser.core.data.source.local

import com.dicoding.mygithubuser.core.data.source.local.entity.GithubDetailEntity
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubFollowerEntity
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubFollowingEntity
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubSearchEntity
import com.dicoding.mygithubuser.core.data.source.local.room.GithubDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val githubDao: GithubDao) {

    fun getAllGithubUser(query: String): Flow<List<GithubSearchEntity>> = githubDao.getAllGithubUser(query)

    fun getFavoriteGithubUser(): Flow<List<GithubSearchEntity>> = githubDao.getFavoriteGithubUser()

    suspend fun insertGithubUser(githubList: List<GithubSearchEntity>) = githubDao.insertGithubUser(githubList)

    fun setFavoriteGithubUser(githubDetail: GithubDetailEntity, newState: Boolean) {
        githubDetail.isFavorite = newState
        githubDao.updateFavoriteGithubUser(githubDetail)
    }

    fun getDetailGithubUser(query: String): Flow<GithubDetailEntity> = githubDao.getDetailGithubUser(query)

    suspend fun insertDetailGithubUser(detailGithub: GithubDetailEntity) = githubDao.insertDetailGithubUser(detailGithub)

    fun getFollower(query: String): Flow<List<GithubFollowerEntity>> = githubDao.getAllFollower(query)

    suspend fun insertGithubFollower(githubList: List<GithubFollowerEntity>) = githubDao.insertGithubFollower(githubList)

    fun getFollowing(query: String): Flow<List<GithubFollowingEntity>> = githubDao.getAllFollowing(query)

    suspend fun insertGithubFollowing(githubList: List<GithubFollowingEntity>) = githubDao.insertGithubFollowing(githubList)

}