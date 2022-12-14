package com.c1ctech.mvvmwithnetworksource.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") val username: String?,
    @SerializedName("avatar_url") val avatar: String?,
    @SerializedName("id") val followers: String?,
    @SerializedName("type") val following: String?,
    @SerializedName("score") val repos: String?


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(avatar)
        parcel.writeString(followers)
        parcel.writeString(following)
        parcel.writeString(repos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
