package com.example.tvsid_10;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import com.example.tvsid_10.Api.ApiService;
import com.example.tvsid_10.Common.Common;
import com.example.tvsid_10.Common.RealPathUtils;
import com.example.tvsid_10.Entity.SinhVien;
import com.example.tvsid_10.ml.Model;
import com.google.common.util.concurrent.ListenableFuture;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangkytaikhoanFragment extends Fragment {

    public DangkytaikhoanFragment() {
        // Required empty public constructor
    }

    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    PreviewView previewView;
    ImageView btn_cap;
    Model model;
    List<String> labels;
    TextView tv_noti;
    boolean predict = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        requestPermission();
        cameraProviderFuture = ProcessCameraProvider.getInstance(getContext());
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {

            }
        }, ContextCompat.getMainExecutor(getContext()));
        onClick();
        initilalize();
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            public void run() {
                // need to do tasks on the UI thread
                double[] preds = predict();
                Log.e("Predict", "pred:" + preds[0] + "|Acc:" + preds[1]);
                if (preds[0] == 0 && preds[1] > 0.95) {
                    predict = true;
                    tv_noti.setText("Thẻ sinh viên,acc=" + (int) (preds[1] * 100) + "%");
                } else {
                    predict = false;
                    tv_noti.setText("Đây không phải thẻ sinh viên");
                }
                handler.postDelayed(this, 500);
            }
        };
        handler.post(runnable);
    }

    private void onClick() {
        btn_cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(predict){
                    ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setTitle("Đang chờ");
                    dialog.show();
                    Bitmap bitmap = previewView.getBitmap();
                    String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bitmap, "lalala" + new Random().nextDouble(), null);
                    Uri uri = Uri.parse(path);
                    String realFilePath = RealPathUtils.getRealPath(getContext(), uri);
                    File file = new File(realFilePath);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("image", "dmm", requestBody);
                    ApiService.api.detectIDSV(body).enqueue(new Callback<SinhVien>() {
                        @Override
                        public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
                            if (call.isExecuted()) {
                                file.delete();
                                dialog.dismiss();
                                if (response.body() == null) {
                                    Toast.makeText(getContext(), "Lỗi kết  nối", Toast.LENGTH_LONG).show();
                                } else {
                                    Common.sinhVien = response.body();
                                    startActivity(new Intent(getContext(), ThongTinDangKyActivity.class));
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<SinhVien> call, Throwable t) {
                            dialog.dismiss();
                            Log.e("TAG", t.getMessage());
                        }
                    });
                }

            }
        });

    }

    //khởi tạo model
    private void initilalize() {
        try {
            model = Model.newInstance(getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        labels = getLabels();

    }

    private List<String> getLabels() {
        List<String> labels = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getContext().getAssets().open("labels.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                labels.add(mLine);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return labels;
    }

    //predict
    public double[] predict() {
        double[] preds = new double[2];
        TensorBuffer inputFeature = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
        // Creates inputs for reference.
        if (previewView.getBitmap() != null) {
            ByteBuffer bytebuff = convertBitmapToByteBuffer(previewView.getBitmap());
            inputFeature.loadBuffer(bytebuff);
            Model.Outputs outputs = model.process(inputFeature);
            TensorBuffer outputFeature = outputs.getOutputFeature0AsTensorBuffer();
            int max = getMax(outputFeature.getFloatArray());
            preds[0] = max;
            preds[1] = outputFeature.getFloatArray()[max];
        }
        return preds;

    }

    //convert bitmap to bytebuffer
    private static final float IMAGE_MEAN = 127.5f;
    private static final float IMAGE_STD = 127.5f;

    private ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {

        bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, false);


        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3);
        byteBuffer.order(ByteOrder.nativeOrder());
        int[] intValues = new int[224 * 224];


        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        int pixel = 0;
        for (int i = 0; i < 224; i++) {
            for (int j = 0; j < 224; j++) {
                int input = intValues[pixel++];
                byteBuffer.putFloat((((input >> 16 & 0xFF) - IMAGE_MEAN) / IMAGE_STD));
                byteBuffer.putFloat((((input >> 8 & 0xFF) - IMAGE_MEAN) / IMAGE_STD));
                byteBuffer.putFloat((((input & 0xFF) - IMAGE_MEAN) / IMAGE_STD));
            }
        }
        return byteBuffer;
    }

    private int getMax(float[] arr) {
        int index = 0;
        float min = 0.0f;
        for (int i = 0; i < labels.size(); i++) {
            if (arr[i] > min) {
                index = i;
                min = arr[i];
            }
        }
        return index;
    }

    private void initView(View view) {
        previewView = view.findViewById(R.id.pv_camera);
        btn_cap = view.findViewById(R.id.tmv_chupanh_dky);
        tv_noti = view.findViewById(R.id.tv_noti);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 12345);
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 4565);
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 456);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dangkytaikhoan, container, false);
    }

    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview);
    }
}