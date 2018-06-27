package com.github.ramonrabello.cookpadassignment.core.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate

/**
 * DAO for operations regarding [Candidate]s.
 */
@Dao
interface CandidateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(candidate: Candidate)

    @Query("DELETE FROM candidates")
    fun deleteAll() : Int

    @Update
    fun update(candidate: Candidate)

    @Delete
    fun delete(candidate: Candidate): Int

    @Query("SELECT * FROM candidates ORDER BY assessment_grade ASC")
    fun allCandidates(): LiveData<List<Candidate>>
}