package com.aysimasavas.gratitudeapp.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aysimasavas.gratitudeapp.R
import com.aysimasavas.gratitudeapp.adapter.RecyclerAdapter
import com.aysimasavas.gratitudeapp.helpers.DateFormatHelper
import com.aysimasavas.gratitudeapp.model.NoteModel
import com.aysimasavas.gratitudeapp.service.NoteDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.calender_layout.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.DateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
import kotlin.collections.ArrayList

class HomeFragment : Fragment() ,RecyclerAdapter.Listener{


    private var noteModels:ArrayList<NoteModel>?=null
    private var recyclerAdapter:RecyclerAdapter?=null


    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this.context,R.anim.rotate_open_anim)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this.context,R.anim.rotate_close_anim)}
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this.context,R.anim.from_bottom_anim)}
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this.context,R.anim.to_bottom_anim)}

    private var clicked=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager=LinearLayoutManager(this.context)

        recyclerView.layoutManager=layoutManager

        noteModels= ArrayList()


        val db= context?.let { NoteDatabase.invoke(it?.applicationContext) }

        noteModels=db?.noteDao()?.getAllNotes() as ArrayList<NoteModel>




        val sortedList=noteModels!!.sortedByDescending {

            it.date?.let { it1 -> DateFormatHelper().stringToDate(it1) }
        }

        val aList=ArrayList(sortedList)


        recyclerAdapter=aList?.let { RecyclerAdapter( it,this@HomeFragment) }

        recyclerView.adapter=recyclerAdapter



        add_button.setOnClickListener {
            onAddButtonClicked()
        }

        settings_button.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
            Navigation.findNavController(it).navigate(action)
            clicked=false
        }


        search_button.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            Navigation.findNavController(it).navigate(action)
            clicked=false
        }

        calendar_button.setOnClickListener {

            val dialog = activity?.let { it1 -> Dialog(it1) }
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)


            dialog?.setCancelable(false)
            dialog?.setContentView(R.layout.calender_layout)

            val calendarView1=dialog?.findViewById(R.id.calendarView2) as CalendarView



            calendarView1.maxDate=Date().time


            calendarView1.setOnDateChangeListener { view, year, month, dayOfMonth ->

                val action=HomeFragmentDirections.actionHomeFragmentToNoteFragment(dayOfMonth,month,year)
                Navigation.findNavController(it).navigate(action)

                val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year

                Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
                dialog.dismiss()

                clicked=false
            }

            val noBtn = dialog.findViewById(R.id.noBtn) as TextView

            noBtn.setOnClickListener { dialog.dismiss() }


            dialog.show()


        }



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
            calendar_button.visibility= View.VISIBLE
            settings_button.visibility= View.VISIBLE

        }
        else
        {
            search_button.visibility= View.INVISIBLE
            calendar_button.visibility= View.INVISIBLE
            settings_button.visibility= View.INVISIBLE

        }

    }




    private fun setAnimation(clicked:Boolean) {

        if(!clicked)

        {
            search_button.startAnimation(fromBottom)
            calendar_button.startAnimation(fromBottom)
            settings_button.startAnimation(fromBottom)
            add_button.startAnimation(rotateOpen)

        }
        else
        {
            search_button.startAnimation(toBottom)
            calendar_button.startAnimation(toBottom)
            settings_button.startAnimation(toBottom)
            add_button.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked:Boolean)
    {
        if(!clicked)
        {
            search_button.isClickable=true
            calendar_button.isClickable=true
            settings_button.isClickable=true

        }
        else
        {
            search_button.isClickable=false
            calendar_button.isClickable=false
            settings_button.isClickable=false
        }
    }

    override fun onItemClick(noteModel: NoteModel,view:View) {

        val mArgs= noteModel.date?.let { DateFormatHelper().stringToCalendar(it) }

        val action=HomeFragmentDirections.actionHomeFragmentToNoteFragment(mArgs!!.get(Calendar.DAY_OF_MONTH),
                mArgs.get(Calendar.MONTH),
                mArgs.get(Calendar.YEAR))

        Navigation.findNavController(view).navigate(action)
        Toast.makeText(this.context,"clicked",Toast.LENGTH_LONG).show()
    }

}

