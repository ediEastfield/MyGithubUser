package com.dicoding.mygithubuser.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubDetailEntity
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubFollowerEntity
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubFollowingEntity
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubSearchEntity

@Database(
    entities = [
        GithubSearchEntity::class,
        GithubDetailEntity::class,
        GithubFollowerEntity::class,
        GithubFollowingEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun githubDao(): GithubDao

}