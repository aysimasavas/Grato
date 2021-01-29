package com.aysimasavas.gratitudeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private val rotateOpen: Animation by lazy {AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim)}
    private val rotateClose: Animation by lazy {AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim)}
    private val fromBottom: Animation by lazy {AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim)}
    private val toBottom: Animation by lazy {AnimationUtils.loadAnimation(this,R.anim.to_bottom_anim)}

    private var clicked=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_button.setOnClickListener {
            onAddButtonClicked()
        }
        search_button.setOnClickListener {

            Toast.makeText(this,"searc",Toast.LENGTH_LONG).show()
        }
        settings_button.setOnClickListener {

            Toast.makeText(this,"sett",Toast.LENGTH_LONG).show()
        }

        val navController = Navigation.findNavController(this, R.id.fragment)

    }

    private fun onAddButtonClicked()
    {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked=!clicked
    }

    private fun setVisibility(clicked:Boolean) {
        if(!clicked)

        {
            search_button.visibility= View.VISIBLE
            settings_button.visibility= View.VISIBLE

        }
        else
        {
            search_button.visibility= View.INVISIBLE
            settings_button.visibility= View.INVISIBLE

        }

    }


    private fun setAnimation(clicked:Boolean) {

        if(!clicked)

        {
            search_button.startAnimation(fromBottom)
            settings_button.startAnimation(fromBottom)
            add_button.startAnimation(rotateOpen)

        }
        else
        {
            search_button.startAnimation(toBottom)
            settings_button.startAnimation(toBottom)
            add_button.startAnimation(rotateOpen)
        }
    }

    private fun setClickable(clicked:Boolean)
    {
        if(!clicked)
        {
            search_button.isClickable=true
            settings_button.isClickable=true

        }
        else
        {
            search_button.isClickable=false
            settings_button.isClickable=false
        }
    }


}