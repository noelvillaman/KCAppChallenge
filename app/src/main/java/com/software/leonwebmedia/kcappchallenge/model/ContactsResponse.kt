package com.software.leonwebmedia.kcappchallenge.model

import android.os.Parcel
import android.os.Parcelable

class ContactsResponse() : Parcelable{
    var name : String? = null
    var id : String? = null
    var companyName : String? = null
    var isFavorite : Boolean? = null
    var smallImageURL : String? = null
    var largeImageURL : String? = null
    var emailAddress : String? = null
    var birthdate : String? = null
    var phone : Phone? = null
    var address : Address? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        id = parcel.readString()
        companyName = parcel.readString()
        isFavorite = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        smallImageURL = parcel.readString()
        largeImageURL = parcel.readString()
        emailAddress = parcel.readString()
        birthdate = parcel.readString()
        phone = parcel.readParcelable(Phone::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(id)
        parcel.writeString(companyName)
        parcel.writeValue(isFavorite)
        parcel.writeString(smallImageURL)
        parcel.writeString(largeImageURL)
        parcel.writeString(emailAddress)
        parcel.writeString(birthdate)
        parcel.writeParcelable(phone, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "ContactsResponse(name=$name, id=$id, companyName=$companyName, isFavorite=$isFavorite, smallImageURL=$smallImageURL, largeImageURL=$largeImageURL, emailAddress=$emailAddress, birthdate=$birthdate, phone=$phone, address=$address)"
    }

    companion object CREATOR : Parcelable.Creator<ContactsResponse> {
        override fun createFromParcel(parcel: Parcel): ContactsResponse {
            return ContactsResponse(parcel)
        }

        override fun newArray(size: Int): Array<ContactsResponse?> {
            return arrayOfNulls(size)
        }
    }

}