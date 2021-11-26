package com.example.galleryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var photo: PhotoInformation.SourcePhoto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        photo = intent.getSerializableExtra("Photo") as PhotoInformation.SourcePhoto

        Glide.with(this)
            .load(photo.sourceLarge)
            .placeholder(R.drawable.loading)
            .error(R.drawable.placeholder)
            .into(image_view_large)

    }
}