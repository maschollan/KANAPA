package com.chollan.kanapa.helper

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Environment
import com.chollan.kanapa.R
import com.chollan.kanapa.model.LocationDetails
import com.chollan.kanapa.model.NearStore
import com.google.android.gms.maps.model.LatLng
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.*

fun Int.setAbsolute(): Int {
    return if (this < 0) -this
    else this
}

fun Int.getDistance(): String {
    return if (this.setAbsolute() < 1000) "${this}m"
    else {
        val km = String.format("%.1f", this.toFloat() / 1000)
        "${km}km"
    }
}

private const val FILENAME_FORMAT = "dd-MMM-yyyy"

val timeStamp: String = SimpleDateFormat(
    FILENAME_FORMAT,
    Locale.US
).format(System.currentTimeMillis())

fun createCustomTempFile(context: Context): File {
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(timeStamp, ".jpg", storageDir)
}

fun createFile(application: Application): File {
    val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
        File(it, application.resources.getString(R.string.app_name)).apply { mkdirs() }
    }

    val outputDirectory = if (
        mediaDir != null && mediaDir.exists()
    ) mediaDir else application.filesDir

    return File(outputDirectory, "$timeStamp.jpg")
}

fun rotateFile(file: File, isBackCamera: Boolean = false) {
    val matrix = Matrix()
    val bitmap = BitmapFactory.decodeFile(file.path)
    val rotation = if (isBackCamera) 90f else -90f
    matrix.postRotate(rotation)
    if (!isBackCamera) {
        matrix.postScale(-1f, 1f, bitmap.width / 2f, bitmap.height / 2f)
    }
    val result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    result.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(file))
}

fun calculateScaleFactor(imageWidth: Int, imageHeight: Int, maxWidth: Int, maxHeight: Int): Int {
    var scaleFactor = 1

    if (imageWidth > maxWidth || imageHeight > maxHeight) {
        val widthScaleFactor = imageWidth / maxWidth
        val heightScaleFactor = imageHeight / maxHeight
        scaleFactor = if (widthScaleFactor > heightScaleFactor) {
            widthScaleFactor
        } else {
            heightScaleFactor
        }
    }

    return scaleFactor
}

fun resizeImage(file: File, maxWidth: Int, maxHeight: Int) {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(file.absolutePath, options)

    val imageWidth = options.outWidth
    val imageHeight = options.outHeight

    val scaleFactor = calculateScaleFactor(imageWidth, imageHeight, maxWidth, maxHeight)

    options.inJustDecodeBounds = false
    options.inSampleSize = scaleFactor

    val bitmap = BitmapFactory.decodeFile(file.absolutePath, options)

    val outputStream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
    outputStream.flush()
    outputStream.close()
}

fun uriToFile(selectedImg: Uri, context: Context): File {
    val contentResolver: ContentResolver = context.contentResolver
    val myFile = createCustomTempFile(context)

    val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
    val outputStream: OutputStream = FileOutputStream(myFile)
    val buf = ByteArray(1024)
    var len: Int
    while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
    outputStream.close()
    inputStream.close()

    return myFile
}

fun NearStore.toLatLng(): LatLng {
    return LatLng(this.lat.toDouble(), this.lon.toDouble())
}

fun calculateDistance(location1: LocationDetails, location2: LocationDetails): Double {
    val earthRadius = 6371
    val lat1 = Math.toRadians(location1.latitude)
    val lon1 = Math.toRadians(location1.longitude)
    val lat2 = Math.toRadians(location2.latitude)
    val lon2 = Math.toRadians(location2.longitude)

    val dlon = lon2 - lon1
    val dlat = lat2 - lat1

    val a = sin(dlat / 2).pow(2) + cos(lat1) * cos(lat2) * sin(dlon / 2).pow(2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    return earthRadius * c
}

fun LocationDetails.distanceTo(locationOther: LocationDetails): Double {
    return calculateDistance(this, locationOther)
}

fun List<NearStore>.sortByDistance(currentLocation: LocationDetails): List<NearStore> {
    for (nearStore in this) {
        nearStore.distance = calculateDistance(
            currentLocation,
            LocationDetails(nearStore.lat.toDouble(), nearStore.lon.toDouble())
        )
    }

    return this.sortedBy { it.distance }
}