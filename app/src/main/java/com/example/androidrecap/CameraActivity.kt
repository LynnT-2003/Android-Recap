package com.example.androidrecap

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import com.example.androidrecap.databinding.ActivityCameraBinding
import com.example.androidrecap.utils.PermissionHandler
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {
    private val view: ActivityCameraBinding by lazy { ActivityCameraBinding.inflate(layoutInflater) }
    private val cameraExecutor: ExecutorService by lazy { Executors.newSingleThreadExecutor() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        PermissionHandler.requestPermissionIfRequired(this, PermissionHandler.CAMERA)

        /*
        1. Controller (Flip, Capture, Camera settings, etc.)
        2. Assign Controller to UI
        3. Control Controller from Activity
         */


        // 1. Controller
        val cameraController = LifecycleCameraController(this)
        cameraController.setEnabledUseCases(
            CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
        )
        // additional settings
        cameraController.isPinchToZoomEnabled = true
        cameraController.isTapToFocusEnabled = true
        cameraController.imageCaptureMode = ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
        cameraController.imageCaptureTargetSize = CameraController.OutputSize(Size(1024, 1024))


        // 2. Assign Controller to UI
        view.pvCamera.controller = cameraController
        cameraController.bindToLifecycle(this)

        // 3. Control controller from Activity
//        if (cameraController.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA)) {
//            cameraController.cameraSelector =
//                CameraSelector.DEFAULT_BACK_CAMERA // set backCam as default
//        }

        val outputOption = ImageCapture.OutputFileOptions.Builder(
            contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            ContentValues()
        ).build()

        // Save directly to Gallery based on outputOptions
//        view.btnCapture.setOnClickListener {
//            cameraController.takePicture(
//                outputOption,
//                cameraExecutor,
//                object : ImageCapture.OnImageSavedCallback {
//                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
//                        Log.e("DAT", "Capture Image Successful")
//                    }
//
//                    override fun onError(exception: ImageCaptureException) {
//                        Log.e("DAT", exception.message.toString())
//                    }
//
//                })
//        }

        // Don't save to Gallery yet, adjust settings before saving, etc.
        view.btnCapture.setOnClickListener {
            cameraController.takePicture(cameraExecutor,
                object : ImageCapture.OnImageCapturedCallback() {
                    override fun onCaptureSuccess(image: ImageProxy) {
                        Log.e("OAT", "Capture Image Successful")
                        super.onCaptureSuccess(image)
                        showImage(image)
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Log.e("OAT", exception.message.toString())
                        super.onError(exception)
                    }
                })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun showImage(image: ImageProxy) {
        val bitmap = imageProxyToBitmap(image)

        this@CameraActivity.runOnUiThread {
            AlertDialog.Builder(this@CameraActivity)
                .setView(ImageView(this@CameraActivity).apply {
                    setImageBitmap(bitmap)
                }).setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }.create()
                .show()
        }
    }

    private fun imageProxyToBitmap(image: ImageProxy): Bitmap {
        val byteBuffer = image.planes[0].buffer
        val bytes = ByteArray(byteBuffer.remaining())
        byteBuffer.get(bytes)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
}