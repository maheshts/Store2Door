
package com.android.store2door.api.Callbacks.loginResponse;

import com.android.store2door.api.Callbacks.common.CommonResponse;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LoginResponse extends CommonResponse {

    @SerializedName("data")
    private Data mData;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }


}
