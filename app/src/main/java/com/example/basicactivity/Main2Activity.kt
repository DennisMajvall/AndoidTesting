package com.example.basicactivity

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicactivity.list.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*

class Main2Activity : AppCompatActivity() {
    var users = arrayOf(
        User("Kalle", 35),
        User("Kajsa", 32),
        User("Mimmi", 27),
        User("Musse", 26),
        User("Långben", 40),
        User("Knatte", 12),
        User("Fnatte", 12),
        User("Tjatte", 12),
        User("A", 13),
        User("B", 14),
        User("C", 15),
        User("D", 16),
        User("E", 17),
        User("F", 18),
        User("G", 19),
        User("H", 20),
        User("I", 21)
    )

    lateinit var adapter : RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val message = intent.getStringExtra(EXTRA_MESSAGE)
        textView2.text = getString(R.string.msg_concat, message)

        createRecyclerView()
    }

    private fun createRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(this,  LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        adapter = RecyclerViewAdapter(users)
        recyclerView.adapter = adapter
    }
}
