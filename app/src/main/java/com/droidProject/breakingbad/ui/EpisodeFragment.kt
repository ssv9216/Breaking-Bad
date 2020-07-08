package com.droidProject.breakingbad.ui

import android.os.Build
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.droidProject.breakingbad.R
import com.droidProject.breakingbad.adadpters.EpisodeAdapter
import com.droidProject.breakingbad.models.Characters
import com.droidProject.breakingbad.models.Episodes
import org.json.JSONArray

class EpisodeFragment : Fragment(R.layout.episode_fragment) {

    var list = ArrayList<Episodes>()
    private lateinit var recyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = super.onCreateView(inflater, container, savedInstanceState)

        //initialize recyclerview
        recyclerView = v?.findViewById(R.id.efRecyclerView) as RecyclerView

        list.clear()
        retrieveEpisodes()


        return v
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun retrieveEpisodes() {
        val rQueue = Volley.newRequestQueue(context)
        val url = "https://breakingbadapi.com/api/episodes"

        val arrayRequest = JsonArrayRequest(url, Response.Listener { response ->
            val totalObjects = response.length()
            for (i in 0 until totalObjects) {
                val objects = response.getJSONObject(i)

                val episode = objects.getString("episode")
                val season = objects.getString("season")
                val title = objects.getString("title")
                val airDate = objects.getString("air_date")
                var characters = ""
                val series = objects.getString("series")

                //creating string from jsonArray (Array inside Array)
                val arrAy = objects.getJSONArray("characters")
                for (j in 0 until arrAy.length()) {
                    val char = arrAy[j]
                    characters = when (j) {
                        0 -> "$char"
                        else -> "$characters, $char "
                    }
                }
                //each episode as item
                val item = Episodes(
                    episode, season, title, airDate, characters, series
                )

                list.plusAssign(item)

            }


            //after successfully data added
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                hasFixedSize()
                adapter = EpisodeAdapter(list)
            }

        }, Response.ErrorListener {

        })
        rQueue.add(arrayRequest)
    }

}