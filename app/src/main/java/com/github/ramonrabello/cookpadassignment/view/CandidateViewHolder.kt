package com.github.ramonrabello.cookpadassignment.view

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.ramonrabello.cookpadassignment.R
import com.github.ramonrabello.cookpadassignment.view.NewCandidateActivity.Companion.EXTRA_CANDIDATE
import com.github.ramonrabello.cookpadassignment.view.NewCandidateActivity.Companion.EXTRA_IN_EDIT_MODE
import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate
import com.github.ramonrabello.cookpadassignment.core.ktx.toTypeface
import kotlinx.android.synthetic.main.candidate_item_foreground.view.candidate_email
import kotlinx.android.synthetic.main.candidate_item_foreground.view.candidate_grade
import kotlinx.android.synthetic.main.candidate_item_foreground.view.candidate_name
import kotlinx.android.synthetic.main.candidate_item_foreground.view.view_foreground

/**
 * ViewHolder to display candidate info.
 */
class CandidateViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
    fun bind(candidate: Candidate){
        itemView.apply {
            // bind data to TextViews
            candidate_name.text = candidate.name
            candidate_name.toTypeface("OpenSans-Regular")

            candidate_email.text = candidate.email
            candidate_email.toTypeface("OpenSans-Light")

            candidate_grade.text = itemView.context.resources.getStringArray(R.array.assessment_grade_spinner_entries)[candidate.assessmentGrade]
            candidate_grade.toTypeface("OpenSans-SemiBold")
        }

        itemView.view_foreground.setOnClickListener{
            val intent = Intent(itemView.context, NewCandidateActivity::class.java)
            intent.putExtra(EXTRA_CANDIDATE, candidate)
            intent.putExtra(EXTRA_IN_EDIT_MODE, true)
            (itemView.context as Activity).startActivityForResult(intent, MainActivity.NEW_CANDIDATE_ACTIVITY_REQUEST_CODE)
        }
    }
}
