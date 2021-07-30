package com.dicoding.mygithubuser.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.mygithubuser.R
import com.dicoding.mygithubuser.core.domain.model.GithubSearch
import com.dicoding.mygithubuser.databinding.ItemListGithubUserBinding

class GithubSearchAdapter : RecyclerView.Adapter<GithubSearchAdapter.ListViewHolder>() {

    private var listData = ArrayList<GithubSearch>()
    var onItemClick: ((GithubSearch) -> Unit)? = null

    fun setData(newListData: List<GithubSearch>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_github_user, viewGroup, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(listViewHolder: ListViewHolder, position: Int) {
        val data = listData[position]
        listViewHolder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListGithubUserBinding.bind(itemView)
        fun bind(data: GithubSearch) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.avatarUrl)
                    .into(ivItemImage)
                tvItemLogin.text = data.login
                tvItemUrl.text = data.htmlUrl
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}