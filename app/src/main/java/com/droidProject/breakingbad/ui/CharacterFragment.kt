package com.droidProject.breakingbad.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.droidProject.breakingbad.CharacterDetail
import com.droidProject.breakingbad.R
import com.droidProject.breakingbad.adadpters.CharacterAdapter
import com.droidProject.breakingbad.models.Characters
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.sample_character.*
import kotlinx.android.synthetic.main.sample_character.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class CharacterFragment : Fragment(R.layout.character_fragment) {

    private val list = ArrayList<Characters>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var v: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.character_fragment, container, false)

        //recyclerview
        recyclerView = v.findViewById<RecyclerView>(R.id.cfRecyclerView)

        //list
        RetrieveData()


        return v
    }


    private fun RetrieveData() {
        val queue = Volley.newRequestQueue(context)
        val url = "https://breakingbadapi.com/api/characters/"
        list.clear()
        val arrayRequest = JsonArrayRequest(url, Response.Listener { response ->
            try {
                val totalObjects = response.length() - 1
                Log.d("TAG", "Total Objects$totalObjects")
                for (i in 0..totalObjects) {
                    val objects = response.getJSONObject(i)
                    val id: Int = objects.getString("char_id").toInt()
                    val name: String = objects.getString("name")
                    val birthday: String = objects.getString("birthday")
                    var occupation: String = ""
                    val img: String = objects.getString("img")
                    val status = objects.getString("status")
                    val nickname = objects.getString("nickname")
                    var appearance: String = ""
                    val portrayed = objects.getString("portrayed")
                    val category = objects.getString("category")

                    //occupation
                    val arrAy = objects.getJSONArray("occupation")
                    for (j in 0 until arrAy.length()) {
                        val occ = arrAy[j]
                        occupation = when (j) {
                            0 -> "$occ"
                            else -> "$occupation, $occ "
                        }
                    }

                    //appearance array
                    val arrAay = objects.getJSONArray("appearance")
                    for (k in 0 until arrAay.length()) {
                        val app = arrAay[k]
                        appearance = when (k) {
                            0 -> "$app"
                            else -> "$appearance, $app"
                        }
                    }

                    val item = Characters(
                        id,
                        name,
                        birthday,
                        occupation,
                        img,
                        status,
                        nickname,
                        appearance,
                        portrayed,
                        category
                    )


                    //Add items to the list
                    list += item

                }

                recyclerView.apply {
                    layoutManager = GridLayoutManager(context, 3)
                    hasFixedSize()
                    adapter =
                        CharacterAdapter(list) { Characters -> charactersItemClicked(Characters) }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, Response.ErrorListener {

        })
        queue.add(arrayRequest)
    }

    //on item click
    private fun charactersItemClicked(characters: Characters) {

        val intent = Intent(context, CharacterDetail::class.java)
        intent.putExtra("character", characters)
        startActivity(intent)

    }
}