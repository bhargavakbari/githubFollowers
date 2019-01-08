package com.android.githubfollowersapp.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.githubfollowersapp.adapters.RecyclerViewClickListener

open abstract class BaseViewHolder : RecyclerView.ViewHolder {

    constructor(itemView: View, mContext: Context) : super(itemView)

    open fun initView() {}

    open fun initDataForRecyclerView(items: List<Any>) {}

    open fun currentItemPosition(position: Int) {}

    open fun currentItemPositionAndData(items: List<Any>, position: Int) {}

    open fun itemClickListenerCallBack(clickListener: RecyclerViewClickListener?) {}

}