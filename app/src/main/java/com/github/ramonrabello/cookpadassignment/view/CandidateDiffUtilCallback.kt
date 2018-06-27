package com.github.ramonrabello.cookpadassignment.view

import android.support.annotation.Nullable
import android.support.v7.util.DiffUtil
import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate

/**
 * [DiffUtil.Callback] for handle difference between two lists
 * and dispatch the update to the adapter automatically.
 */
class CandidateDiffUtilCallback(private val oldCandidateList: List<Candidate>, private val newCandidateList: List<Candidate>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldCandidateList.size
    override fun getNewListSize() = newCandidateList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCandidateList[oldItemPosition].id == newCandidateList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCandidate = oldCandidateList[oldItemPosition]
        val newCandidate = newCandidateList[newItemPosition]
        return oldCandidate.name == newCandidate.name
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}