package com.example.galleryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.util.Log
import android.widget.AbsListView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_images.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class MainActivity : AppCompatActivity() {

    val photoInfoMut = mutableListOf<PhotoInformation.SourcePhoto>()
    var b: Int = 0
    lateinit var adapter2: ImagesAdapter

    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loadPhotos(page)

        recycler_view_images.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    page += 1
                    loadPhotos(page)
                }
            }
        })


    }

    private fun loadPhotos(page : Int) {

        //initiate the service
        val destinationService = ServiceBuilder.buildService(ApiService::class.java)
        val requestCall = destinationService.getPhotoList("Paisagens", page)
        //make network call asynchronously
        requestCall.enqueue(object : Callback<SearchPhotosResponse> {
            override fun onResponse(
                call: Call<SearchPhotosResponse>,
                response: Response<SearchPhotosResponse>
            ) {
                if (response.isSuccessful) {
                    val photoList = response.body()!!
//                    Log.d("Response", "photoList size IDS : ${photoList.photosListInfo.photo.size}") // -> Imprime sempre 100
                    // BEGINNING of FOR
                    for (x in photoList.photosListInfo.photo) {
                        // For each ID does:
                        val photoId = x.id
                        // Para inicializar a recyclerView com os tamanhos
                        val photoObj = PhotoInformation.SourcePhoto(
                            photoId, "", "", "", "", "",
                            "", "", ""
                        )

                        photoInfoMut.add(photoObj)
                        adapter2 = ImagesAdapter(photoInfoMut)
                        adapter2.notifyItemInserted(b)
                        recycler_view_images.apply {
                            setHasFixedSize(true)
                            layoutManager = GridLayoutManager(this@MainActivity, 2)
                            adapter = adapter2
                        }

                        // Aqui estou a guardar os objetos na minha class (consigo mostrar os valores -> [x])

                        val destinationService = ServiceBuilder.buildService(ApiService::class.java)
                        val requestCall = destinationService.getSizesList(photoId)

                        requestCall.enqueue(object : Callback<GetSizesResponse> {
                            override fun onResponse(
                                call: Call<GetSizesResponse>,
                                response: Response<GetSizesResponse>
                            ) {
//                                Log.d("Response", "photoInfo size: ${photoInfo.info.size}") // -> Imprime sempre 100, Tamanho do photoInfo = 100
                                if (response.isSuccessful) {
                                    val photoList = response.body()!!
                                    // Para cada imagem, mostra cada label os tamanhos e urls
                                    for (i in photoList.sizes.size) {
                                        if (i.label == "Large Square") {
                                            photoObj.labelSquare = i.label
                                            photoObj.sourceSquare = i.source
                                            photoObj.heightSquare = i.height.toString()
                                            photoObj.widthSquare = i.width.toString()

                                        } else if (i.label == "Large") {
                                            photoObj.labelLarge = i.label
                                            photoObj.sourceLarge = i.source
                                            photoObj.heightLarge = i.height.toString()
                                            photoObj.widthLarge = i.width.toString()

                                        } else if (photoObj.sourceLarge == "") {
                                            photoObj.labelLarge = "Large"
                                            photoObj.sourceLarge = "No Image"
                                            photoObj.heightLarge = "500"
                                            photoObj.widthLarge = "500"

                                        } else if (photoObj.sourceSquare == "") {
                                            photoObj.labelSquare = "Large Square"
                                            photoObj.sourceSquare = "No Image"
                                            photoObj.heightSquare = "150"
                                            photoObj.widthSquare = "150"

                                        }
                                    }
                                    // Neste momento tem uma imagem de erro caso apareça uma imagem sem um dos tamanhos
                                    photoInfoMut.set(b, photoObj)
                                    // Para ir carregando enquanto se pode dar scroll
                                    adapter2.notifyItemChanged(b)

                                    Log.d("Respostas", "photoMUTABLE no A: ${b + 1}")
                                    b += 1

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
                } else {
                    Toast.makeText(this@MainActivity, "BENFICA", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<SearchPhotosResponse>, t: Throwable) {

                Toast.makeText(this@MainActivity, "Somathin wrong $t", Toast.LENGTH_LONG).show()
            }
        })
    }

}