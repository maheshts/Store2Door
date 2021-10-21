package com.android.store2door.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public SessionManager sessionManager;
    public Context mContext;
    private CustomProgressDialog progressDialog;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            mContext = getActivity();
            sessionManager = new SessionManager(mContext);
        }
    }

    public void showProgressDialog(){
        if(progressDialog == null) progressDialog = new CustomProgressDialog(mContext);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    public void hideProgressDialog(){
        if(progressDialog != null) progressDialog.dismiss();
    }



}
