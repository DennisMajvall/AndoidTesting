package com.example.basicactivity

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action3", Snackbar.LENGTH_LONG)
                .setAction("Action") {
                    println("sup!")
                }.show()
        }

        btn_toast.setOnClickListener {
            Toast.makeText(it.context, getString(R.string.toast_hello), Toast.LENGTH_LONG).show()
            val intent = Intent(this, Main2Activity::class.java).apply {
                putExtra(EXTRA_MESSAGE, textView.text)
            }
            startActivity(intent)
        }

        btn_increment.setOnClickListener(this::count)
        btn_random.setOnClickListener(this::randomNumber)

        randomNumber(null)
    }

    private fun count(view: View){
        val curr = Integer.parseInt(textView.text.toString())
        textView.text = (curr+1).toString()
    }

    private fun randomNumber(view: View?){
        textView.text = ((Math.random() * 100.0).toInt().toString())
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
