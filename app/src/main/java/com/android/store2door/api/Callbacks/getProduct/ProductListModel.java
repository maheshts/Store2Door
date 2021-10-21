
package com.android.store2door.api.Callbacks.getProduct;

import com.android.store2door.api.Callbacks.common.CommonResponse;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ProductListModel extends CommonResponse {

    @SerializedName("data")
    private DataProduct mDataProduct;


    public DataProduct getData() {
        return mDataProduct;
    }

    public void setData(DataProduct dataProduct) {
        mDataProduct = dataProduct;
    }


}
