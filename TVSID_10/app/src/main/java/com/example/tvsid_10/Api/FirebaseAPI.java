package com.example.tvsid_10.Api;

import android.net.Uri;

import com.example.tvsid_10.Entity.SinhVien;
import com.google.android.gms.tasks.OnSuccessListener;
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
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Uri downloadUrl = uri;
                        url[0] = downloadUrl.getPath();
                    }
                });
            }
        });
        return url[0];
    }
}
