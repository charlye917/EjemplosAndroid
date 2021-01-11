package com.charlye934.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.charlye934.retrofitdemo.model.Album
import com.charlye934.retrofitdemo.model.AlbumItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retService: AlbumService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        getRequestWithQueryParameters()
        //getRequestWithPathParameters()
        //uploadAlbums()
    }

    private fun getRequestWithQueryParameters(){

        val responseLiveData: LiveData<Response<Album>> = liveData {
            val response = retService.getSortedAlbums(3)
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if(albumList != null){
                while (albumList.hasNext()){
                    val albumsItem = albumList.next()
                    val result = " " + "Album title: ${albumsItem.title}" + " \n" +
                            " " + " Album id: ${albumsItem.id}"  + "\n" +
                            " " + " Album userId: ${albumsItem.userId}" + "\n\n"
                    Log.d("__TAG",albumsItem.title)
                    textView.append(result)
                }
            }
        })
    }

    private fun getRequestWithPathParameters(){
        //path parameters example
        val pathResponse: LiveData<Response<AlbumItem>> = liveData {
            val respose = retService.getAlbum("3")
            emit(respose)
        }

        pathResponse.observe(this, Observer {
            val albumList = it.body()?.title
            Toast.makeText(this, "$albumList", Toast.LENGTH_SHORT).show()
        })
    }

    private fun uploadAlbums(){
        val albums = AlbumItem(0, "Mytitle", 1)

        val postResponse: LiveData<Response<AlbumItem>> = liveData {
            val response = retService.uploadAlbum(albums)
            emit(response)
        }

        postResponse.observe(this, Observer {
            val albumList = it.body()

            val result = " " + "Album title: ${albumList!!.title}" + " \n" +
                    " " + " Album id: ${albumList.id}"  + "\n" +
                    " " + " Album userId: ${albumList.userId}" + "\n\n"
        })
    }
}