<!-- PROJECT LOGO -->  
<br />  
<div align="center">  
  
<img src="https://github.com/rarangogi/tmp/blob/main/Screenshot%202022-11-05%20at%202.22.40%20PM.png?raw=true" align="center">  
  
  <p align="center">  
    Proyecto de Android desarrollado por el equipo 26 de MISO. Para el curso de Ingeniería de sofware para aplicaciones móviles 2022-15.  
  </p>  
<p align="left">  
    Para más información sobre el proyecto, su diseño y sobre la gestión del equipo durante su desarrollo, usted puede visitar  la wiki y el tablero kanban del proyecto.  
  </p>  
<img src="https://camo.githubusercontent.com/659a5990dce5fe14f8682e5ab72320da267738c0b2f84ddbb033eb2c41ccc8b3/68747470733a2f2f73697374656d61732e756e69616e6465732e6564752e636f2f696d616765732f686561646572732f3636333336366f2e706e67">
</div>  
  
  
  
<!-- ABOUT THE PROJECT -->  
## Acerca del proyecto  

* Proyecto construido con  [Kotlin](https://kotlinlang.org/)
* La versión de la API de android usada en el desarrollo es Lollipop. Garantizando así que para el año en curso la app funcione en más del 91% de los dispositivos android.
* El estilo arquitectónico que se definió es el de una app con backend.  Para nuestro caso puntual, el backend de nuestra app se en este [repo](https://github.com/MISW-4104-Web/BackVynils)
* Utilizando el patron [MVVM](https://developer.android.com/jetpack/guide?hl=es-419)
  
## Getting Started  
  
Este es un ejemplo de como debes configurar el projecto para correrlo localmente  
  
### Pre-requisitos  
* [Git](https://git-scm.com)
* [Android Studio](https://www.googleadservices.com/pagead/aclk?sa=L&ai=DChcSEwiJ6PmJ54f0AhWCoYYKHQsLAu8YABAAGgJ2dQ&ohost=www.google.com&cid=CAESQOD2CpmvaJ7w6L2exjF1tvuvqVaMAG_80FgpXq3E_s-rO2DaFiyL6XC_m7q3MGR2KmZTsKgYJW6KaqoeOIoXcVg&sig=AOD64_0G6D7MWUMOnBu0aGsX6GIs6MJgxg&q&adurl&ved=2ahUKEwi95_KJ54f0AhU0szEKHUzjDqkQ0Qx6BAgCEAE)  
  
### Instalacion y ejecucion  
  
Puede realizar la instalacion y ejecucion con 2 metodos:   
  
1. Instalar desde codigo fuente:  
   1. Clonar el repositorio  
   `git clone https://github.com/mgdarwin/VinilosMovilApp.git`  
  Correr utilizando android studio  
  
2. Descargue el apk que se encuentra en la seccion de Sprint 3 en la wiki e instalelo en su dispositivo android.   
  
### Pruebas  
  
Para la ejecución de pruebas, se está utilizando Espresso, que es una tecnología propia de Android y permite ejecutar pruebas E2E de la aplicación. En este caso, y dada la naturaleza asíncrona del acceso a los datos y se recomienda desactivar las animaciones del dispositivo en el que se va a probar la aplicación. Puede hacerlo siguiendo los pasos de este [enlace](https://mcmw.abilitynet.org.uk/how-disable-interface-animations-android-10)
  
Esta aplicación ha sido probada en los siguientes dispositivos:  
  
- Nokia 8 - Android 9 - Modelo TA-1012  
- Motorola - Moto  - G Android: 5.1 Lollipop
- Samsung Galaxy S20 - Android 11  
- Motorola Z3 play - Android: 9 Pie  
  
  
*Si prueba la aplicación en un dispositivo diferente y encuentra algún error, agradecemos que nos informe creando un Issue en este repositorio, describiendo el tipo de dispositivo, sistema operativo, y condiciones adicionales que nos ayuden a solucionar el bug.*
