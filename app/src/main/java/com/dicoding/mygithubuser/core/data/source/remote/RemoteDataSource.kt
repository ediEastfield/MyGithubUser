package com.dicoding.mygithubuser.core.data.source.remote

import android.util.Log
import com.dicoding.mygithubuser.core.data.source.remote.network.ApiResponse
import com.dicoding.mygithubuser.core.data.source.remote.network.ApiService
import com.dicoding.mygithubuser.core.data.source.remote.response.GithubDetailResponse
import com.dicoding.mygithubuser.core.data.source.remote.response.GithubSearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllGithubUser(query: String): Flow<ApiResponse<List<GithubSearchResponse>>> {
        return flow {
            try {
                val response = apiService.getListGithubUser(query)
                val dataArray = response.items
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.items))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailGithubUser(query: String?): Flow<ApiResponse<GithubDetailResponse>> {
        return flow {
            try {
                val response = apiService.getDetailGithubUser(query)
                emit(ApiResponse.Success(response))
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFollower(query: String?): Flow<ApiResponse<List<GithubSearchResponse>>> {
        return  flow {
            try {
                val response = apiService.getFollower(query)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFollowing(query: String?): Flow<ApiResponse<List<GithubSearchResponse>>> {
        return flow {
            try {
                val response = apiService.getFollowing(query)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}