package com.example.galleryapp
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImagesAdapter(private val searchPhotosResponseList: List<PhotoInformation.SourcePhoto>) : RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {

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

        fun bind(photo: PhotoInformation.SourcePhoto) {

                Glide.with(itemView)
                    .load(photo.sourceSquare)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .into(image)

        }
    }

}