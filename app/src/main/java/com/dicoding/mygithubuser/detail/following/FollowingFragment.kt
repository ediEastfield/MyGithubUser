package com.dicoding.mygithubuser.detail.following

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
import com.dicoding.mygithubuser.databinding.FragmentFollowingBinding
import com.dicoding.mygithubuser.detail.DetailGithubUserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingFragment(private val username: String) : Fragment() {

    private val followingViewModel: FollowingViewModel by viewModels()

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val followingAdapter = GithubSearchAdapter()
            followingAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailGithubUserActivity::class.java)
                intent.putExtra(DetailGithubUserActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            followingViewModel.getFollowings(username).observe(viewLifecycleOwner, { following ->
                if (following != null) {
                    when (following) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            followingAdapter.setData(following.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = following.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvFollowings) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = followingAdapter
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}