package com.aashis.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.aashis.roomdatabase.model.Note
import com.aashis.roomdatabase.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_add.*

class AddNote : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        setUp()

    }

    private fun setUp() {
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
       add_btn.setOnClickListener {
        insertDataDatabase()
       }
    }

    private fun insertDataDatabase(){
        val descTitle = noteTitle.text.toString()
        val desc = noteDesc.text.toString()

        if(inputCheck(descTitle, desc)){
            // Create User Object
            val note = Note(
                0,
                descTitle,
                desc
            )
            // Add Data to Database
            noteViewModel.addNote(note)
            Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            finish()
        }else{
            Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

     }

    private fun inputCheck(firstName: String, lastName: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName))
    }

}