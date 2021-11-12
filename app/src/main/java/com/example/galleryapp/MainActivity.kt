package com.example.galleryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_images.*

class MainActivity : AppCompatActivity() {

    private var imageAdapter = ImagesAdapter()
    private var dataList = mutableListOf<Images>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeActivity()
        recyclerViewImages()

    }

    private fun recyclerViewImages() {

        dataList = mutableListOf(
            Images("1","75","150",R.drawable.car),
            Images("2","75","150",R.drawable.car),
            Images("3","75","150",R.drawable.car),
            Images("4","75","150",R.drawable.car)
        )

        recycler_view_images.layoutManager = GridLayoutManager(this, 2)
        recycler_view_images.adapter = imageAdapter

        imageAdapter.setDataList(dataList)
    }


    private fun changeActivity() {
        recycler_view_images.setOnClickListener {
            val cardIntent = Intent(this, DetailsActivity::class.java)
            startActivity(cardIntent)
        }
    }



}