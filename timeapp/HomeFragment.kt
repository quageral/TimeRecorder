package com.example.timeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class HomeFragment: Fragment() {

    private val fragmentList = ArrayList<Fragment>()
    lateinit var tabLayout:TabLayout
    lateinit var viewPager:ViewPager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

//        view.findViewById<TabLayout>(R.id.)
//        view.findViewById<ViewPager>()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 在这里添加对象
    }
}