package com.example.basicactivity.list

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.basicactivity.User
import kotlinx.android.synthetic.main.row_layout.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name = itemView.name
    private val age = itemView.age

    init {
        itemView.setOnClickListener {
            Toast.makeText(
                itemView.context,
                "${name.text} ${age.text}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun onBind(user: User){
        name.text = user.name
        age.text = (user.age ?: 0).toString()
    }
}