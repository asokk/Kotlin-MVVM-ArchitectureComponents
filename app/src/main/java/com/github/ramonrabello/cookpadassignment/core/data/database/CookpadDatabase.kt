package com.github.ramonrabello.cookpadassignment.core.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate

/**
 * Database definition for using Room.
 */
@Database(entities = [Candidate::class], version = 1, exportSchema = false)
abstract class CookpadDatabase : RoomDatabase() {

    /**
     * Retrieves [CandidateDao] in order to allow
     * SQL operations with the dao.
     */
    abstract fun candidateDao(): CandidateDao

    /**
     * Singleton to retrieve a single object of [CookpadDatabase].
     */
    companion object {
        fun getDatabase(context: Context) =
                synchronized(CookpadDatabase::class) {
                    return@synchronized Room.databaseBuilder(context.applicationContext,
                            CookpadDatabase::class.java, "candidate_database")
                            .build()
                }
    }
}