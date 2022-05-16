package com.example.tvsid_10.Activity

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.tvsid_10.Adapter.FaceAdapter
import com.example.tvsid_10.Common.Common
import com.example.tvsid_10.R
import com.example.tvsid_10.ml.Facenet
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException
import kotlinx.android.synthetic.main.activity_face_update.*
class FaceUpdateActivity : AppCompatActivity() {
    lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    var bitmapList: ArrayList<Bitmap> = ArrayList()
    lateinit var inputFeature: TensorBuffer
    lateinit var highAccuractiyOpts: FaceDetectorOptions
    lateinit var faceAdapter: FaceAdapter
    lateinit var faceDetector: FaceDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_update)
        checkPermission()

        cameraProviderFuture = ProcessCameraProvider.getInstance(applicationContext)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(applicationContext))
        setupFaceDetector()
        instanceModel()
        loadAdapter()
        onClick()
    }


    //set adapter for recycleview
    private fun loadAdapter() {
        faceAdapter = FaceAdapter(bitmapList, applicationContext)
        rv_face.adapter = faceAdapter
    }

    private fun onClick() {

        btn_cap.setOnClickListener({
            var bitmap = preview_camera.bitmap
            val image = InputImage.fromBitmap(bitmap!!, 0)
            faceDetector.process(image).addOnSuccessListener({ faces ->
                for (face in faces) {
                    val bounds = face.boundingBox
                    val left = bounds.left.toFloat()
                    val right = bounds.right.toFloat()
                    val top = bounds.top.toFloat()
                    val bottom = bounds.bottom.toFloat()
                    var faceCrop = Bitmap.createBitmap(
                        bitmap,
                        left.toInt(),
                        top.toInt(),
                        (right - left).toInt(),
                        (bottom - top).toInt()
                    )
                    bitmapList.add(faceCrop)
                    faceAdapter.notifyDataSetChanged()
                    if (bitmapList.size == 5) {
                        getFeatures()
                    }
                    break
                }
            })

        })
    }

    private fun duDoan(bitmap: Bitmap) {
        val byteBuffer =
            Common.convertBitmapToByteBuffer(bitmap)
        inputFeature.loadBuffer(byteBuffer)
        val outputs = model!!.process(inputFeature)
        val outputBuffer = outputs.outputFeature0AsTensorBuffer
        val predict = outputBuffer.floatArray
        val tmp = sort(predict)
        val pred = IntArray(Common.predicts.size)
        val prob =
            FloatArray(Common.predicts.size)
        Log.e("predict", predict.size.toString() + "")
        for (i in Common.predicts.indices) {
            Log.e(
                "predict", "index: " + findIndex(
                    outputBuffer.floatArray,
                    tmp!![i]
                ) + " prob:" + predict[i] + ""
            )
            prob[i] = tmp[i]
            pred[i] = findIndex(outputBuffer.floatArray, tmp[i])
        }
        Log.e("predict", "acc=" + acc(pred, prob))



    }

    private fun getFeatures() {
        val progressBar = ProgressDialog(this)
        progressBar.setTitle("Đang cập nhật khuôn mặt")
        progressBar.show()
        Common.predicts = ArrayList()
        for (bitmap in bitmapList) {
            val byteBuffer = Common.convertBitmapToByteBuffer(bitmap)
            inputFeature.loadBuffer(byteBuffer)
            val outputs = model!!.process(inputFeature)
            val outputBuffer = outputs.outputFeature0AsTensorBuffer
            val predict = outputBuffer.floatArray
            val tmp = sort(predict)
            Log.e("predict", predict.size.toString() + "")
            for (i in 0..2) {
                Common.predicts.add(
                    findIndex(
                        outputBuffer.floatArray,
                        tmp!![i]
                    )
                )
                Log.e(
                    "predict",
                    "index: " + findIndex(
                        outputBuffer.floatArray,
                        tmp!![i]
                    ) + " prob:" + predict[i] + ""
                )
            }
        }
        progressBar.dismiss()
        finish()
        val set: Set<Int> = LinkedHashSet(Common.predicts)
        Common.predicts = ArrayList(set)
        for (i in Common.predicts.indices) {
            Log.e("predict", Common.predicts[i].toString() + "")
        }
    }

    private fun setupFaceDetector() {
        inputFeature = TensorBuffer.createFixedSize(intArrayOf(1, 160, 160, 3), DataType.FLOAT32)
        highAccuractiyOpts = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()
        faceDetector = FaceDetection.getClient(highAccuractiyOpts)
    }

    private fun sort(arr: FloatArray): FloatArray? {
        val n = arr.size
        for (i in 0 until n - 1) for (j in 0 until n - i - 1) if (arr[j] < arr[j + 1]) {
            // swap arr[j+1] và arr[i]
            val temp = arr[j]
            arr[j] = arr[j + 1]
            arr[j + 1] = temp
        }
        return arr
    }

    private fun findIndex(arr: FloatArray, prob: Float): Int {
        var index = -1
        for (i in arr.indices) {
            if (arr[i] == prob) {
                index = i
            }
        }
        return index
    }

    var model: Facenet? = null

    //instance model
    private fun instanceModel() {
        try {
            model = Facenet.newInstance(this)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun acc(predict: IntArray, prob: FloatArray): Float {
        var count = 0
        for (i in predict.indices) {
            for (integer in Common.predicts) {
                if (predict[i] == integer) {
                    count++
                }
            }
        }
        return count.toFloat()/Common.predicts.size
    }


    //check quyền camera
    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            else -> {
                // You can directly ask for the permission.
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 123)
            }
        }
    }

    //show preview cho camera x
    private fun bindPreview(@NonNull cameraProvider: ProcessCameraProvider) {
        val preview = Preview.Builder()
            .build()
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
            .build()
        preview.setSurfaceProvider(preview_camera.surfaceProvider)
        val camera =
            cameraProvider.bindToLifecycle((this as LifecycleOwner), cameraSelector, preview)
    }
}