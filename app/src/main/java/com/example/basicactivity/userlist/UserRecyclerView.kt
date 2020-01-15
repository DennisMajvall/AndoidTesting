package com.example.basicactivity.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicactivity.R
import com.example.basicactivity.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserRecyclerView(
    private val items: Array<User>, // 1. Change the class used by the Array
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<UserRecyclerView.ViewHolder>() {

    // 2. Choose XML here
    val xml_layout = R.layout.item_user;

    // 3. Customize the callback-function to send your type of Data (instead of User)
    interface OnItemClickListener { fun onUserItemClicked(user: User)  }

    // 4. Customize the ViewHolder to match your xml-layout
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.name
        private val age = itemView.age

        fun bind(user: User) {
            name.text = user.name
            age.text = (user.age ?: 0).toString()

            itemView.setOnClickListener {
                itemClickListener.onUserItemClicked(user)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(xml_layout, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}