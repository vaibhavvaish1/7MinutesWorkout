package com.example.a7minutesworkout

import android.content.Context
import androidx.room.Database
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity :: class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDAO() : HistoryDAO

    companion object{
        @Volatile
        var INSTANCE : HistoryDatabase? = null
        fun getInstance(context: Context):HistoryDatabase{
            synchronized(this){
                var instance  = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java,
                        "history_database"
                    )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this lesson. You can learn more about
                        // migration with Room in this blog post:
                        // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }

                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}