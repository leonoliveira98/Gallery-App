package com.example.galleryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_images.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class MainActivity : AppCompatActivity() {

    val photoInfo : PhotoInformation = PhotoInformation(listOf())
    var a : Int = 0

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
                    Log.d("Response", "photoList size IDS : ${photoList.photosListInfo.photo.size}") // -> Imprime sempre 100
                    // BEGINNING of FOR
                    for (x in photoList.photosListInfo.photo){
                        // For each ID does:
                        val photoId = x.id

                        // Para inicializar a recyclerView com os tamanhos
                        val photoObj = PhotoInformation.SourcePhoto("","", "", "", "","",
                            "","","")
                        photoObj.id = photoId
                        photoInfo.info += photoObj

                        recycler_view_images.apply {
                            setHasFixedSize(true)
                            layoutManager = GridLayoutManager(this@MainActivity, 2)
                            adapter = ImagesAdapter(photoInfo.info)
                        }

                        val destinationService  = ServiceBuilder.buildService(ApiService::class.java)
                        val requestCall = destinationService.getSizesList(photoId)

                        requestCall.enqueue(object : Callback<GetSizesResponse>{
                            override fun onResponse(call: Call<GetSizesResponse>, response: Response<GetSizesResponse>) {
                                Log.d("Response", "photoInfo size: ${photoInfo.info.size}") // -> Imprime sempre 100

                                if (response.isSuccessful) {
                                    val photoList = response.body()!!
                                    // Para cada imagem, mostra cada label os tamanhos e urls
                                    a = 0

                                    for (i in photoList.sizes.size){

                                        if(i.label == "Large Square"){
                                            photoObj.labelSquare = i.label
                                            photoObj.sourceSquare = i.source
                                            photoObj.heightSquare = i.height.toString()
                                            photoObj.widthSquare = i.width.toString()
                                            a += 1

                                        } else if (i.label == "Large"){
                                            photoObj.labelLarge = i.label
                                            photoObj.sourceLarge = i.source
                                            photoObj.heightLarge = i.height.toString()
                                            photoObj.widthLarge = i.width.toString()
                                            a += 1

                                        }
                                    }

                                    // Se tiver os dois tamanhos ele entra aqui e manda para o adapter
                                    if(a == 2){
                                        recycler_view_images.apply {
                                            setHasFixedSize(true)
                                            layoutManager = GridLayoutManager(this@MainActivity, 2)
                                            adapter = ImagesAdapter(photoInfo.info)
                                        }
                                    }

                                } else {
                                    Toast.makeText(this@MainActivity, "BENFICA", Toast.LENGTH_LONG).show()
                                }

                            }
                            // END OF OnResponse
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