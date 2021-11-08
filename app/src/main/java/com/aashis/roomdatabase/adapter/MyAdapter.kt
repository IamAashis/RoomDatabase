package com.aashis.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aashis.roomdatabase.R
import com.aashis.roomdatabase.model.Note
import kotlinx.android.synthetic.main.custom_row.view.*


class MyAdapter(private val onItemClicked: (position: Int) -> Unit?) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.itemView.titleText.text = currentItem.noteTitle
        holder.itemView.descText.text = currentItem.noteDescription


        holder.itemView.rowLayout.setOnClickListener {
            onItemClicked(position)
        }
    }

    fun setData(note: List<Note>) {
        this.noteList = note
        notifyDataSetChanged()
    }

}

