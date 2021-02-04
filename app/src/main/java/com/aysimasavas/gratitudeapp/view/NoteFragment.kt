package com.aysimasavas.gratitudeapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.aysimasavas.gratitudeapp.R
import com.aysimasavas.gratitudeapp.helpers.DateFormatHelper
import com.aysimasavas.gratitudeapp.model.NoteModel
import com.aysimasavas.gratitudeapp.service.NoteDatabase
import kotlinx.android.synthetic.main.fragment_note.*


class NoteFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    val args: NoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db= context?.let { NoteDatabase.invoke(it?.applicationContext) }

        dateText.text=DateFormatHelper().calendarToString(args.day,args.month,args.year)


        saveButton.setOnClickListener {

            if(!noteText.text.equals(""))
            {
                val noteModel=NoteModel(dateText.text.toString(),noteText.text.toString())

                db?.noteDao()?.insertAll(noteModel)


            }

            val action=NoteFragmentDirections.actionNoteFragmentToHomeFragment()
            Navigation.findNavController(it).navigate(action)

        }
    }


}