package com.dicoding.mygithubuser.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.mygithubuser.detail.follower.FollowerFragment
import com.dicoding.mygithubuser.detail.following.FollowingFragment

class SectionsPagerAdapter(private val username: String, activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowerFragment(username)
            1 -> fragment = FollowingFragment(username)
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2

}