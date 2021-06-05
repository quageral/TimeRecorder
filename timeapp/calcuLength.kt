package com.example.timeapp


fun calcLength(start:String, end:String): String {
    var hour = end.subSequence(0,2).toString().toInt()-start.subSequence(0,2).toString().toInt()
    var min = end.subSequence(3,5).toString().toInt()-start.subSequence(3,5).toString().toInt()
    if (hour<0){
        hour += 24
        if (min<0){
            min += 60
            hour -= 1
        }
    }else if (hour==0&&min<0){
        min+=60
        hour+=23
    }else if (min<0){
        min += 60
        hour -= 1
    }
    return when {
        hour==0 -> {
            min.toString()+"min"
        }
        min==0 -> {
            hour.toString()+"h"
        }
        else -> {
            hour.toString()+"h"+min.toString()+"min"
        }
    }

}

