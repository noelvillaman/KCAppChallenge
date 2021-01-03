package com.software.leonwebmedia.kcappchallenge.model

import android.os.Parcel
import android.os.Parcelable

class Address() : Parcelable {
    var street : String? = null
    var city : String? = null
    var state : String? = null
    var country : String? = null
    var zipCode : String? = null

    constructor(parcel: Parcel) : this() {
        street = parcel.readString()
        city = parcel.readString()
        state = parcel.readString()
        country = parcel.readString()
        zipCode = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(street)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeString(country)
        parcel.writeString(zipCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "$street, $city, $state, $country, $zipCode)"
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }
    }


}