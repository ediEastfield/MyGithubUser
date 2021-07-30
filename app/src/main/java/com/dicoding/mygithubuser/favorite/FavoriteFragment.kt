package com.dicoding.mygithubuser.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mygithubuser.core.ui.GithubSearchAdapter
import com.dicoding.mygithubuser.databinding.FragmentFavoriteBinding
import com.dicoding.mygithubuser.detail.DetailGithubUserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
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

            favoriteViewModel.favoriteGithubUser.observe(viewLifecycleOwner, { dataGithub ->
                githubSearchAdapter.setData(dataGithub)
                binding.viewEmpty.root.visibility = if (dataGithub.isNotEmpty()) View.GONE else View.VISIBLE
            })

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