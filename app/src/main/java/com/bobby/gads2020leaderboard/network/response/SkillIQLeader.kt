package com.bobby.gads2020leaderboard.network.response

import android.os.Parcel
import android.os.Parcelable

data class SkillIQLeader(
    val name:String,
    val score:Int,
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
        parcel.writeInt(score)
        parcel.writeString(country)
        parcel.writeString(badgeUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SkillIQLeader> {
        override fun createFromParcel(parcel: Parcel): SkillIQLeader {
            return SkillIQLeader(parcel)
        }

        override fun newArray(size: Int): Array<SkillIQLeader?> {
            return arrayOfNulls(size)
        }
    }
}