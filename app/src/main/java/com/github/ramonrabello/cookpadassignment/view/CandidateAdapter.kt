package com.github.ramonrabello.cookpadassignment.view

import android.support.v7.util.DiffUtil
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.ramonrabello.cookpadassignment.R
import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate
import com.github.ramonrabello.cookpadassignment.core.ktx.addItem
import com.github.ramonrabello.cookpadassignment.core.ktx.deleteItem
import com.github.ramonrabello.cookpadassignment.core.ktx.updateItem

/**
 * Adapter that holds candidate collection.
 */
class CandidateAdapter : BaseAdapter<Candidate, CandidateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.candidate_item, parent, false)
        return CandidateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {
        holder.bind(items[position])
    }

    /**
     * Adds a candidate in the list
     * and notifies the adapter.
     */
    fun addCandidate(candidate: Candidate) {
        addItem(candidate) {
            Log.d("CandidateAdapter", "Candidate $candidate inserted.")
        }
    }

    /**
     * Updates the candidate in adapter if it is found.
     */
    fun updateCandidate(candidate: Candidate) {
        updateItem(candidate) {
            Log.d("CandidateAdapter", "Candidate $candidate updated.")
        }
    }

    /**
     * Updates the candidate in adapter if it is found.
     */
    fun deleteCandidate(candidate: Candidate) {
        deleteItem(candidate) {
            Log.d("CandidateAdapter", "Candidate $candidate deleted.")
        }
    }

    fun updateCandidates(newCandidates: List<Candidate>) {
        val diffUtilCallback = CandidateDiffUtilCallback(items, newCandidates)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        items.clear()
        items.addAll(newCandidates)
        diffResult.dispatchUpdatesTo(this)
    }
}