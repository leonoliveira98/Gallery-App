package com.example.galleryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Intent working
        text_view_hello.setOnClickListener{
            val detailActivity = Intent(this, DetailsActivity::class.java)
            startActivity(detailActivity)
        }
    }
}