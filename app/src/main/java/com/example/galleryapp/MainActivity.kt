package com.example.galleryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var imageAdapter = ImagesAdapter()
    private var dataList = mutableListOf<Images>(
        Images("1","75","150",R.drawable.car),
        Images("2","75","150",R.drawable.car),
        Images("3","75","150",R.drawable.car),
        Images("4","75","150",R.drawable.car)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeActivity()

        recycler_view_images.layoutManager = GridLayoutManager(this, 2)
        recycler_view_images.adapter = imageAdapter
        //recycler_view_images.adapter

        imageAdapter.setDataList(dataList)




    }


    private fun changeActivity() {

    }



}