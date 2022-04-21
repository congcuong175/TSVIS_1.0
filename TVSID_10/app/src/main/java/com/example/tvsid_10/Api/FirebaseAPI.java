package com.example.tvsid_10.Api;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.tvsid_10.Entity.SinhVien;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirebaseAPI {
    public StorageReference storageReference;
    public FirebaseStorage firebaseStorage;

    public FirebaseAPI() {
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference("Image");
    }

    public String uploadImage(Uri uri, SinhVien sinhVien) {
        final String[] url = {""};
        storageReference = storageReference.child(sinhVien.getID() + "").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Uri uri1 = task.getResult();
                        url[0] = uri1.toString();
                    }
                });
            }
        });
        return url[0];
    }
}
