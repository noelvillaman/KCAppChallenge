package com.software.leonwebmedia.kcappchallenge.home

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.software.leonwebmedia.kcappchallenge.R
import com.software.leonwebmedia.kcappchallenge.adapter.ContactListAdapter
import com.software.leonwebmedia.kcappchallenge.adapter.MultiViewTypeAdapter
import com.software.leonwebmedia.kcappchallenge.model.ContactsResponse
import com.software.leonwebmedia.kcappchallenge.networking.KCConection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var contact_recycleView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val url = "https://s3.amazonaws.com/technical-challenge/v3/contacts.json"

        contact_recycleView = findViewById(R.id.favorite_list)

        GlobalScope.launch(Dispatchers.Default) {  // replaces doAsync
            //do background work here
            KCConection(url).run()
            val lista = KCConection(url).getReponse()
            launch(Dispatchers.Main) { // replaces uiThread
                //do UI work here
                arrangeData(lista as ArrayList<ContactsResponse>)
                showList(lista)
            }
        }
    }

    private fun arrangeData(data : ArrayList<ContactsResponse>){
        Collections.sort(
            data,
            { lhs, rhs ->
                if (lhs.name!! < rhs.name.toString()) -1 else if (lhs.id!! > rhs.id.toString()) 1 else 0
            })
    }

    private fun showList(contactList: List<ContactsResponse>){
        contact_recycleView.layoutManager = LinearLayoutManager(this)
        contact_recycleView.adapter = ContactListAdapter(
            this,
            contactList as ArrayList<ContactsResponse>
        )
        contact_recycleView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

    }

    private fun showList2(contactList: List<ContactsResponse>){
        contact_recycleView.layoutManager = LinearLayoutManager(this)
        contact_recycleView.adapter = MultiViewTypeAdapter(
            contactList as ArrayList<ContactsResponse>,this,
        )
        contact_recycleView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

    }

    private fun redecoration(recyclerView: RecyclerView){
        recyclerView.addItemDecoration(object : ItemDecoration() {
            private val textSize = 50
            private val groupSpacing = 100
            private val itemsInGroup = 3
            private val paint: Paint = Paint()

            override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                for (i in 0 until parent.childCount) {
                    val view = parent.getChildAt(i)
                    val position = parent.getChildAdapterPosition(view)
                    if (position % itemsInGroup == 0) {
                        c.drawText(
                            "Group " + (position / itemsInGroup + 1), view.left.toFloat(),
                            (view.top - groupSpacing / 2 + textSize / 3).toFloat(), paint
                        )
                    }
                }
            }

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                if (parent.getChildAdapterPosition(view) % itemsInGroup == 0) {
                    outRect.set(0, groupSpacing, 0, 0);
                }
            }
        })
    }
}