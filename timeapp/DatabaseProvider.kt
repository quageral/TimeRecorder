//package com.example.timeapp

//import android.content.ContentProvider
//import android.content.ContentValues
//import android.content.UriMatcher
//import android.net.Uri
//import android.util.Log
//import androidx.core.content.contentValuesOf
//
//////        添加数据按钮监听
////addData.setOnClickListener {
////    // 添加数据
////    val uri = Uri.parse("content://com.example.filepersistencetest.provider/student")
////    val values = contentValuesOf("name" to "陈廷墀",
////        "number" to 20192005319)
////    val newUri = contentResolver.insert(uri, values)
////    studentId = newUri?.pathSegments?.get(1)
////}
////
////
////
//////        查询数据按钮监听
////queryData.setOnClickListener {
////    // 查询数据
////    val uri = Uri.parse("content://com.example.filepersistencetest.provider/student")
////    contentResolver.query(uri, null, null, null, null)?.apply {
////        while (moveToNext()) {
////            val name = getString(getColumnIndex("name"))
////            val number = getString(getColumnIndex("number"))
////            Log.d("MainActivity", "student name is $name")
////            Log.d("MainActivity", "student number is $number")
////        }
////        close()
////    }
////}
////updateData.setOnClickListener {
////    // 更新数据
////    studentId?.let {
////        val uri = Uri.parse("content://com.example.filepersistencetest.provider/student/$it")
////        val values = contentValuesOf("name" to "A Storm of Swords",
////            "number" to 20192005319)
////        contentResolver.update(uri, values, null, null)
////    }
////}
//////        删除数据按钮监听
////deleteData.setOnClickListener {
////    // 删除数据
////    studentId?.let {
////        val uri = Uri.parse("content://com.example.filepersistencetest.provider/student/$it")
////        contentResolver.delete(uri, null, null)
////    }
////}
//
//class DatabaseProvider : ContentProvider() {
//
//    private val studentDir = 0
//    private val studentItem = 1
//    private val categoryDir = 2
//    private val categoryItem = 3
//    private val authority = "com.example.kotlintime.provider"
//    private var dbHelper: DatabaseHelper? = null
//    private val uriMatcher by lazy {
//        val matcher = UriMatcher(UriMatcher.NO_MATCH)
//        matcher.addURI(authority, "student", studentDir)
//        matcher.addURI(authority, "student/#", studentItem)
//        matcher.addURI(authority, "category", categoryDir)
//        matcher.addURI(authority, "category/#", categoryItem)
//        matcher
//    }
//    override fun onCreate() = context?.let {
//        dbHelper = DatabaseHelper(it, "Time.db", 1)
//        true
//    } ?: false
//    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?) = dbHelper?.let {
//        // 查询数据
//        val db = it.readableDatabase
//        val cursor = when (uriMatcher.match(uri)) {
//            studentDir -> db.query("Student", projection, selection, selectionArgs,
//                    null, null, sortOrder)
//            studentItem -> {
//                val studentId = uri.pathSegments[1]
//                db.query("Student", projection, "id = ?", arrayOf(studentId), null, null,
//                        sortOrder)
//            }
//            categoryDir -> db.query("Category", projection, selection, selectionArgs,
//                    null, null, sortOrder)
//            categoryItem -> {
//                val categoryId = uri.pathSegments[1]
//                db.query("Category", projection, "id = ?", arrayOf(categoryId),
//                        null, null, sortOrder)
//            }
//            else -> null
//        }
//        cursor
//    }
//    override fun insert(uri: Uri, values: ContentValues?) = dbHelper?.let {
//        // 添加数据
//        val db = it.writableDatabase
//        val uriReturn = when (uriMatcher.match(uri)) {
//            studentDir, studentItem -> {
//                val newStudentId = db.insert("Student", null, values)
//                Uri.parse("content://$authority/student/$newStudentId")
//            }
//            categoryDir, categoryItem -> {
//                val newCategoryId = db.insert("Category", null, values)
//                Uri.parse("content://$authority/category/$newCategoryId")
//            }
//            else -> null
//        }
//        uriReturn
//    }
//    override fun update(uri: Uri, values: ContentValues?, selection: String?,
//                        selectionArgs: Array<String>?) = dbHelper?.let {
//        // 更新数据
//        val db = it.writableDatabase
//        val updatedRows = when (uriMatcher.match(uri)) {
//            studentDir -> db.update("Student", values, selection, selectionArgs)
//            studentItem -> {
//                val studentId = uri.pathSegments[1]
//                db.update("Student", values, "id = ?", arrayOf(studentId))
//            }
//            categoryDir -> db.update("Category", values, selection, selectionArgs)
//            categoryItem -> {
//                val categoryId = uri.pathSegments[1]
//                db.update("Category", values, "id = ?", arrayOf(categoryId))
//            }
//            else -> 0
//        }
//        updatedRows
//    } ?: 0
//    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?)
//            = dbHelper?.let {
//        // 删除数据
//        val db = it.writableDatabase
//        val deletedRows = when (uriMatcher.match(uri)) {
//            studentDir -> db.delete("Student", selection, selectionArgs)
//            studentItem -> {
//                val studentId = uri.pathSegments[1]
//                db.delete("Student", "id = ?", arrayOf(studentId))
//            }
//            categoryDir -> db.delete("Category", selection, selectionArgs)
//            categoryDir -> db.delete("Category", selection, selectionArgs)
//            categoryDir -> db.delete("Category", selection, selectionArgs)
//            categoryItem -> {
//                val categoryId = uri.pathSegments[1]
//                db.delete("Category", "id = ?", arrayOf(categoryId))
//            }
//            else -> 0
//        }
//        deletedRows
//    } ?: 0
//
//    override fun getType(uri: Uri) = when (uriMatcher.match(uri)) {
//        studentDir -> "vnd.android.cursor.dir/vnd.com.example.filepersistencetest.provider.student"
//        studentItem -> "vnd.android.cursor.item/vnd.com.example.filepersistencetest.provider.student"
//        categoryDir -> "vnd.android.cursor.dir/vnd.com.example.filepersistencetest.provider.category"
//        categoryItem -> "vnd.android.cursor.item/vnd.com.example.filepersistencetest.provider.category"
//        else -> null
//    }
//
//}