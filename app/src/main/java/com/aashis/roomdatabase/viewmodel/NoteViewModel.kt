package com.aashis.roomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aashis.roomdatabase.data.NoteDatabase
import com.aashis.roomdatabase.data.NoteDatabase.Companion.getDatabase
import com.aashis.roomdatabase.model.Note
import com.aashis.roomdatabase.respository.NoteRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Note>>
    private val respository: NoteRespository

    init {
        val noteDao = NoteDatabase.getDatabase(
            application
        ).noteDao()
        respository = NoteRespository(noteDao)
        readAllData = respository.readAllData
    }

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            respository.addNote(note)
        }
    }


    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            respository.updateNote(note)
        }
    }

    fun deleteNote(note: Note?){
        viewModelScope.launch(Dispatchers.IO) {
           respository.deleteNote(note)
        }
    }



}