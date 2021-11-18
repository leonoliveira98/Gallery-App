package com.example.galleryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_images.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class MainActivity : AppCompatActivity() {

    lateinit var photoId: String
    lateinit var photoLabel: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadPhotos()

    }

    private fun loadPhotos() {
        //initiate the service
        val destinationService  = ServiceBuilder.buildService(ApiService::class.java)
        val requestCall = destinationService.getPhotoList("Paisagem", 1)
        //make network call asynchronously
        requestCall.enqueue(object : Callback<SearchPhotosResponse>{

            override fun onResponse(call: Call<SearchPhotosResponse>, response: Response<SearchPhotosResponse>) {
//                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val photoList  = response.body()!!
                    Log.d("Response", "photoList size IDS : ${photoList.photosListInfo.photo.size}")

                    // BEGINNING of FOR
                    for (x in photoList.photosListInfo.photo){
                        photoId = x.id
//                        Log.d("Response3", "$photoId")  //Imprime os ID's todos

                        val destinationService  = ServiceBuilder.buildService(ApiService::class.java)
                        val requestCall = destinationService.getSizesList("$photoId")

                        requestCall.enqueue(object : Callback<GetSizesResponse>{

                            override fun onResponse(call: Call<GetSizesResponse>, response: Response<GetSizesResponse>) {
//                                Log.d("Response", "onResponse: ${response.body()}")
                                if (response.isSuccessful) {
                                    val photoList = response.body()!!

                                    // Isto tinha de ser 100
                                    // O tamanho ta a ser basicamente todas as labels que cada imagem tem
                                    Log.d("Response","photoList size images : ${photoList.sizes.size}\n")
                                    Log.d("Response","photoList content : ${photoList.sizes.size}\n")


                                    recycler_view_images.apply {
                                        setHasFixedSize(true)
                                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                                        adapter = ImagesAdapter(photoList.sizes.size)
                                    }

                                } else {
                                    Toast.makeText(this@MainActivity, "BENFICA", Toast.LENGTH_LONG).show()
                                }
                            } // END OF OnResponse
                            override fun onFailure(call: Call<GetSizesResponse>, t: Throwable) {

                                Toast.makeText(this@MainActivity, "Somathin wrong $t", Toast.LENGTH_LONG).show()
                            }
                        })
                        //END OF ENQUEUE
                    }
                    // END OF FOR
                }else{
                        Toast.makeText(this@MainActivity, "BENFICA",Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<SearchPhotosResponse>, t: Throwable) {

                Toast.makeText(this@MainActivity, "Somathin wrong $t", Toast.LENGTH_LONG).show()
            }
        })
    }
}