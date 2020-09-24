package com.jay.inmobochallenge.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jay.inmobochallenge.data.Posts
import com.jay.inmobochallenge.databinding.ItemPostBinding

class PostAdapter : ListAdapter<Posts,PostAdapter.PostViewHolder>(PostDiffCallBack()) {

    class PostViewHolder private constructor(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun postBind(post: Posts) {
            binding.posts = post
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup):PostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPostBinding.inflate(layoutInflater, parent, false)
                return PostViewHolder(binding)
            }
        }
    }

    class PostDiffCallBack : DiffUtil.ItemCallback<Posts>() {
        override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
     return PostViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.postBind(getItem(position))
    }



}