package com.github.ramonrabello.cookpadassignment.core.data.repository

import android.app.Application
import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate
import kotlinx.coroutines.experimental.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Unit tests for class [CandidateRepository].
 */
class CandidateRepositoryTest {

    private lateinit var candidateRepository: CandidateRepository
    private lateinit var mockCandidate:Candidate

    @Before
    fun runBeforeTest() {
        val application = mock(Application::class.java)
        mockCandidate = mock(Candidate::class.java)
        candidateRepository = CandidateRepository(application)
    }

    @Test
    fun insertCandidateIntoRepository() {
        launch {
            candidateRepository.insert(mockCandidate)
            verify(candidateRepository).insert(mockCandidate)
        }
    }

    @Test
    fun updateCandidateIntoRepository() {
        launch {
            candidateRepository.update(mockCandidate)
            verify(candidateRepository).update(mockCandidate)
        }
    }

    @Test
    fun deleteCandidateIntoRepository() {
        launch {
            candidateRepository.delete(mockCandidate)
            verify(candidateRepository).delete(mockCandidate)
        }
    }

    @Test
    fun getAllCandidatesFromRepository() {
        launch {
            candidateRepository.allCandidates
            verify(candidateRepository).allCandidates
        }
    }

    @Test
    fun deleteAllCandidatesFromRepository() {
        launch {
            candidateRepository.deleteAll()
            verify(candidateRepository).deleteAll()
        }
    }
}