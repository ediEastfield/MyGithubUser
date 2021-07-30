package com.dicoding.mygithubuser.core.data

import com.dicoding.mygithubuser.core.data.source.local.LocalDataSource
import com.dicoding.mygithubuser.core.data.source.remote.RemoteDataSource
import com.dicoding.mygithubuser.core.data.source.remote.network.ApiResponse
import com.dicoding.mygithubuser.core.data.source.remote.response.GithubDetailResponse
import com.dicoding.mygithubuser.core.data.source.remote.response.GithubSearchResponse
import com.dicoding.mygithubuser.core.domain.model.GithubDetail
import com.dicoding.mygithubuser.core.domain.model.GithubSearch
import com.dicoding.mygithubuser.core.domain.repository.IGithubRepository
import com.dicoding.mygithubuser.core.utils.AppExecutors
import com.dicoding.mygithubuser.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGithubRepository {

    override fun getAllGithubUser(query: String): Flow<Resource<List<GithubSearch>>> =
        object : NetworkBoundResource<List<GithubSearch>, List<GithubSearchResponse>>() {
            override fun loadFromDB(): Flow<List<GithubSearch>> {
                return localDataSource.getAllGithubUser(query).map {
                    DataMapper.mapGithubSearchEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<GithubSearch>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<GithubSearchResponse>>> =
                remoteDataSource.getAllGithubUser(query)

            override suspend fun saveCallResult(data: List<GithubSearchResponse>) {
                val githubSearchList = DataMapper.mapGithubSearchResponsesToEntities(data)
                localDataSource.insertGithubUser(githubSearchList)
            }
        }.asFlow()

    override fun getFavoriteGithubUser(): Flow<List<GithubSearch>> {
        return localDataSource.getFavoriteGithubUser().map {
            DataMapper.mapGithubSearchEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGithubUser(githubDetail: GithubDetail, state: Boolean) {
        val githubDetailEntity = DataMapper.mapGithubDetailDomainToEntity(githubDetail)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGithubUser(githubDetailEntity, state) }
    }

    override fun getDetailGithubUser(query: String): Flow<Resource<GithubDetail>> =
        object : NetworkBoundResource<GithubDetail, GithubDetailResponse>() {
            override fun loadFromDB(): Flow<GithubDetail> {
                return localDataSource.getDetailGithubUser(query).map { detailGithub ->
                    if (detailGithub != null) DataMapper.mapDetailGithubEntitiesToDomain(detailGithub) else detailGithub
                }
            }

            override fun shouldFetch(data: GithubDetail?): Boolean =
                data?.login == null || data.login.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<GithubDetailResponse>> =
                remoteDataSource.getDetailGithubUser(query)

            override suspend fun saveCallResult(data: GithubDetailResponse) {
                val detailGithub = DataMapper.mapDetailGithubResponseToEntities(data)
                localDataSource.insertDetailGithubUser(detailGithub)
            }
        }.asFlow()

    override fun getFollower(query: String): Flow<Resource<List<GithubSearch>>> =
        object : NetworkBoundResource<List<GithubSearch>, List<GithubSearchResponse>>() {
            override fun loadFromDB(): Flow<List<GithubSearch>> {
                return localDataSource.getFollower(query).map {
                    DataMapper.mapGithubFollowerEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<GithubSearch>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GithubSearchResponse>>> =
                remoteDataSource.getFollower(query)

            override suspend fun saveCallResult(data: List<GithubSearchResponse>) {
                val githubList = DataMapper.mapGithubFollowResponsesToEntities(data,query)
                localDataSource.insertGithubFollower(githubList)
            }
        }.asFlow()

    override fun getFollowing(query: String): Flow<Resource<List<GithubSearch>>> =
        object : NetworkBoundResource<List<GithubSearch>, List<GithubSearchResponse>>() {
            override fun loadFromDB(): Flow<List<GithubSearch>> {
                return localDataSource.getFollowing(query).map {
                    DataMapper.mapGithubFollowingEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<GithubSearch>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GithubSearchResponse>>> =
                remoteDataSource.getFollowing(query)

            override suspend fun saveCallResult(data: List<GithubSearchResponse>) {
                val githubList = DataMapper.mapGithubFollowingResponseToEntities(data, query)
                localDataSource.insertGithubFollowing(githubList)
            }
        }.asFlow()

}