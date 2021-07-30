package com.dicoding.mygithubuser.core.utils

import com.dicoding.mygithubuser.core.data.source.local.entity.GithubDetailEntity
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubFollowerEntity
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubFollowingEntity
import com.dicoding.mygithubuser.core.data.source.local.entity.GithubSearchEntity
import com.dicoding.mygithubuser.core.data.source.remote.response.GithubDetailResponse
import com.dicoding.mygithubuser.core.data.source.remote.response.GithubSearchResponse
import com.dicoding.mygithubuser.core.domain.model.GithubDetail
import com.dicoding.mygithubuser.core.domain.model.GithubSearch

object DataMapper {

    fun mapGithubSearchResponsesToEntities(input: List<GithubSearchResponse>): List<GithubSearchEntity> {
        val githubSearchList = ArrayList<GithubSearchEntity>()
        input.map {
            val githubSearch = GithubSearchEntity(
                githubId = it.id.toString(),
                login = it.login,
                htmlUrl = it.htmlUrl,
                avatarUrl = it.avatarUrl
            )
            githubSearchList.add(githubSearch)
        }
        return githubSearchList
    }

    fun mapGithubFollowResponsesToEntities(input: List<GithubSearchResponse>, follow: String): List<GithubFollowerEntity> {
        val githubFollowerList = ArrayList<GithubFollowerEntity>()
        input.map {
            val githubFollower = GithubFollowerEntity(
                githubId = it.id.toString(),
                login = it.login,
                htmlUrl = it.htmlUrl,
                avatarUrl = it.avatarUrl,
                follower = follow
            )
            githubFollowerList.add(githubFollower)
        }
        return githubFollowerList
    }

    fun mapGithubFollowingResponseToEntities(input: List<GithubSearchResponse>, following: String): List<GithubFollowingEntity> {
        val githubFollowingList = ArrayList<GithubFollowingEntity>()
        input.map {
            val githubFollowing = GithubFollowingEntity(
                githubId = it.id.toString(),
                login = it.login,
                htmlUrl = it.htmlUrl,
                avatarUrl = it.avatarUrl,
                following = following
            )
            githubFollowingList.add(githubFollowing)
        }
        return githubFollowingList
    }

    fun mapDetailGithubResponseToEntities(input: GithubDetailResponse) = GithubDetailEntity(
        githubId = input.id.toString(),
        login = input.login,
        avatarUrl = input.avatarUrl,
        name = input.name,
        location = input.location,
        company = input.company,
        followers = input.followers,
        following = input.following,
        publicRepos = input.publicRepos,
        followersUrl = input.followersUrl,
        followingUrl = input.followingUrl,
        isFavorite = false,
        htmlUrl = input.htmlUrl
    )

    fun mapGithubSearchEntitiesToDomain(input: List<GithubSearchEntity>): List<GithubSearch> =
        input.map {
            GithubSearch(
                githubId = it.githubId,
                login = it.login,
                htmlUrl = it.htmlUrl,
                avatarUrl = it.avatarUrl
            )
        }

    fun mapGithubFollowerEntitiesToDomain(input: List<GithubFollowerEntity>): List<GithubSearch> =
        input.map {
            GithubSearch(
                githubId = it.githubId,
                login = it.login,
                htmlUrl = it.htmlUrl,
                avatarUrl = it.avatarUrl
            )
        }

    fun mapGithubFollowingEntitiesToDomain(input: List<GithubFollowingEntity>): List<GithubSearch> =
        input.map {
            GithubSearch(
                githubId = it.githubId,
                login = it.login,
                htmlUrl = it.htmlUrl,
                avatarUrl = it.avatarUrl
            )
        }

    fun mapDetailGithubEntitiesToDomain(input: GithubDetailEntity) =
        GithubDetail(
            githubId = input.githubId,
            login = input.login,
            avatarUrl = input.avatarUrl,
            name = input.name,
            location = input.location,
            company = input.company,
            followers = input.followers,
            following = input.following,
            publicRepos = input.publicRepos,
            followersUrl = input.followersUrl,
            followingUrl = input.followingUrl,
            isFavorite = input.isFavorite,
            htmlUrl = input.htmlUrl
        )

    fun mapGithubDetailDomainToEntity(input: GithubDetail) = GithubDetailEntity(
        githubId = input.githubId,
        login = input.login,
        avatarUrl = input.avatarUrl,
        name = input.name,
        location = input.location,
        company = input.company,
        followers = input.followers,
        following = input.following,
        publicRepos = input.publicRepos,
        followersUrl = input.followersUrl,
        followingUrl = input.followingUrl,
        isFavorite = input.isFavorite,
        htmlUrl = input.htmlUrl
    )

}