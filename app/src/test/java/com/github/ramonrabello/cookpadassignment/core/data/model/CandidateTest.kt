package com.github.ramonrabello.cookpadassignment.core.data.model

import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate
import junit.framework.Assert.assertNotNull
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Unit tests for class [Candidate].
 */
class CandidateTest {

    private lateinit var candidate: Candidate

    @Test
    fun shouldValidateIfCandidateHasId() {
        candidate = Candidate(12312, "Ramon Rabello", "ramon.rabello@gmail.com", "5511999999", 0)
        assertThat(candidate.id.toString(), equalTo(12312.toString()))
    }

    @Test
    fun shouldValidateIfCandidateHasPhoneNumber() {
        candidate = Candidate(12312, "Ramon Rabello", "ramon.rabello@gmail.com", "55119988998", 0)
        assertNotNull(candidate.phoneNumber)
    }

    @Test
    fun shouldValidateIfCandidateHasEmail() {
        candidate = Candidate(12312, "Ramon Rabello", "ramon.rabello@gmail.com", "5511999999", 0)
        assertNotNull(candidate.email)
    }

    @Test
    fun shouldValidateIfCandidateHasAssessmentGradeRangeIsValid() {
        candidate = Candidate(12312, "Ramon Rabello", "ramon.rabello@gmail.com", "5511999999", 0)
        assertThat(candidate.assessmentGrade, anyOf(
                equalTo(0), equalTo(1),
                equalTo(2), equalTo(3),
                equalTo(4), equalTo(5)))
    }
}