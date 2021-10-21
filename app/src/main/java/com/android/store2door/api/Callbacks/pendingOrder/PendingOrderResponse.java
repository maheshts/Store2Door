
package com.android.store2door.api.Callbacks.pendingOrder;

import java.io.Serializable;
import java.util.List;

import com.android.store2door.api.Callbacks.common.CommonResponse;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PendingOrderResponse extends CommonResponse implements Serializable {

    @SerializedName("data")
    private List<Datum> mData;

    public int getImportedProductCount() {
        return mImportedProductCount;
    }

    public void setImportedProductCount(int mImportedProductCount) {
        this.mImportedProductCount = mImportedProductCount;
    }

    @SerializedName("imported_product_count")
    private int mImportedProductCount;


    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }


}
