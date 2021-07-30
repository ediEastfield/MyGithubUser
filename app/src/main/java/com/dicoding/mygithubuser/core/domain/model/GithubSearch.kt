package com.dicoding.mygithubuser.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubSearch(

    val githubId: String,
    val login: String,
    val htmlUrl: String,
    val avatarUrl: String

): Parcelable