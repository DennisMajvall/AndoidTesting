package com.example.basicactivity.userlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicactivity.R
import com.example.basicactivity.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserRecyclerView(
    private val users: Array<User>,
    private val userItemClickListener: OnUserItemClickListener
) : RecyclerView.Adapter<UserRecyclerView.ViewHolder>() {

    // OnUserItemClickListener contains the array of ViewHolders (One ViewHolder for every item)
    // ViewHolder is the class binding together the View (xml) with the Model (data)
    // OnUserItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("__", "onBindViewHolder")
        holder.bind(users[position], userItemClickListener)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.name
        private val age = itemView.age

        fun bind(user: User, clickListenerUser: OnUserItemClickListener) {
            name.text = user.name
            age.text = (user.age ?: 0).toString()

            itemView.setOnClickListener {
                clickListenerUser.onUserItemClicked(user)
            }
        }
    }


    interface OnUserItemClickListener {
        fun onUserItemClicked(user: User)
    }
}