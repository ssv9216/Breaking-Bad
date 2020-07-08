package com.droidProject.breakingbad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.droidProject.breakingbad.adadpters.TabAdapter
import com.droidProject.breakingbad.ui.CharacterFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //initialization
        val tabLayout : TabLayout = findViewById(R.id.ahTabLayout)
        val viewPager:ViewPager = findViewById(R.id.ahViewPager)

        //add tabs
        tabLayout.addTab(tabLayout.newTab().setText("Characters"))
        tabLayout.addTab(tabLayout.newTab().setText("Episodes"))
        tabLayout.addTab(tabLayout.newTab().setText("Quote"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        //toolBar
        setSupportActionBar(ahToolBar)
        ahToolBar.bringToFront()
        ahToolBar.title = ""


        //viewpager adapter
        val adapter = TabAdapter(this,supportFragmentManager,tabLayout.tabCount)
        viewPager.adapter =adapter

        //on tab change
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })


        //collapsing app bar title
        ahCToolbar.setExpandedTitleColor(resources.getColor(R.color.colorPrimaryDark))
        ahCToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.colorAccent))




    }

    // creating optionMenu to main toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.home_menu,menu)
        return true
    }

    // On optionMenu item clicked in main toolbar
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
             R.id.hmAbout ->
                 startActivity(Intent(this,About::class.java))
            else ->
                Toast.makeText(this,"Not-About",Toast.LENGTH_SHORT).show()

        }
        return true
    }
}