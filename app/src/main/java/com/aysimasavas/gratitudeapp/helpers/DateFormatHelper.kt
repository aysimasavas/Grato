package com.aysimasavas.gratitudeapp.helpers

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class DateFormatHelper {

    var formatString:String="dd MM yyyy"

    @SuppressLint("SimpleDateFormat")
    val simpleDateFormat=SimpleDateFormat(formatString)


    fun dateToString(date: Date):String
    {
        return simpleDateFormat.format(date)
    }

    fun stringToDate(str:String):Date
    {
        return simpleDateFormat.parse(str)
    }

    fun calendarToString(day:Int,month:Int,year:Int):String{

        val calendar=Calendar.getInstance()

        calendar.set(Calendar.DAY_OF_MONTH,day)
        calendar.set(Calendar.MONTH,month)
        calendar.set(Calendar.YEAR,year)

        return simpleDateFormat.format(calendar.time)
    }

    fun stringToCalendar(str:String):Calendar
    {

        val calendar=Calendar.getInstance()

        var date=simpleDateFormat.parse(str)

        calendar.time=date

        return calendar

    }

}