package com.github.ramonrabello.cookpadassignment.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.MenuItem
import com.github.ramonrabello.cookpadassignment.R
import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate
import com.github.ramonrabello.cookpadassignment.core.ktx.toTypeface
import kotlinx.android.synthetic.main.activity_new_candidate.toolbar
import kotlinx.android.synthetic.main.content_new_candidate.candidate_email_field
import kotlinx.android.synthetic.main.content_new_candidate.candidate_grade_spinner
import kotlinx.android.synthetic.main.content_new_candidate.candidate_name_field
import kotlinx.android.synthetic.main.content_new_candidate.candidate_phone_number_field
import kotlinx.android.synthetic.main.content_new_candidate.save_button

class NewCandidateActivity : AppCompatActivity() {

    private var candidateToEdit: Candidate? = null

    companion object {
        const val EXTRA_CANDIDATE = "com.github.ramonrabello.cookpadassignment.view.NewCandidateActivity.EXTRA_REPLY"
        const val EXTRA_IN_EDIT_MODE = "com.github.ramonrabello.cookpadassignment.view.NewCandidateActivity.EXTRA_IN_EDIT_MODE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_candidate)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        candidate_name_field.toTypeface("OpenSans-Regular")
        candidate_phone_number_field.toTypeface("OpenSans-Regular")
        candidate_email_field.toTypeface("OpenSans-Regular")
        save_button.setOnClickListener { prepareCandidate() }
    }

    override fun onResume() {
        super.onResume()
        candidateToEdit = intent.getParcelableExtra(EXTRA_CANDIDATE)
        candidateToEdit?.let {
            toolbar.title = getString(R.string.activity_new_candidate_in_edit_mode)
            candidate_name_field.setText(it.name)
            candidate_email_field.setText(it.email)
            candidate_phone_number_field.setText(it.phoneNumber)
            candidateToEdit?.assessmentGrade?.let {
                candidate_grade_spinner.setSelection(it)
            }
        }

    }

    private fun prepareCandidate() {
        val data = Intent()
        val name = candidate_name_field.text.toString().trim()
        val email = candidate_email_field.text.toString().trim()
        val phoneNumber = candidate_phone_number_field.text.toString().trim()
        val assessmentGrade = candidate_grade_spinner.selectedItemPosition

        when {
            TextUtils.isEmpty(name) ||
                    TextUtils.isEmpty(email) ||
                    TextUtils.isEmpty(phoneNumber) ||
                    assessmentGrade == -1 -> setResult(Activity.RESULT_CANCELED, data)
            else -> {
                val candidate by lazy {
                    if (intent.getBooleanExtra(EXTRA_IN_EDIT_MODE, false)) {
                        data.putExtra(EXTRA_IN_EDIT_MODE, true)
                        candidateToEdit?.let {
                            it.name = name
                            it.phoneNumber = phoneNumber
                            it.email = email
                            it.assessmentGrade = assessmentGrade
                            candidateToEdit
                        }
                    } else {
                        Candidate(name = name, phoneNumber = phoneNumber, email = email, assessmentGrade = assessmentGrade)
                    }
                }
                data.putExtra(EXTRA_CANDIDATE, candidate)
                setResult(Activity.RESULT_OK, data)
            }
        }
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when {
            item?.itemId == android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
