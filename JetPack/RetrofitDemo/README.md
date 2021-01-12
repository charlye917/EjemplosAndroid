# Retrofit

## Data class
´´´kotlin
data class AlbumItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)
´´´
## Interface Service
´´´kotlin
interface AlbumService {
    @GET("/albums")
    suspend fun getAlbums(): Response<Album>

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int): Response<Album>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") id: String): Response<AlbumItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body album:AlbumItem): Response<AlbumItem>
}
´´´
## Config retrofit
´´´kotlin
class RetrofitInstance {

    companion object{
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25,TimeUnit.SECONDS)
        }.build()

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}
´´´

## Instance Retrofit 
```kotlin
retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)
```

## Consumo con LiveData
```kotlin
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
```



