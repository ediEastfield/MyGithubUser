package com.dicoding.mygithubuser.core.data.source.local.room

import androidx.room.*
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubDetailEntity
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubFollowerEntity
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubFollowingEntity
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubSearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubDao {

    @Query("SELECT * FROM githubSearch WHERE login LIKE '%' || :query || '%'")
    fun getAllGithubUser(query: String): Flow<List<GithubSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubUser(githubUser: List<GithubSearchEntity>)

    @Query("SELECT * FROM githubDetail WHERE login = :query")
    fun getDetailGithubUser(query: String): Flow<GithubDetailEntity>

    @Query("SELECT * FROM githubDetail WHERE isFavorite = 1")
    fun getFavoriteGithubUser(): Flow<List<GithubSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailGithubUser(detailGithub: GithubDetailEntity)

    @Update
    fun updateFavoriteGithubUser(githubDetail: GithubDetailEntity)

    @Query("SELECT * FROM githubFollower WHERE follower =:query")
    fun getAllFollower(query: String): Flow<List<GithubFollowerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubFollower(githubFollower: List<GithubFollowerEntity>)

    @Query("SELECT * FROM githubFollowing WHERE following =:query")
    fun getAllFollowing(query: String): Flow<List<GithubFollowingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubFollowing(githubFollowing: List<GithubFollowingEntity>)

}