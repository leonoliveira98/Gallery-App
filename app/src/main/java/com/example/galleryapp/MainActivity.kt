package com.example.galleryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeActivity()

//        recycler_view_images.layoutManager = GridLayout(this, 2)
    }


    private fun changeActivity() {

//        text_view_hello.setOnClickListener {
//            val detailActivity = Intent(this, DetailsActivity::class.java)
//            startActivity(detailActivity)
//        }

    }



}