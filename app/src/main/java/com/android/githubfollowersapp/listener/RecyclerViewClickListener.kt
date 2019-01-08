package com.android.githubfollowersapp.adapters

import android.view.View

interface RecyclerViewClickListener {
    fun onItemClicked(view: View, position: Int, any: Any);
}
