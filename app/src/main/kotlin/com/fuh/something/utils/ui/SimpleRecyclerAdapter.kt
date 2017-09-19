package com.fuh.something.utils.ui

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by nick on 15.09.17.
 */
abstract class SimpleRecyclerAdapter<VH: SimpleRecyclerAdapter.ViewHolder<M>, M>(
        @LayoutRes private val layoutId: Int,
        initialData: List<M> = listOf()
) : RecyclerView.Adapter<VH>() {

    private val items: MutableList<M> = initialData.toMutableList()

    abstract fun createHolder(itemView: View): VH

    fun addItems(vararg data: M) {
        val insertPosition = items.lastIndex
        items.addAll(data)

        notifyItemRangeInserted(insertPosition, data.size)
    }

    fun addItems(data: Iterable<M>) {
        val insertPosition = items.lastIndex
        items.addAll(data)

        val insertedRange = items.lastIndex - insertPosition
        notifyItemRangeInserted(insertPosition, insertedRange)
    }

    fun replaceItems(data: Iterable<M>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return createHolder(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val data = items[position]

        holder.bind(data)
    }

    override fun getItemCount(): Int = items.size

    abstract class ViewHolder<M>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bind(data: M)
    }
}