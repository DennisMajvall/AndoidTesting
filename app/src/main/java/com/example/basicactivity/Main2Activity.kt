package com.example.basicactivity

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicactivity.fragment.AFragment
import com.example.basicactivity.fragment.BFragment
import com.example.basicactivity.intents.EXTRA_MESSAGE
import com.example.basicactivity.list.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*


class Main2Activity : AppCompatActivity() {
    lateinit var adapter: RecyclerViewAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setFragment(AFragment.newInstance())
        button.setOnClickListener(this::onButtonClick)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        textView2.text = getString(R.string.msg_concat, message)

        createRecyclerView()
    }

    private fun createRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        adapter = RecyclerViewAdapter(users)
        recyclerView.adapter = adapter
    }

    private fun setFragment(fragNewInstance: Fragment, tag: String = "") {
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(
            R.id.fragmentContainer,
            fragNewInstance,
            tag.ifEmpty { fragNewInstance.javaClass.simpleName }
        )

        transaction.commit()
    }

    private fun toggleFragmentAB(): String {

        val curr: Fragment? = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
//        val newFrag: Fragment = if (curr is AFragment) BFragment.newInstance() else AFragment.newInstance() // ternary instead of switch-case
        val newFrag: Fragment = when (curr) {
            is AFragment -> BFragment.newInstance()
            else -> AFragment.newInstance()
        }

        setFragment(newFrag)
        return curr?.tag.toString()
    }

    private fun onButtonClick(view: View) {
        val s = toggleFragmentAB()
        (view as Button).text = "Change to $s"
    }
}
