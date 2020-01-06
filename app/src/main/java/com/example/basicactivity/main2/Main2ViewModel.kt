package com.example.basicactivity.main2

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.basicactivity.fragment.AFragment

class Main2ViewModel : ViewModel() {
    var currFragment: Fragment = AFragment.newInstance()
}
