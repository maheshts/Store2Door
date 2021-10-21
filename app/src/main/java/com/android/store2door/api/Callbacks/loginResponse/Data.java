
package com.android.store2door.api.Callbacks.loginResponse;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Data {
    @SerializedName("data")
    private DataResponse mData;

    @SerializedName("token")
    private String mToken;


    public DataResponse getData() {
        return mData;
    }

    public void setData(DataResponse data) {
        mData = data;
    }


    public String getToken() {
        return "Bearer "+mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

}
