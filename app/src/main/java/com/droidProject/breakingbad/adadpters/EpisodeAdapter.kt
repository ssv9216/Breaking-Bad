package com.droidProject.breakingbad.adadpters

import android.annotation.SuppressLint
import android.os.Build
import android.transition.AutoTransition
import android.transition.Transition
import android.transition.TransitionManager
import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.Px
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import com.droidProject.breakingbad.R
import com.droidProject.breakingbad.models.Episodes
import kotlinx.android.synthetic.main.episode_sample.view.*


class EpisodeAdapter(private val list: ArrayList<Episodes>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var isExpanded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v1 = LayoutInflater.from(parent.context).inflate(R.layout.episode_sample, parent, false)


        return EpisodeViewHolder(v1)

    }

    override fun getItemCount(): Int {
        return list.size
    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EpisodeViewHolder ->
                holder.bind(list[position], position)

        }
    }

    class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val epNumber = itemView.esENumber
        private val epTitle = itemView.esETitle
        private val epDate = itemView.esEDate
        private val epCharacters = itemView.esECharacters
        private val epSeries = itemView.esESeries
        private val rootLayout = itemView.esRl1
        private val linearLayout = itemView.esLL1
        private val season = itemView.esESeason
        private val rootSeason = itemView.esCv1
        private val rootCardView = itemView.esRootCV

        @RequiresApi(Build.VERSION_CODES.KITKAT)
        @SuppressLint("SetTextI18n")
        fun bind(episodes: Episodes, position: Int) {


            epNumber.text = "Episode ${episodes.episode}"
            epTitle.text = episodes.eTitle
            epDate.text = episodes.airDate
            epCharacters.text = episodes.eCharacters
            epSeries.text = episodes.searies



            linearLayout.visibility = View.GONE
            rootSeason.visibility = View.GONE

            val episode = episodes.episode.trim().toInt()

            if (episode == 1) {
                season.text = "Season ${episodes.season}"
                rootSeason.visibility = View.VISIBLE
            }



            rootLayout.setOnClickListener {

                if (linearLayout.visibility == View.VISIBLE) {

                    linearLayout.visibility = View.GONE


                } else {
                    TransitionManager.beginDelayedTransition(rootCardView)
                    linearLayout.visibility = View.VISIBLE
                }


            }
        }
    }


}