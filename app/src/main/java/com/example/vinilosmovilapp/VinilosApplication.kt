package com.example.vinilosmovilapp

import android.app.Application
import com.example.vinilosmovilapp.database.VinylRoomDatabase

class VinilosApplication : Application() {
    val database by lazy { VinylRoomDatabase.getDatabase(this) }
}
