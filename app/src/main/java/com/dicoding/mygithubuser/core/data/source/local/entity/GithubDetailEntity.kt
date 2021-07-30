package com.dicoding.mygithubuser.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "githubDetail")
data class GithubDetailEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "githubId")
    var githubId: String,

    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String,

    @ColumnInfo(name = "followersUrl")
    var followersUrl: String,

    @ColumnInfo(name = "followingUrl")
    var followingUrl: String,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "company")
    var company: String?,

    @ColumnInfo(name = "location")
    var location: String?,

    @ColumnInfo(name = "publicRepos")
    var publicRepos: Int,

    @ColumnInfo(name = "followers")
    var followers: Int,

    @ColumnInfo(name = "following")
    var following: Int,

    @ColumnInfo(name = "htmlUrl")
    var htmlUrl: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

)