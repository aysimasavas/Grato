package com.aysimasavas.gratitudeapp.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.aysimasavas.gratitudeapp.R

import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.theme_layout.*
import kotlinx.android.synthetic.main.theme_layout.view.*
import java.util.*

class SettingsFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences
    val themeKey="currentTheme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sharedPreferences = requireActivity().getSharedPreferences(
                "ThemePref",
                Context.MODE_PRIVATE
        )


        themeText.setOnClickListener {

            themeTextOnClick()
        }


    }

    private fun themeTextOnClick()
    {

        val dialog = activity?.let { it1 -> Dialog(it1) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)


        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.theme_layout)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(this.resources.getColor(android.R.color.transparent)))



        if (dialog != null) {
            sweetDreamsThemeOnClick(dialog)
        }
        if (dialog != null) {
            greenThemeOnClick(dialog)
        }
        if (dialog != null) {
            deepBlueThemeOnClick(dialog)
        }
        if (dialog != null) {
            violetGardenThemeOnClick(dialog)
        }
        if (dialog != null) {
            serenityThemeOnClick(dialog)
        }
        if (dialog != null) {
            orangeCakeThemeOnClick(dialog)
        }
        if (dialog != null) {
            sweetBrownyThemeOnClick(dialog)
        }
        if (dialog != null) {
            sunLightThemeOnClick(dialog)
        }

        val backButton = dialog?.findViewById(R.id.themeBackButton) as TextView

        backButton.setOnClickListener { dialog.dismiss() }


        dialog?.show()



    }


    fun sweetDreamsThemeOnClick(dialog: Dialog)
    {

        dialog.findViewById<ImageView>(R.id.sweetDreams).sweetDreams.setOnClickListener {

            sharedPreferences.edit().putString(themeKey, "sweetDreams").apply()

            val intent =Intent(activity,MainActivity::class.java)

            activity?.finish()
            startActivity(intent)
            dialog.dismiss()
        }

    }


    fun sweetBrownyThemeOnClick(dialog: Dialog?)
    {
        dialog?.findViewById<ImageView>(R.id.sweetBrowny)?.sweetBrowny?.setOnClickListener {
            sharedPreferences.edit().putString(themeKey, "sweetBrowny").apply()

            val intent =Intent(activity,MainActivity::class.java)

            activity?.finish()
            startActivity(intent)
            dialog.dismiss()
        }

    }
    fun deepBlueThemeOnClick(dialog: Dialog)
    {

        dialog.findViewById<ImageView>(R.id.deepBlue).deepBlue.setOnClickListener {
            sharedPreferences.edit().putString(themeKey, "deepBlue").apply()

            val intent =Intent(activity,MainActivity::class.java)

            activity?.finish()
            startActivity(intent)
            dialog.dismiss()
        }

    }
    fun violetGardenThemeOnClick(dialog: Dialog)
    {

        dialog.findViewById<ImageView>(R.id.violetGarden).violetGarden.setOnClickListener {
            sharedPreferences.edit().putString(themeKey, "violetGarden").apply()

            val intent =Intent(activity,MainActivity::class.java)

            activity?.finish()
            startActivity(intent)
            dialog.dismiss()
        }
    }
    fun orangeCakeThemeOnClick(dialog: Dialog)
    {

        dialog.findViewById<ImageView>(R.id.orangeCake).orangeCake.setOnClickListener {

            sharedPreferences.edit().putString(themeKey, "orangeCake").apply()

            val intent =Intent(activity,MainActivity::class.java)

            activity?.finish()
            startActivity(intent)
            dialog.dismiss()
        }
    }
    fun serenityThemeOnClick(dialog: Dialog)
    {

        dialog.findViewById<ImageView>(R.id.serenity).serenity.setOnClickListener {

            sharedPreferences.edit().putString(themeKey, "serenity").apply()

            val intent =Intent(activity,MainActivity::class.java)

            activity?.finish()
            startActivity(intent)
            dialog.dismiss()
        }
    }

    fun greenThemeOnClick(dialog: Dialog)
    {

        dialog.findViewById<ImageView>(R.id.green).green.setOnClickListener {
            sharedPreferences.edit().putString(themeKey, "green").apply()

            val intent =Intent(activity,MainActivity::class.java)

            activity?.finish()
            startActivity(intent)
            dialog.dismiss()
        }
    }


    fun sunLightThemeOnClick(dialog: Dialog)
    {

        dialog.findViewById<ImageView>(R.id.sunLight).sunLight.setOnClickListener {
            sharedPreferences.edit().putString(themeKey, "sunLight").apply()

            val intent =Intent(activity,MainActivity::class.java)

            activity?.finish()
            startActivity(intent)
            dialog.dismiss()
        }
    }






}