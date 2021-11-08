package com.aashis.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.aashis.roomdatabase.model.Note
import com.aashis.roomdatabase.utils.BundleConstants
import com.aashis.roomdatabase.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_update_note.*

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    var note: Note?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

        Setup()

    }

    private fun Setup() {

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        note=intent?.getParcelableExtra(BundleConstants.navigateToUpdate)
        noteTitleUpdate.setText(note?.noteTitle)
        noteDescUpdate.setText(note?.noteDescription)

        add_btnUpdate.setOnClickListener {
            updateItem()
        }

    }

    private fun updateItem() {
        val noteTitle = noteTitleUpdate.text.toString()
        val noteDesc = noteDescUpdate.text.toString()

        if (inputCheck(noteTitle, noteTitle)) {
            // Create User Object
            val updateNote = Note(note?.id?:0,  noteTitle, noteDesc)
            // Update Current User
            noteViewModel.updateNote(updateNote)
            Toast.makeText(this, "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            finish()
        } else {
            Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(noteTitle: String, noteDesc: String): Boolean {
        return !(TextUtils.isEmpty(noteTitle) && TextUtils.isEmpty(noteTitle))
    }
}