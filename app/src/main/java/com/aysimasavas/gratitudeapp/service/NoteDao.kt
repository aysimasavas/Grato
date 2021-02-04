package com.aysimasavas.gratitudeapp.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aysimasavas.gratitudeapp.model.NoteModel

@Dao
interface NoteDao {


    @Insert
    fun insertAll(vararg notes:NoteModel):List<Long>


    @Query("SELECT * FROM notemodel")
    fun getAllNotes(): List<NoteModel>


    @Query("SELECT * FROM notemodel WHERE uuid= :notesId")
    fun getNotes(notesId:Int): NoteModel


    @Delete
    fun deleteNotes(notes:NoteModel)


}