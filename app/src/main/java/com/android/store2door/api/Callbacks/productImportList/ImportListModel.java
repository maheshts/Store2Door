
package com.android.store2door.api.Callbacks.productImportList;

import java.io.Serializable;
import java.util.List;

import com.android.store2door.api.Callbacks.common.CommonResponse;
import com.android.store2door.api.Callbacks.importProduct.DataImportModel;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ImportListModel extends CommonResponse implements Serializable {

    @SerializedName("data")
    private DataImportModel mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Long mStatus;

    public DataImportModel getData() {
        return mData;
    }

    public void setData(DataImportModel data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }


}
