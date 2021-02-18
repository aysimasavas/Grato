package com.aysimasavas.gratitudeapp.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aysimasavas.gratitudeapp.model.NoteModel

@Dao
interface NoteDao {


    @Insert
    fun insertAll(vararg notes:NoteModel)


    @Query("SELECT * FROM notemodel")
    fun getAllNotes(): List<NoteModel>


    @Query("SELECT * FROM notemodel WHERE date= :mDate")
    fun getNotes(mDate:String): NoteModel


    @Delete
    fun deleteNotes(notes:NoteModel)


    @Update
    fun updateNotes(notes: NoteModel)

    @Query("SELECT * FROM notemodel WHERE note LIKE :note OR date LIKE :note")
    fun getSearchResults(note : String) : List<NoteModel>




}