package com.dicoding.mygithubuser.core.data.source.remote.network

import com.dicoding.mygithubuser.core.data.source.remote.response.GithubDetailResponse
import com.dicoding.mygithubuser.core.data.source.remote.response.GithubSearchResponse
import com.dicoding.mygithubuser.core.data.source.remote.response.ListGithubUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getListGithubUser(
        @Query("q") q: String?
    ): ListGithubUserResponse

    @GET("users/{username}")
    suspend fun getDetailGithubUser(
        @Path("username") username: String?
    ): GithubDetailResponse

    @GET("users/{username}/followers")
    suspend fun getFollower(
        @Path("username") username: String?
    ): List<GithubSearchResponse>

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String?
    ): List<GithubSearchResponse>

}