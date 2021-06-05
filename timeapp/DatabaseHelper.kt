//package com.example.timeapp
//
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import android.widget.Toast
//
//class DatabaseHelper (context: Context, name:String, version:Int):
//        SQLiteOpenHelper(context,name,null,version){
//    private val createStu = "create table Time (" +
//            " id integer primary key autoincrement," +  //仅作为自增主键
//            " day text," +  //如：2021年6月5日
//            " start text," + //如：09:05
//            " end text," +   //如：12:12
//            " type text)"   //如：学习
//
//
//
//    override fun onCreate(db:SQLiteDatabase){
//        db.execSQL(createStu)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        db.execSQL("drop table if exists Time")
//        onCreate(db)
//    }
//}