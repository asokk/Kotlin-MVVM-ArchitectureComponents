package com.github.ramonrabello.cookpadassignment.core.data.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.github.ramonrabello.cookpadassignment.core.data.database.CandidateDao
import com.github.ramonrabello.cookpadassignment.core.data.database.CookpadDatabase
import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate
import kotlinx.coroutines.experimental.DefaultDispatcher
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withContext

/**
 * Implementation of [CandidateRepository] that uses Room for persistence.
 */
class CandidateRepository(application: Application) {

    private val candidateDao: CandidateDao by lazy { CookpadDatabase.getDatabase(application).candidateDao() }
    val allCandidates: LiveData<List<Candidate>> by lazy { runBlocking { candidateDao.allCandidates() } }

    /**
     *  Delegates insertion to the [CandidateDao.insert()] method
     *  to insert a candidate in the database.
     */
    suspend fun insert(candidate: Candidate) {
        withContext(DefaultDispatcher) { candidateDao.insert(candidate) }
    }

    /**
     * Delegates to [CandidateDao.deleteAll()] method
     * to deletion of all candidates from the database.
     */
    suspend fun deleteAll() {
        withContext(DefaultDispatcher) { candidateDao.deleteAll() }
    }

    /**
     * Delegates to [CandidateDao.delete()] method
     * to deletion a candidate from the database.
     */
    suspend fun delete(candidate: Candidate) {
        withContext(DefaultDispatcher) { candidateDao.delete(candidate) }
    }

    /**
     * Delegates to [CandidateDao.delete()] method
     * to deletion a candidate from the database.
     */
    suspend fun update(candidate: Candidate) {
        withContext(DefaultDispatcher) { candidateDao.update(candidate) }
    }
}