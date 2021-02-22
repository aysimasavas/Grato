package com.aysimasavas.gratitudeapp.view

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aysimasavas.gratitudeapp.R

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


        lateinit var sharedPreferences: SharedPreferences
        val themeKey="currentTheme"



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        sharedPreferences = getSharedPreferences(
                "ThemePref",
                Context.MODE_PRIVATE
        )


        when (sharedPreferences.getString(themeKey, "purple")) {
            "serenity" -> theme.applyStyle(R.style.Serenity, true)
            "violetGarden" -> theme.applyStyle(R.style.VioletGarden, true)
            "orangeCake" -> theme.applyStyle(R.style.OrangeCake, true)
            "deepBlue" -> theme.applyStyle(R.style.DeepBlue, true)
            "green" -> theme.applyStyle(R.style.Theme_GratitudeApp, true)
            "sweetDreams" -> theme.applyStyle(R.style.SweetDreams, true)
            "sweetBrowny" -> theme.applyStyle(R.style.SweetBrowny, true)
            "sunLight" -> theme.applyStyle(R.style.sunLight, true)
        }


        setContentView(R.layout.activity_main)

    }


}