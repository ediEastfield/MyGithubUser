package com.dicoding.mygithubuser.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGithubUserResponse(

    @field:SerializedName("total_count")
    val totalCount: Int? = null,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @field:SerializedName("items")
    val items: List<GithubSearchResponse>

)