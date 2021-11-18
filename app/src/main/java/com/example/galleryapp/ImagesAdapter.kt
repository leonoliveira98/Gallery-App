package com.example.galleryapp
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide

class ImagesAdapter(private val searchPhotosResponseList: List<GetSizesResponse.Sizes.Url>) : RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {

    //  total count of items in the list
    override fun getItemCount() = searchPhotosResponseList.size

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {

        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_images, parent,false)
        return ImagesViewHolder(view)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {

        // Set item views based on your views and data model
       return holder.bind(searchPhotosResponseList[position])
    }


    class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var image : ImageView = itemView.findViewById(R.id.image_view)
        var tvView = itemView.findViewById<TextView>(R.id.text_view_car)


        fun bind(photo: GetSizesResponse.Sizes.Url) {


//            if(photo.label == "Square"){

                tvView.text = photo.label
                Glide.with(itemView)
                    .load(photo.source)
                    .into(image)
//            }



        }
    }

}