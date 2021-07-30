package com.dicoding.mygithubuser.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.mygithubuser.R
import com.dicoding.mygithubuser.core.data.Resource
import com.dicoding.mygithubuser.core.domain.model.GithubDetail
import com.dicoding.mygithubuser.core.domain.model.GithubSearch
import com.dicoding.mygithubuser.databinding.ActivityDetailGithubUserBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailGithubUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    private val detailGithubUserViewModel: DetailGithubUserViewModel by viewModels()

    private lateinit var binding: ActivityDetailGithubUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGithubUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarDetail.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        val detailGithubUser = intent.getParcelableExtra<GithubSearch>(EXTRA_DATA)
        detailGithubUserViewModel.getDetailGithubUser(detailGithubUser!!.login).observe(this, { detailGithub ->
            if (detailGithub != null) {
                when (detailGithub) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        showDetailGithub(detailGithub.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = detailGithub.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        val sectionsPagerAdapter = SectionsPagerAdapter(detailGithubUser.login, this)
        binding.appBarDetail.content.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.appBarDetail.content.tabs, binding.appBarDetail.content.viewPager) { tab, pos ->
            tab.text = resources.getString(TAB_TITLES[pos])
        }.attach()
    }

    private fun showDetailGithub(detailGithub: GithubDetail?) {
        detailGithub?.let {
            Glide.with(this)
                .load(detailGithub.avatarUrl)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.appBarDetail.ivDetailImage)

            supportActionBar?.title = detailGithub.login
            binding.appBarDetail.tvDetailName.text = detailGithub.name
            binding.appBarDetail.tvDetailLocation.text = detailGithub.location
            binding.appBarDetail.tvDetailCompany.text = detailGithub.company
            binding.appBarDetail.cardBarDetail.tvTotalFollowers.text = detailGithub.followers.toString()
            binding.appBarDetail.cardBarDetail.tvTotalFollowing.text = detailGithub.following.toString()
            binding.appBarDetail.cardBarDetail.tvTotalRepository.text = detailGithub.publicRepos.toString()

            var statusFavorite = detailGithub.isFavorite
            setStatusFavorite(statusFavorite)
            binding.appBarDetail.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailGithubUserViewModel.setFavoriteGithubUser(detailGithub, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.appBarDetail.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.appBarDetail.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}