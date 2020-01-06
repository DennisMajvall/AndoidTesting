package com.example.basicactivity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.basicactivity.R
import kotlinx.android.synthetic.main.content_main.*

class AFragment : Fragment() {

    companion object {
        fun newInstance() = AFragment()
    }

    private lateinit var viewModel: AViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.a_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AViewModel::class.java)
        textView.text = getString(R.string.msg_hello_from, viewModel.text)
    }

}
