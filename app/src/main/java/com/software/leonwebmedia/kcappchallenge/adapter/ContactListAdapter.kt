package com.software.leonwebmedia.kcappchallenge.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.software.leonwebmedia.kcappchallenge.R
import com.software.leonwebmedia.kcappchallenge.home.ContactDetails
import com.software.leonwebmedia.kcappchallenge.model.Address
import com.software.leonwebmedia.kcappchallenge.model.ContactsResponse
import com.software.leonwebmedia.kcappchallenge.model.Phone
import com.squareup.picasso.Picasso


class ContactListAdapter(
    val mContext: Context,
    val contacts_response_list: ArrayList<ContactsResponse>
) : RecyclerView.Adapter<ContactListAdapter.OthersViewHolder>() {


    override fun onBindViewHolder(holderOthers: OthersViewHolder, position: Int) {
        if (contacts_response_list[position].isFavorite != true){
            holderOthers.contact_star.visibility = View.GONE
        }
        Picasso.get().load(contacts_response_list[position].largeImageURL).placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.user_large_hdpi).into(holderOthers.contact_photo)
        holderOthers.contact_name.text = contacts_response_list[position].name
        if (contacts_response_list[position].companyName != null || !contacts_response_list[position].companyName.equals("")
            || !contacts_response_list[position].companyName.equals("null", true)) {
            holderOthers.contact_ocupation.text = contacts_response_list[position].companyName

        } else
            holderOthers.contact_ocupation.visibility = View.GONE

        holderOthers.itemView.setOnClickListener {
            openDetails(contacts_response_list[position],
                contacts_response_list[position].phone,
                contacts_response_list[position].address)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OthersViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.contacts_list_view, parent,
            false
        )
        return OthersViewHolder(view)
    }


    override fun getItemCount() = contacts_response_list.size

    private fun openDetails(contact_response_objec: ContactsResponse, phone: Phone?, address: Address?){
        val intentDetails = Intent(mContext, ContactDetails::class.java)
        intentDetails.putExtra("contact_object", contact_response_objec)
        intentDetails.putExtra("phone_object", phone)
        intentDetails.putExtra("address_object", address)
        Log.i("TAG", "We just open a new object on the screen ${contact_response_objec.hashCode()}")
        mContext.startActivity(intentDetails)
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2 * 2
    }

    class OthersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val contact_photo : ImageView = itemView.findViewById(R.id.contact_image)
        val contact_star : ImageView = itemView.findViewById(R.id.contact_star)
        val contact_name : TextView = itemView.findViewById(R.id.contact_adapter_name)
        val contact_ocupation : TextView = itemView.findViewById(R.id.contact_adapter_ocupation)
    }
}