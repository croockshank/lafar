package com.genadidharma.lafar.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genadidharma.lafar.data.Person
import com.genadidharma.lafar.databinding.ItemPersonProfileBinding

class FriendsAttachmentAdapter : RecyclerView.Adapter<FriendsAttachmentAdapter.FriendsAttachmentAdapterViewHolder>() {

    private var list: List<Person?> = mutableListOf()

    override fun getItemCount(): Int = list.size

    fun submitList(attachment: List<Person?>) {
        list = attachment
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsAttachmentAdapterViewHolder {
        return FriendsAttachmentAdapterViewHolder(ItemPersonProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        ))
    }

    override fun onBindViewHolder(holder: FriendsAttachmentAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class FriendsAttachmentAdapterViewHolder(
            private val binding: ItemPersonProfileBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(attachment: Person?) {
            binding.person = attachment
        }
    }
}