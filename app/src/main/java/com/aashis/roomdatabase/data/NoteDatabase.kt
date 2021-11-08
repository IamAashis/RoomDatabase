package com.aashis.roomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aashis.roomdatabase.model.Note

@Database(entities = [Note::class], version =1, exportSchema = false )
abstract class NoteDatabase: RoomDatabase(){
    abstract fun noteDao(): NoteDao

    companion object{
        @Volatile
        private var INSTANCE:NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "user_database"
            ).build()
            INSTANCE = instance
            return instance
        }

    }

}
