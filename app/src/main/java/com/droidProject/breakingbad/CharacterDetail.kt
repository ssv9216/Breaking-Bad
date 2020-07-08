package com.droidProject.breakingbad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.droidProject.breakingbad.models.Characters
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_detail.*
import kotlinx.android.synthetic.main.detail_layout_include.*

class CharacterDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)


        //Toolbar
        setSupportActionBar(acdToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        acdToolbar.bringToFront()

        //collapsing appbar title
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.acdCToolbar)


        val intent = intent
        val character:Characters = intent.getParcelableExtra("character")
        if(intent.extras != null) {
            valNickname.text = character.nickname
            valBirthday.text = character.birthday
            valOccupation.text = character.occupation
            valStatus.text = character.status
            valAppearance.text = character.appearance
            valCategory.text = character.category
            valPortrayed.text = character.portrayed
            collapsingToolbarLayout.title =character.name
            Picasso.get()
                .load(character.img)
                .into(acdImageView)
        }

    }
}