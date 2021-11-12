package com.example.galleryapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView;

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {

    // ?????
    var dataList = emptyList<Images>()

    internal fun setDataList(dataList: List<Images>) {
        this.dataList = dataList
    }
    // ?????


    //  total count of items in the list
    override fun getItemCount() = dataList.size

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {

        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_images, parent,false)
        return ImagesViewHolder(view)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {

        // Get the data model based on position
        var data = dataList[position]

        // Set item views based on your views and data model
        holder.image.setImageResource(data.url)
    }


    class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var image : ImageView = itemView.findViewById(R.id.image_view)

    }

}