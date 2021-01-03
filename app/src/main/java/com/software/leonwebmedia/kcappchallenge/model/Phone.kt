package com.software.leonwebmedia.kcappchallenge.model

import android.os.Parcel
import android.os.Parcelable

class Phone() : Parcelable {
    var work : String? = null
    var home : String? = null
    var mobile : String? = null

    constructor(parcel: Parcel) : this() {
        work = parcel.readString()
        home = parcel.readString()
        mobile = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(work)
        parcel.writeString(home)
        parcel.writeString(mobile)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Phone> {
        override fun createFromParcel(parcel: Parcel): Phone {
            return Phone(parcel)
        }

        override fun newArray(size: Int): Array<Phone?> {
            return arrayOfNulls(size)
        }
    }


}