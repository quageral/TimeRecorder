package com.example.timeapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.*
import java.util.*

class HistoryFragment: Fragment(), View.OnClickListener  {

    private var showdailog: Button? = null

    //选择日期Dialog
    private lateinit var tableRecyclerView: RecyclerView
    private var datePickerDialog: DatePickerDialog? = null
    private var calendar: Calendar? = null
    //定义一堆时间对象，后面会从数据库获取这些对象
    private val timeList = listOf(
        Time(
            1,
            "09:20",
            "11:00",
            "学习"
        ),
        Time(
            2,
            "11:00",
            "11:10",
            "学习"
        ),
        Time(
            3,
            "11:10",
            "12:20",
            "学习"
        ),
        Time(
            4,
            "12:20",
            "13:20",
            "学习"
        ),
        Time(
            5,
            "11:00",
            "23:59",
            "学习"
        ),
        Time(
            6,
            "23:20",
            "01:00",
            "学习"
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        tableRecyclerView = view.findViewById(R.id.table_recycle_view)
        showdailog = view.findViewById<Button>(R.id.showdailog)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //为历史界面的表格设置线性布局
        table_recycle_view.layoutManager = LinearLayoutManager(context)
        //用适配器将时间信息加载到布局
        table_recycle_view.adapter = TableAdapter(timeList)
        //历史记录界面的日历
        showdailog!!.setOnClickListener(this)
        calendar = Calendar.getInstance()
    }

    inner class TableAdapter(private val timeList : List<Time>): RecyclerView.Adapter<TableAdapter.TableViewHolder>(){

        inner class TableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            //接收到传递进来的tableItemView，进行渲染操作（输入内容）
            val number:TextView = itemView.findViewById(R.id.number)
            val start:TextView = itemView.findViewById(R.id.start)
            val end:TextView = itemView.findViewById(R.id.end)
            val length:TextView = itemView.findViewById(R.id.length)
            val type:TextView = itemView.findViewById(R.id.type)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
            //获取到一整个table_view布局，进行返回
            val tableItemView = LayoutInflater.from(parent.context).inflate(R.layout.table_item,parent,false)
            return TableViewHolder(tableItemView)
        }

        override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
            //把数据跟视图进行绑定
            val time = timeList[position]
            holder.number.text = time.number.toString()
            holder.start.text = time.start
            holder.end.text = time.end
            holder.length.text = calcLength(time.start,time.end)
            holder.type.text = time.type


        }

        override fun getItemCount()= timeList.size


    }

    //按下按钮弹出日期选择界面
    override fun onClick(v: View) {
        when (v.id) {
            R.id.showdailog -> showDailog()
        }
    }

    private fun showDailog() {
        datePickerDialog = context?.let {
            DatePickerDialog(
                it, { view, year, monthOfYear, dayOfMonth -> //monthOfYear 得到的月份会减1所以我们要加1
                    val time =
                        year.toString() + "　" + (monthOfYear + 1).toString() + "  " + Integer.toString(
                            dayOfMonth
                        )
                    Log.d("测试", time)
                },
                calendar!![Calendar.YEAR],
                calendar!![Calendar.MONTH],
                calendar!![Calendar.DAY_OF_MONTH]
            )
        }
        datePickerDialog!!.show()
        //自动弹出键盘问题解决
        datePickerDialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }


}