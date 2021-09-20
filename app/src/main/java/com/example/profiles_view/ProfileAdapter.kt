package com.example.profiles_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.profiles_view.api.User

class ProfileAdapter(
    private val profiles: MutableList<User>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val itemAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)
        val itemFullname: TextView = itemView.findViewById(R.id.tvProfileFullname)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_profile,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val currentProfile = profiles[position]
        holder.itemFullname.text = currentProfile.fullname

        Glide.with(holder.itemAvatar)
            .load(profiles[position].avatar)
            .into(holder.itemAvatar)
    }

    override fun getItemCount(): Int {
        return profiles.size
    }
}