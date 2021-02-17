package com.aysimasavas.gratitudeapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.aysimasavas.gratitudeapp.R
import com.aysimasavas.gratitudeapp.helpers.DateFormatHelper
import com.aysimasavas.gratitudeapp.model.NoteModel
import com.aysimasavas.gratitudeapp.service.NoteDatabase
import com.aysimasavas.gratitudeapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*


class NoteFragment : Fragment() {

    private var isEdit: Boolean? = null
    private var noteModels: NoteModel? = null
    private lateinit var noteViewModel: NoteViewModel
    var index=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    val args: NoteFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_note, container, false)
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = context?.let { NoteDatabase.invoke(it?.applicationContext) }

        noteViewModel=ViewModelProviders.of(this).get(NoteViewModel::class.java)
        isEdit = args.note != ""
        noteModels=db?.noteDao()?.getNotes(DateFormatHelper().calendarToString(args.day, args.month, args.year))


        if (args.info.equals("today"))
        {
            textTittle.setText("Minnettarım..")
        }

        if(noteModels==null)
        {
            noteModels = NoteModel(DateFormatHelper().calendarToString(args.day, args.month, args.year), args.note)
        }
        else{
            isEdit=true
        }

        dateText.text = DateFormatHelper().calendarToString(args.day, args.month, args.year)
        noteText.setText(noteModels!!.note)

        saveButton.setOnClickListener {

            val action = NoteFragmentDirections.actionNoteFragmentToHomeFragment()

            if (isEdit as Boolean) {

                if(noteText.text.toString().equals(""))
                {
                    //db?.noteDao()?.deleteNotes(db.noteDao().getNotes(noteModels!!.date.toString()))
                    db?.noteDao()?.getNotes(noteModels!!.date.toString())?.let { it1 -> noteViewModel.delete(this.requireContext(), it1) }

                }
                else
                {
                    noteModels=db?.noteDao()?.getNotes(noteModels!!.date.toString())

                    noteModels!!.setText(noteText.text.toString())
                    noteModels?.let { it1 -> noteViewModel.update(this.requireContext(), it1) }

                }

            }
            else if (!noteText.text.toString().equals("")) {
                noteModels!!.setText(noteText.text.toString())
                noteViewModel.insert(this.requireContext(), noteModels!!)

            }


            Navigation.findNavController(it).navigate(action)

        }
        suggestionOnClick()
        shareOnClick()


    }


    private fun suggestionOnClick(){

        suggestionButton.setOnClickListener {

            val suggestions = resources.getStringArray(R.array.suggestion)
            noteText.hint=suggestions[index]
            index++
            if(index==suggestions.size)
            {
                index=0
            }

        }

    }

    private fun shareOnClick(){


        shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, noteModels!!.note)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }


    }

}