package com.example.basicactivity.main2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicactivity.R
import com.example.basicactivity.User
import com.example.basicactivity.fragment.AFragment
import com.example.basicactivity.fragment.BFragment
import com.example.basicactivity.intents.EXTRA_MESSAGE
import com.example.basicactivity.retrofit.MarvelRetrofit
import com.example.basicactivity.retrofit.MarvelService
import com.example.basicactivity.userlist.UserRecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class Main2Activity : AppCompatActivity(), UserRecyclerView.OnItemClickListener {
    private lateinit var userRecyclerView: UserRecyclerView
    private lateinit var viewModel: Main2ViewModel
    private val users = arrayOf(
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

        viewModel = ViewModelProviders.of(this).get(Main2ViewModel::class.java)
        setFragment(viewModel.currFragment)

        button.setOnClickListener(this::onButtonClick)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        textView2.text = getString(R.string.msg_concat, message)

        createRecyclerView()
        testRetrofit()
    }

    private fun testRetrofit() {

        MarvelRetrofit.marvelService.getAllCharacters()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result, err ->
                if (err?.message != null) Log.d("__", "Error getAllCharacters " + err.message)
                else {
                    Log.d("___", "I got a CharacterDataWrapper $result")
                }
            }
    }

    private fun createRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        userRecyclerView = UserRecyclerView(users, this)
        recyclerView.adapter = userRecyclerView
    }

    private fun setFragment(newInstanceOfFragment: Fragment, tag: String = "") {
        viewModel.currFragment = newInstanceOfFragment
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.fragmentContainer,
            newInstanceOfFragment,
            tag.ifEmpty { newInstanceOfFragment.javaClass.simpleName }
        )

        transaction.commit()
    }

    private fun toggleFragmentAB(): String {

//        val curr: Fragment? = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
//        val newFrag: Fragment = if (curr is AFragment) BFragment.newInstance() else AFragment.newInstance() // ternary instead of switch-case

        val curr = viewModel.currFragment
        val newFrag: Fragment = when (curr) {
            is AFragment -> BFragment.newInstance()
            else -> AFragment.newInstance()
        }

        setFragment(newFrag)
        return curr.tag.toString()
    }

    private fun onButtonClick(view: View) {
        val s = toggleFragmentAB()
        (view as Button).text = getString(R.string.button_change_to, s)
//        (view as Button).text = "Change to $s"
    }

    override fun onUserItemClicked(user: User) {
        Toast.makeText(
            this,
            "${user.name} ${user.age}",
            Toast.LENGTH_SHORT
        ).show()
    }
}
