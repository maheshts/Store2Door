package com.android.store2door.api.Callbacks.common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommonResponse implements Serializable {
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Long mStatus;

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
