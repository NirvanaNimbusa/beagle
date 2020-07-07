package com.pandulapeter.beagle.core.util

import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.media.ImageReader
import android.media.ImageReader.OnImageAvailableListener
import android.os.Build
import android.os.Handler
import android.view.Surface
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
internal class ScreenshotWriter(
    private val width: Int,
    private val height: Int,
    handler: Handler,
    private val callback: (Bitmap) -> Unit
) : OnImageAvailableListener {

    val surface: Surface get() = imageReader.surface
    private val imageReader: ImageReader = ImageReader.newInstance(width, height, PixelFormat.RGBA_8888, 3)
    private var latestBitmap: Bitmap? = null

    init {
        imageReader.setOnImageAvailableListener(this, handler)
    }

    override fun onImageAvailable(reader: ImageReader) {
        val image = imageReader.acquireLatestImage()
        if (image != null) {
            val planes = image.planes
            val buffer = planes[0].buffer
            val pixelStride = planes[0].pixelStride
            val rowStride = planes[0].rowStride
            val rowPadding = rowStride - pixelStride * width
            val bitmapWidth = width + rowPadding / pixelStride
            if (latestBitmap?.width != bitmapWidth || latestBitmap?.height != height) {
                latestBitmap?.recycle()
                latestBitmap = Bitmap.createBitmap(bitmapWidth, height, Bitmap.Config.ARGB_8888)
            }
            latestBitmap?.copyPixelsFromBuffer(buffer)
            image.close()
            val byteArrayOutputStream = ByteArrayOutputStream()
            val croppedBitmap = Bitmap.createBitmap(latestBitmap!!, 0, 0, width, height)
            croppedBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            callback(croppedBitmap)
            imageReader.close()
        }
    }
}