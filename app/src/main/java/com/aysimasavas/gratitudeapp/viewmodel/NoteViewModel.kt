package com.aysimasavas.gratitudeapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aysimasavas.gratitudeapp.model.NoteModel
import com.aysimasavas.gratitudeapp.service.Repo
import com.aysimasavas.gratitudeapp.service.NoteDatabase as NoteDatabase

class NoteViewModel : ViewModel(){



    fun update(context: Context, noteModel: NoteModel)
    {
        Repo.update(context,noteModel)
    }


    fun insert(context: Context,noteModel: NoteModel)
    {
        Repo.insert(context,noteModel)
    }


    fun delete(context: Context,noteModel: NoteModel)
    {
        Repo.delete(context,noteModel)
    }


    fun searchForItem(context: Context,note:String):List<NoteModel>?
    {
       return Repo.search(context,note)
    }

}