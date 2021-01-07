# Ejemplos de Room con MVVM

## Room with MVVM and DataBinding
<img src="RoomDemo/1.gif" alt="drawing" width="300"/>

## Documentación
Estos son los 3 componentes principales de Room:

 -> Base de datos: Contiene el titular de la base de datos y sirve como punto de acceso principal para la conexión subyacente a los datos persistentes y relacionales de tu app.
    La clase anotada con @Database debe cumplir con las siguientes condiciones:

        - Ser una clase abstracta que extiende RoomDatabase
        - Incluir la lista de entidades asociadas con la base de datos dentro de la anotación
        - Contener un método abstracto que tenga 0 argumentos y muestre la clase anotada con @Dao
            En el entorno de ejecución, puedes adquirir una instancia de Database llamando a Room.databaseBuilder() o Room.inMemoryDatabaseBuilder().

-> Entidad: Representa una tabla dentro de la base de datos.

-> DAO: Contiene los métodos utilizados para acceder a la base de datos.

## Codigo
```kotlin
//DATABASE
@Database(entities = [Subscriber::class],version = 1)
abstract class SubscriberDatabase : RoomDatabase() {

    abstract val subscriberDAO : SubscriberDAO

    companion object{
      @Volatile
      private var INSTANCE : SubscriberDatabase? = null
          fun getInstance(context: Context): SubscriberDatabase {
              synchronized(this){
                  var instance = INSTANCE
                  if(instance==null){
                      instance = Room.databaseBuilder(
                          context.applicationContext,
                          SubscriberDatabase::class.java,
                          "subscriber_data_database"
                      ).build()
                  }
                  return instance
              }
          }

    }
}
```

```kotlin
//ENTIDAD
@Entity(tableName = "subscriber_data_table")
data class Subscriber (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscriber_name")
    var id : Int,

    @ColumnInfo(name = "subscriber_id")
    var name : String,

    @ColumnInfo(name = "subscriber_email")
    var email : String
)
```

```kotlin
//DAO
@Dao
interface SubscriberDAO {

    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber) : Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber) : Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber) : Int

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers():LiveData<List<Subscriber>>
}
```

linlk: https://developer.android.com/training/data-storage/room


