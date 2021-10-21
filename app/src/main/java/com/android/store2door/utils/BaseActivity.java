package com.android.store2door.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;

import androidx.appcompat.app.AppCompatActivity;


public  abstract class BaseActivity extends AppCompatActivity {

    public SessionManager sessionManager;
    public Context mContext;
    private CustomProgressDialog progressDialog;

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            for (NetworkInfo anInfo : info)
                if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }

        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mContext=this;
        sessionManager=new SessionManager(mContext);

    }

    public void showProgressDialog(){
        if(progressDialog == null) progressDialog = new CustomProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    public void hideProgressDialog(){
        if(progressDialog != null) progressDialog.dismiss();
    }





}
