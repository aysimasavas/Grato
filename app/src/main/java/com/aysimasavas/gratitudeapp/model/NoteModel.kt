package com.aysimasavas.gratitudeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class NoteModel(

        @ColumnInfo(name="date")
        var date:String?,
        @ColumnInfo(name="note")
        var note:String?) {

    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0


    fun setText(str:String)
    {
        this.note=str

    }
}