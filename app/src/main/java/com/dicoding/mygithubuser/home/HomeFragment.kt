package com.dicoding.mygithubuser.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mygithubuser.R
import com.dicoding.mygithubuser.core.data.Resource
import com.dicoding.mygithubuser.core.ui.GithubSearchAdapter
import com.dicoding.mygithubuser.databinding.FragmentHomeBinding
import com.dicoding.mygithubuser.detail.DetailGithubUserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val githubSearchAdapter = GithubSearchAdapter()
            githubSearchAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailGithubUserActivity::class.java)
                intent.putExtra(DetailGithubUserActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            binding.viewEmpty.root.visibility = View.VISIBLE

            binding.tlSearch.setEndIconOnClickListener {
                val query = binding.etSearch.text
                Toast.makeText(requireContext(),query, Toast.LENGTH_SHORT).show()

                homeViewModel.githubSearch(query.toString()).observe(viewLifecycleOwner, { github ->
                    if (github != null) {
                        binding.viewEmpty.root.visibility = View.GONE
                        when (github) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                githubSearchAdapter.setData(github.data)
                            }
                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                                binding.viewError.root.visibility = View.VISIBLE
                                binding.viewError.tvError.text = github.message ?: getString(R.string.something_wrong)
                            }
                        }
                    }
                })
            }

            with(binding.rvGithubUser) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = githubSearchAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}