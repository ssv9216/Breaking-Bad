package com.droidProject.breakingbad.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.droidProject.breakingbad.R
import com.droidProject.breakingbad.TopSpacingItemDecoration
import com.droidProject.breakingbad.adadpters.QuoteAdapter
import com.droidProject.breakingbad.models.Quotes

class QuoteFragment : Fragment(R.layout.quote_fragment){

    private val list = ArrayList<Quotes>()
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  super.onCreateView(inflater, container, savedInstanceState)

        //recyclerview
        recyclerView = v?.findViewById(R.id.qfRecyclerView)!!

        list.clear()
        retrieveQuote()
        return v
    }

    private fun retrieveQuote() {
        val requestQueue = Volley.newRequestQueue(context)
        val url = "https://breakingbadapi.com/api/quotes"

        val arrayRequest = JsonArrayRequest(url,Response.Listener { response ->

            val totalObjects = response.length()
            Log.d("TAG","total quote : $totalObjects")

            for (i in 0 until totalObjects){

                val objects = response.getJSONObject(i)

                val id = objects.getString("quote_id")
                val quote = objects.getString("quote")
                val author = objects.getString("author")
                val series = objects.getString("series")

                val item = Quotes(id,quote,author,series)
                list += item

            }

            //access list and apply it to recyclerview
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                hasFixedSize()
                val topSpacingDecoration = TopSpacingItemDecoration(1)
                addItemDecoration(topSpacingDecoration)
                adapter = QuoteAdapter(list,context)
                itemAnimator?.moveDuration = 2000
            }



        },Response.ErrorListener {
            error ->
            Log.d("TAG",error.toString())
        })
        requestQueue.add(arrayRequest)
    }
}