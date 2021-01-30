# Proyecto Movies con MVVM y Dagger

## Proyecto
<img src="1.gif" alt="drawing" width="300"/>

## Uso de Dagger

### Implementacion en Fragment
```kotlin
@Inject
    lateinit var factory: MovieViewModelFactory

(context.applicationContext as Injector).createMovieSubComponent()
            .inject(this)    
```

### App Implementacion de dagger
```kotlin
class App : Application(), Injector {

    private lateinit var appComponet: AppComponent

    override fun onCreate(){
        super.onCreate()

       appComponet = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
           .repositoryModule(RepositoryModule(BuildConfig.API_KEY))
           .build()
    }

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponet.movieSubComponent().create()
    }

    override fun createTvShowSubcomponent(): TvShowSubComponent {
        return appComponet.tvShowSubComponent().create()
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
        return appComponet.arttistSubComponent().create()
    }
}
```

### Injector
```kotlin
interface Injector {
    fun createMovieSubComponent(): MovieSubComponent
    fun createTvShowSubcomponent(): TvShowSubComponent
    fun createArtistSubComponent(): ArtistSubComponent
}
```

### App Component
```kotlin
@Singleton
@Component(modules = [
    AppModule::class,
    DataBaseModule::class,
    InteractorModule::class,
    NetModule::class,
    RepositoryModule::class
])
interface AppComponent {
    fun movieSubComponent(): MovieSubComponent.Factory
    fun tvShowSubComponent(): TvShowSubComponent.Factory
    fun arttistSubComponent(): ArtistSubComponent.Factory
}
```

### AppModule
```kotlin
@Module(subcomponents = [
    MovieSubComponent::class,
    TvShowSubComponent::class,
    ArtistSubComponent::class
])
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context{
        return context.applicationContext
    }
}
```

### MovieSubcomponent
```kotlin
@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {
    fun inject(movieFragment: MovieFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): MovieSubComponent
    }
}
```

### MovieModule
```kotlin
@Module
class MovieModule {
    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        movieInteractor: MovieInteractor
    ): MovieViewModelFactory{
        return MovieViewModelFactory(
            movieInteractor
        )
    }
}
´´´

### DatabaseModule
```kotlin
@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideMovieDataBase(context: Context): TMDBDatabase{
        return Room.databaseBuilder(context, TMDBDatabase::class.java, "tmdbclient")
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase): MovieDao{
        return tmdbDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideTvDao(tmdbDatabase: TMDBDatabase): TVShowDao {
        return tmdbDatabase.tvDao()
    }

    @Singleton
    @Provides
    fun provideArtistDao(tmdbDatabase: TMDBDatabase): ArtistDao{
        return tmdbDatabase.artistDao()
    }
}
```
### InteractorModule
```kotlin
@Module
class InteractorModule {

    @Provides
    fun provideMovieInteractor(movieRepository: MovieRepository): MovieInteractor{
        return MovieInteractorImp(movieRepository)
    }

    @Provides
    fun provideArtistInteractor(artistRepository: ArtistRepository): ArtistInteractor {
        return ArtistInteractorImp(artistRepository)
    }

    @Provides
    fun provideTvShowInteractor(tvShowRepository: TvShowRepository): TvShowInteractor{
        return TvShowInteractorImp(tvShowRepository)
    }
}
```
### NetModule
```kotlin
@Module
class NetModule(private val baseUrl: String){

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideTMDBService(retrofit: Retrofit): TMDBService{
        return retrofit.create(TMDBService::class.java)
    }
}
```
### RepositoryModule
```kotlin
@Module
class RepositoryModule(
    private val apiKey: String
) {

    @Singleton
    @Provides
    fun provideMovieDataSources(
        movieDao: MovieDao,
        tmdbService: TMDBService,
    ): MovieRepository{
        return MovieRepositoryImp(
            movieDao,
            tmdbService,
            apiKey
        )
    }

    @Singleton
    @Provides
    fun provideTvDataSources(
        tvShowDao: TVShowDao,
        tmdbService: TMDBService,
    ): TvShowRepository{
        return TvShowRepositoryImp(
            tvShowDao,
            tmdbService,
            apiKey
        )
    }

    @Singleton
    @Provides
    fun provideArtistDataSources(
        artsitDao: ArtistDao,
        tmbdService: TMDBService,
    ): ArtistRepository{
        return ArtistRepositoryImp(
            artsitDao,
            tmbdService,
            apiKey
        )
    }
}
```





