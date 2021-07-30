package com.dicoding.mygithubuser.detail.follower

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mygithubuser.R
import com.dicoding.mygithubuser.core.data.Resource
import com.dicoding.mygithubuser.core.ui.GithubSearchAdapter
import com.dicoding.mygithubuser.databinding.FragmentFollowerBinding
import com.dicoding.mygithubuser.detail.DetailGithubUserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerFragment(private val username: String) : Fragment() {

    private val followerViewModel: FollowerViewModel by viewModels()

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val followerAdapter = GithubSearchAdapter()
            followerAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailGithubUserActivity::class.java)
                intent.putExtra(DetailGithubUserActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }


            followerViewModel.getFollowers(username).observe(viewLifecycleOwner, { follower ->
                if (follower != null) {
                    when (follower) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            followerAdapter.setData(follower.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = follower.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvFollowers) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = followerAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}