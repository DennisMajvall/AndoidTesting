package com.example.basicactivity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.basicactivity.R
import kotlinx.android.synthetic.main.content_main.*

class BFragment : Fragment() {

    companion object {
        fun newInstance() = BFragment()
    }

    private lateinit var viewModel: BViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.b_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BViewModel::class.java)
        textView.text = getString(R.string.msg_hello_from, viewModel.text)
    }

}
