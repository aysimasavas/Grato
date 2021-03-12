package com.aysimasavas.gratitudeapp.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aysimasavas.gratitudeapp.R


class MainActivity : AppCompatActivity(){

    lateinit var sharedPreferences: SharedPreferences
    val themeKey="currentTheme"



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        sharedPreferences = getSharedPreferences(
                "ThemePref",
                Context.MODE_PRIVATE
        )




        when (sharedPreferences.getString(themeKey, "sunLight")) {
            "serenity" -> theme.applyStyle(R.style.Serenity, true)
            "violetGarden" -> theme.applyStyle(R.style.VioletGarden, true)
            "orangeCake" -> theme.applyStyle(R.style.OrangeCake, true)
            "deepBlue" -> theme.applyStyle(R.style.DeepBlue, true)
            "green" -> theme.applyStyle(R.style.greenDark, true)
            "sweetDreams" -> theme.applyStyle(R.style.SweetDreams, true)
            "sweetBrowny" -> theme.applyStyle(R.style.SweetBrowny, true)
            "sunLight" -> theme.applyStyle(R.style.Theme_GratitudeApp, true)
        }



       setContentView(R.layout.activity_main)


    }



}