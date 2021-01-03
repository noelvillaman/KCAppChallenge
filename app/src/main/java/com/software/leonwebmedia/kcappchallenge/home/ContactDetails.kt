package com.software.leonwebmedia.kcappchallenge.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.software.leonwebmedia.kcappchallenge.R
import com.software.leonwebmedia.kcappchallenge.model.Address
import com.software.leonwebmedia.kcappchallenge.model.ContactsResponse
import com.software.leonwebmedia.kcappchallenge.model.Phone
import com.squareup.picasso.Picasso


class ContactDetails : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var name: TextView
    lateinit var phone: TextView
    lateinit var mobile: TextView
    lateinit var work : TextView
    lateinit var address: TextView
    lateinit var birthdate: TextView
    lateinit var email: TextView
    lateinit var phoneObject : Phone
    lateinit var addresObject : Address
    lateinit var myMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        setSupportActionBar(findViewById(R.id.toolbar))
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);


        imageView = findViewById(R.id.iv_details)
        name = findViewById(R.id.tv_details_name)
        phone = findViewById(R.id.contact_dphone)
        mobile = findViewById(R.id.contact_mobile_number)
        work = findViewById(R.id.contact_dworknumber)
        address = findViewById(R.id.address_contact_d)
        birthdate = findViewById(R.id.contact_dbirthdate)
        email = findViewById(R.id.contact_email_d)
        phoneObject = intent.getParcelableExtra("phone_object")!!
        addresObject = intent.getParcelableExtra<Address>("address_object")!!
        intent.getParcelableExtra<ContactsResponse>("contact_object")?.let { contact_details(it) }

    }

    private fun contact_details(contactsResponse: ContactsResponse) {
        Picasso.get().load(contactsResponse.largeImageURL)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.user_large_hdpi).into(imageView)
        name.text = contactsResponse.name
        phone.text = phoneObject.home
        if (phoneObject.home == null || phoneObject.home.equals("")) {
            findViewById<View>(R.id.include_phone).visibility = View.GONE
        }
        mobile.text = phoneObject.mobile
        if (phoneObject.mobile == null || phoneObject.mobile.equals("")) {
            findViewById<View>(R.id.include_mobile).visibility = View.GONE
        }
        if (phoneObject.work == null || phoneObject.work.equals("")) {
            findViewById<View>(R.id.contact_work_number).visibility = View.GONE
        }
        work.text = phoneObject.work

        email.text = contactsResponse.emailAddress
        birthdate.text = contactsResponse.birthdate
        address.text =
            " ${addresObject.street}, ${addresObject.city}, ${addresObject.state}" +
                    " ${addresObject.zipCode}, ${addresObject.country}"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        if (menu != null) {
            myMenu = menu
        }
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                toggle()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toggle() {}

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val contactItem = menu!!.findItem(R.id.action_favorite)
        if(intent.getParcelableExtra<ContactsResponse>("contact_object")?.isFavorite == true) {
            contactItem.icon = ContextCompat.getDrawable(this, R.drawable.favorite_true)
        }
        return super.onPrepareOptionsMenu(menu)
    }
}