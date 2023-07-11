package com.eva.androidweatherapp.widgets

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import com.eva.androidweatherapp.widgets.data.SerializedResource
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.io.InputStream
import java.io.OutputStream

@OptIn(ExperimentalSerializationApi::class)
object WeatherDataDefinition : GlanceStateDefinition<SerializedResource> {

    const val fileName = "_WIDGET_WEATHER_DATASTORE_FILE"

    private val Context.weatherDataStore by dataStore(fileName, CurrentWeatherModelSerializer)


    override suspend fun getDataStore(
        context: Context,
        fileKey: String
    ): DataStore<SerializedResource> = context.weatherDataStore

    override fun getLocation(context: Context, fileKey: String): File =
        context.dataStoreFile(fileName)

    object CurrentWeatherModelSerializer :
        Serializer<SerializedResource> {

        private val kSerializable = SerializedResource.serializer()

        override val defaultValue: SerializedResource
            get() = SerializedResource.IsLoading


        override suspend fun readFrom(input: InputStream): SerializedResource {
            return try {
                input.use { stream ->
                    Json.decodeFromStream(kSerializable, stream)
                }
            } catch (exception: SerializationException) {
                throw CorruptionException("Could not read location data: ${exception.message}")
            } catch (e: Exception) {
                throw e
            }
        }

        override suspend fun writeTo(t: SerializedResource, output: OutputStream) {
            output.use { stream ->
                Json.encodeToStream(kSerializable, t, stream)
            }
        }
    }

}