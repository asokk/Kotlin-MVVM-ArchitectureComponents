package com.github.ramonrabello.cookpadassignment.core.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Represents a candidate entity in the app.
 */
@Entity(tableName = "candidates")
@Parcelize
data class Candidate(@PrimaryKey(autoGenerate = true) val id: Long = 0,
                     var name: String,
                     var email: String,
                     @ColumnInfo(name = "phone_number")
                     var phoneNumber: String,
                     @ColumnInfo(name = "assessment_grade")
                     var assessmentGrade: Int) : Parcelable