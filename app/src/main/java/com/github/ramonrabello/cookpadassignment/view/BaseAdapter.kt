package com.github.ramonrabello.cookpadassignment.view

import android.support.v7.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    var items:MutableList<T> = mutableListOf()
    override fun getItemCount() = items.size
}