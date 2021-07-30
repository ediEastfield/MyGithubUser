package com.dicoding.mygithubuser.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubDetail(

    val githubId: String,
    val followers: Int,
    val avatarUrl: String,
    val followingUrl: String,
    val following: Int,
    val name: String?,
    val company: String?,
    val location: String?,
    val publicRepos: Int,
    val login: String,
    val followersUrl: String,
    val htmlUrl: String,
    val isFavorite: Boolean

): Parcelable