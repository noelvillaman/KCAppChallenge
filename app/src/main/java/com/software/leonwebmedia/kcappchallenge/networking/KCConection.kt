package com.software.leonwebmedia.kcappchallenge.networking

import android.util.Log
import com.software.leonwebmedia.kcappchallenge.model.Address
import com.software.leonwebmedia.kcappchallenge.model.ContactsResponse
import com.software.leonwebmedia.kcappchallenge.model.Phone
import org.json.JSONArray
import java.net.URL

class KCConection(private val url: String) {
    fun run() {
        val kcJsonString = URL(url).readText()
        Log.d(javaClass.simpleName, kcJsonString)
    }

    fun getReponse(): List<ContactsResponse> {
        val kcJsonResponse = URL(url).readText()
        var jsonArrayLocal = ArrayList<ContactsResponse>()
        val jObject = JSONArray(kcJsonResponse)
        //val jsonArrayThinkrite = jObject.getJSONArray()
        for (jsonObject in 0 until jObject.length()) {
            val contactsResponse = ContactsResponse()
            val contactsObject = jObject.getJSONObject(jsonObject)
            if (contactsObject.has("name")) {
                contactsResponse.name = contactsObject.optString("name")
            }
            if (contactsObject.has("id")) {
                contactsResponse.id = contactsObject.optString("id")
            }
            if (contactsObject.has("companyName")) {
                contactsResponse.companyName = contactsObject.optString("companyName")
            }
            if (contactsObject.has("isFavorite")) {
                contactsResponse.isFavorite = contactsObject.optBoolean("isFavorite")
            }

            if (contactsObject.has("smallImageURL")) {
                contactsResponse.smallImageURL = contactsObject.optString("smallImageURL")
            }
            if (contactsObject.has("largeImageURL")) {
                contactsResponse.largeImageURL = contactsObject.optString("largeImageURL")
            }
            if (contactsObject.has("emailAddress")) {
                contactsResponse.emailAddress = contactsObject.optString("emailAddress")
            }
            if (contactsObject.has("birthdate")) {
                contactsResponse.birthdate = contactsObject.optString("birthdate")
            }
            val phoneObject = contactsObject.getJSONObject("phone")
            contactsResponse.phone = Phone()
            contactsResponse.phone?.home = phoneObject.optString("home")
            contactsResponse.phone?.mobile = phoneObject.optString("mobile")
            contactsResponse.phone?.work = phoneObject.optString("work")

            val addressObject = contactsObject.getJSONObject("address")
            contactsResponse.address = Address()
            contactsResponse.address?.street = addressObject.optString("street")
            contactsResponse.address?.state = addressObject.optString("state")
            contactsResponse.address?.city = addressObject.optString("city")
            contactsResponse.address?.country = addressObject.optString("country")
            contactsResponse.address?.zipCode = addressObject.optString("zipCode")

            jsonArrayLocal.add(contactsResponse)
        }
        return jsonArrayLocal
    }
}