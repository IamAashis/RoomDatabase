package com.aashis.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.aashis.roomdatabase.adapter.MyAdapter
import com.aashis.roomdatabase.model.Note
import com.aashis.roomdatabase.utils.BundleConstants
import com.aashis.roomdatabase.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel
    lateinit var adapter: MyAdapter
    var note: List<Note> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUp()

    }

    private fun setUp() {
        initRecyclerView()
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.readAllData.observe(this, Observer { note ->
            this.note = note
            adapter.setData(note)
        })

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddNote::class.java))
        }
    }

    private fun initRecyclerView() {
        adapter = MyAdapter() {
            navigateToUpdateActivity(note[it])
        }
        recycleview?.adapter = adapter
        recycleview?.layoutManager = LinearLayoutManager(this)
    }

    private fun navigateToUpdateActivity(note: Note) {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra(BundleConstants.navigateToUpdate, note)
        startActivity(intent)
    }

}