package com.dicoding.mygithubuser.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GithubSearchResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("html_url")
    val htmlUrl: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String

)