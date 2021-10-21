
package com.android.store2door.api.Callbacks.orderRequest;

import java.io.Serializable;
import java.util.List;

import com.android.store2door.api.Callbacks.common.CommonResponse;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class OrderRequestModel extends CommonResponse implements Serializable {

    @SerializedName("data")
    private List<DatumOrderRequest> mData;

    public List<DatumOrderRequest> getData() {
        return mData;
    }

    public void setData(List<DatumOrderRequest> data) {
        mData = data;
    }

}
