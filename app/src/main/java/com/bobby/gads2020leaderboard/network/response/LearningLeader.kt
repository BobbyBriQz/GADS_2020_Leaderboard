package com.bobby.gads2020leaderboard.network.response

import android.os.Parcel
import android.os.Parcelable

data class LearningLeader(
    val name:String,
    val hours:Int,
    val country:String,
    val badgeUrl:String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(hours)
        parcel.writeString(country)
        parcel.writeString(badgeUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LearningLeader> {
        override fun createFromParcel(parcel: Parcel): LearningLeader {
            return LearningLeader(parcel)
        }

        override fun newArray(size: Int): Array<LearningLeader?> {
            return arrayOfNulls(size)
        }
    }
}