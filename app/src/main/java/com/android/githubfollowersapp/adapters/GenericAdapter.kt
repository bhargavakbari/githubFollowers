package com.android.githubfollowersapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.githubfollowersapp.viewHolders.BaseViewHolder


class GenericAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    val TAG = "GenericAdapter"
    var clickListener: RecyclerViewClickListener? = null
    var listSize: Int = 0
    var items: List<Any>? = null
    var context: Context? = null
    var layoutId: Int = 1
    var className: String = ""
    var item: Any? = null
    var baseViewHolder: BaseViewHolder? = null

    constructor(items: List<Any>?, context: Context, layoutId: Int, className: String, clickListener: RecyclerViewClickListener? = null) {
        this.clickListener = clickListener
        if (items != null) this.listSize = items.size
        else this.listSize = 0
        this.items = items
        this.context = context
        this.layoutId = layoutId
        this.className = className
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(layoutId, parent, false)
        val c = Class.forName(className).getConstructor(View::class.java, Context::class.java)
        baseViewHolder = c.newInstance(view, context) as BaseViewHolder
        baseViewHolder?.initView()
        if (items != null) baseViewHolder?.initDataForRecyclerView(items!!)
        baseViewHolder?.itemClickListenerCallBack(clickListener)
        return baseViewHolder!!
    }

    override fun getItemCount(): Int {
        return listSize
    }

    fun refreshRecyclerView(items: List<Any>) {
        this.listSize = items.size
        this.items = items
        baseViewHolder?.initDataForRecyclerView(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val baseViewHolder = holder as BaseViewHolder
        baseViewHolder.currentItemPosition(position)
        baseViewHolder.currentItemPositionAndData(items!!, position)
    }

}