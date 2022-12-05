package com.example.vinilosmovilapp.database

import androidx.room.TypeConverter
import com.example.vinilosmovilapp.models.Artist
import com.example.vinilosmovilapp.models.Comment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

public class DataConvert:java.io.Serializable {
    @TypeConverter
    fun stringToListServer(data: String?): List<Comment?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<Comment?>?>() {}.type
        return Gson().fromJson<List<Comment?>>(data, listType)
    }

    @TypeConverter
    fun listServerToString(someObjects: List<Comment?>?): String? {
        return Gson().toJson(someObjects)
    }

    @TypeConverter
    fun stringToListServer2(data: String?): List<Artist?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<Artist?>?>() {}.type
        return Gson().fromJson<List<Artist?>>(data, listType)
    }

    @TypeConverter
    fun listServerToString2(someObjects: List<Artist?>?): String? {
        return Gson().toJson(someObjects)
    }
}