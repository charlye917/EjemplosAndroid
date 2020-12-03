# Encrypt File y SharedPreferences

## Ejemplo del uso de la biblioteca de seguridad de android para
## File y SharedPreferences

### Ejemplo de uso 
<img src="01.gif" alt="drawing" width="400"/>

### Administración de claves

La biblioteca de seguridad utiliza un sistema de 2 partes para la administración de claves:

    - Un conjunto de claves que contiene una o más claves para encriptar un archivo o datos de preferencias compartidas. Se almacena el conjunto de claves en SharedPreferences.
    - Una clave principal (master) que encripta todos los conjuntos de claves. Esta se almacena utilizando el sistema del almacén de claves de Android.

### Clases incluidas

#### EncryptedFile
    - Proporciona implementaciones personalizadas de FileInputStream y FileOutputStream, lo que otorga a tu app operaciones de transmisión de lectura y escritura más seguras.

#### EncryptedSharedPreferences
    - Envuelve la clase SharedPreferences y encripta automáticamente claves y valores utilizando un método de dos esquemas:

        - Se encriptan las claves mediante un algoritmo de encriptación determinista, de modo que sea posible encriptar la clave y buscarla correctamente.
        - Se encriptan los valores con AES-256 GCM de una manera no determinista.

## Liga Documentacion
[Documentacion de la libreria Encrypt] (https://developer.android.com/topic/security/data)

