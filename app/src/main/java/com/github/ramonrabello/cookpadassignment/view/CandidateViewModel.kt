package com.github.ramonrabello.cookpadassignment.view

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate
import com.github.ramonrabello.cookpadassignment.core.data.repository.CandidateRepository

/**
 * ViewModel that wraps operations and information for the candidates repository.
 */
class CandidateViewModel(application: Application) : AndroidViewModel(application) {
    private val repository by lazy { CandidateRepository(application) }
    val allCandidates: LiveData<List<Candidate>> by lazy { repository.allCandidates }

    /**
     * Delegates to repository to insert the [Candidate]
     * in the repository.
     */
    suspend fun insert(candidate: Candidate) {
        repository.insert(candidate)
    }

    /**
     * Delegates to repository to delete the [Candidate]
     * from the repository.
     */
    suspend fun delete(candidate: Candidate) {
        repository.delete(candidate)
    }

    /**
     * Delegates to repository to delete all [Candidate]
     * from the repository.
     */
    suspend fun deleteAll() {
        repository.deleteAll()
    }

    /**
     * Delegates to repository to update a [Candidate]
     * in the repository.
     */
    suspend fun update(candidate: Candidate) {
        repository.update(candidate)
    }
}