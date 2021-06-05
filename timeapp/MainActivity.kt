package com.example.timeapp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_history.*
import java.util.*


class MainActivity : AppCompatActivity() {


    val fragmentList = listOf(HomeFragment(), HistoryFragment(), CountFragment())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val contentViewPager = findViewById<ViewPager>(R.id.content_view_pager)
        contentViewPager.offscreenPageLimit = 3 //设置fragment页面的缓存数量
        contentViewPager.adapter = Adapter(supportFragmentManager)//设置适配器
        val bottomNav =findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                //点击哪一个图标，显示哪一个页面：0、1、2
                // smoothScroll=false这个参数能解决切换时的多页闪烁问题
                R.id.nav_home -> contentViewPager.setCurrentItem(0, false)
                R.id.nav_history -> contentViewPager.setCurrentItem(1, false)
                R.id.nav_count -> contentViewPager.setCurrentItem(2, false)
            }
            false
        }

        //导航栏按钮点击后样式
        contentViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(p: Int, pOffset: Float, pOffsetPixels: Int) {}//无需添加
            override fun onPageScrollStateChanged(state: Int) {}//无需添加
            override fun onPageSelected(position: Int) {
                // 将对应的底部导航栏菜单项设置为选中状态
                bottomNav.menu.getItem(position).isChecked = true
            }
        })

        //设置数据库，未完成
//        val dbHelper = DatabaseHelper(this, "Time.db", 1)
//        dbHelper.writableDatabase


    }

    //未完成的adapter内部类
    inner class Adapter(fm: FragmentManager) : FragmentPagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

    }




}



