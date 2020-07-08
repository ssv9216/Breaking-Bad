package com.droidProject.breakingbad.adadpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.droidProject.breakingbad.R
import com.droidProject.breakingbad.models.Characters
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.sample_character.view.*

class CharacterAdapter(val list: ArrayList<Characters>, private val clickListener:(Characters)->Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.sample_character,parent,false)
        return CharacterViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CharacterViewHolder ->
                holder.bind(list[position],clickListener)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CharacterViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        private val name = itemView.scName
        private val image = itemView.scImage

        fun bind(characters: Characters,clickListener: (Characters) -> Unit){
            name.text = characters.name
            Picasso.get().load(characters.img).into(image)
            image.setOnClickListener {
                clickListener(characters)
            }

        }
    }

}