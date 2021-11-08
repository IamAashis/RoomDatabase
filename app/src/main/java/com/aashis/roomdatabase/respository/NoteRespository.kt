package com.aashis.roomdatabase.respository

import androidx.lifecycle.LiveData
import com.aashis.roomdatabase.data.NoteDao
import com.aashis.roomdatabase.model.Note

class NoteRespository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

}