package com.techmaster.interviewassignment.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.techmaster.interviewassignment.R
import com.techmaster.interviewassignment.data.PostModels
import com.techmaster.interviewassignment.databinding.PostitemsBinding

class PostAdapter : PagingDataAdapter<PostModels, PostAdapter.ItemViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = PostitemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }

          holder.itemView.setOnClickListener {
              val intent = Intent(holder.parent.context, DetailScreen::class.java)
              intent.putExtra("itemData", item)
              holder.parent.context.startActivity(intent)
          }
    }

    inner class ItemViewHolder(private val binding: PostitemsBinding, val parent: ViewGroup) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PostModels) {
            //THere is no requirement to handle complex task still if required can use coroutine i am not using
            //here because i found that test case useless for this usecase
            binding.apply {
                title.text = item.title
                userId.text =
                    binding.userId.context.getString(R.string.user_id, item.userId.toString())
                postid.text = binding.userId.context.getString(R.string.post_id, item.id.toString())
                body.text = item.body
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PostModels>() {
            override fun areItemsTheSame(oldItem: PostModels, newItem: PostModels): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: PostModels, newItem: PostModels): Boolean {
                return oldItem == newItem
            }
        }
    }
}