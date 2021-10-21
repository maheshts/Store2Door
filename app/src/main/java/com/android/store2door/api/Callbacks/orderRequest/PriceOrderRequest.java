
package com.android.store2door.api.Callbacks.orderRequest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class PriceOrderRequest implements Serializable {

    @SerializedName("id")
    private String mId;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("quantity")
    private String mQuantity;
    @SerializedName("unit")
    private String mUnit;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String quantity) {
        mQuantity = quantity;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }

}
