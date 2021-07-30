package com.dicoding.mygithubuser.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "githubFollower")
data class GithubFollowerEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "githubId")
    var githubId: String,

    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "htmlUrl")
    var htmlUrl: String,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String,

    @ColumnInfo(name = "follower")
    var follower: String

)