package com.github.ramonrabello.cookpadassignment

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.github.ramonrabello.cookpadassignment.core.data.database.CandidateDao
import com.github.ramonrabello.cookpadassignment.core.data.database.CookpadDatabase
import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate
import com.github.ramonrabello.cookpadassignment.extensions.getValueBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.UUID

/**
 * Unit tests for [CookpadDatabase] operations.
 */
@RunWith(AndroidJUnit4::class)
class CookpadDatabaseTest {

    private lateinit var candidateDao: CandidateDao
    private lateinit var database: CookpadDatabase

    @Before
    fun runBeforeTest() {
        val context: Context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, CookpadDatabase::class.java).build()
        candidateDao = database.candidateDao()
    }

    @After
    fun runAfterTest() {
        database.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertNewCandidateAndGetBackFromDatabase() {
        val candidate = createCandidate()
        candidateDao.insert(candidate)
        val insertedCandidate = candidateDao.allCandidates().getValueBlocking()
        assertNotNull(insertedCandidate)
    }

    private fun createCandidate() = Candidate(UUID.randomUUID().variant().toLong(), "Ramon Rabello", "ramon.rabello@gmail.com", "+55119988998", 0)

    @Test
    @Throws(IOException::class)
    fun updateCandidateAndReadTheUpdatedValue() {
        val candidate = createCandidate()
        candidateDao.insert(candidate)
        val insertedCandidate = candidateDao.allCandidates().getValueBlocking()?.find { candidate.name == "Ramon Rabello" }
        insertedCandidate?.name = "Ramon Rabello Updated"
        insertedCandidate?.let {
            candidateDao.update(insertedCandidate)
            val updatedCandidate = candidateDao.allCandidates().getValueBlocking()?.find { candidate.id == insertedCandidate.id }
            assertThat(updatedCandidate?.name, equalTo(insertedCandidate.name))
        }
    }

    @Test
    @Throws(IOException::class)
    fun deleteCandidateFromDatabase() {
        val candidate = createCandidate()
        candidateDao.insert(candidate)
        val insertedCandidate = candidateDao.allCandidates().getValueBlocking()?.find { candidate.id == candidate.id }
        insertedCandidate?.let {
            candidateDao.delete(insertedCandidate)
            val deletedCandidate = candidateDao.allCandidates().getValueBlocking()?.find { candidate.id == insertedCandidate.id }
            assertNull(deletedCandidate)
        }
    }

    @Test
    @Throws(IOException::class)
    fun deleteAllCandidatesFromDatabase() {
        var candidate = createCandidate()
        candidateDao.insert(candidate)

        candidate = createCandidate()
        candidateDao.insert(candidate)

        candidate = createCandidate()
        candidateDao.insert(candidate)

        candidateDao.deleteAll()
        val candidates = candidateDao.allCandidates().getValueBlocking()
        assertThat(candidates, hasSize(0))
    }
}