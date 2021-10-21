package com.android.store2door.api.Callbacks.orderRequest;

import com.google.gson.annotations.SerializedName;

public class OrderRequestAdapterModel {

    @SerializedName("price_id")
    private String mPriceId;
    @SerializedName("name")
    private String mName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("image")
    private String mImage;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("product_id")
    private String mProductId;
    @SerializedName("quantity")
    private String mQuantity;
    @SerializedName("selectQuantity")
    private String mSelectQuantity;
    @SerializedName("selectPrice")
    private String mSelectPrice;
    @SerializedName("selected")
    private boolean mSelected;
    @SerializedName("unit")
    private String mUnit;
    @SerializedName("order_id")
    private String mOrderId;

    public String getOrderId() {
        return mOrderId;
    }

    public void setOrderId(String orderId) {
        mOrderId = orderId;
    }

    public String getSelectPrice() {
        return mSelectPrice;
    }

    public void setSelectPrice(String mSelectPrice) {
        this.mSelectPrice = mSelectPrice;
    }

    public String getSelectQuantity() {
        return mSelectQuantity;
    }

    public void setSelectQuantity(String mSelectQuantity) {
        this.mSelectQuantity = mSelectQuantity;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean mSelected) {
        this.mSelected = mSelected;
    }

    public String getPriceId() {
        return mPriceId;
    }

    public void setPriceId(String mPriceId) {
        this.mPriceId = mPriceId;
    }


    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }


    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getProductId() {
        return mProductId;
    }

    public void setProductId(String mProductId) {
        this.mProductId = mProductId;
    }

    public String getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String mUnit) {
        this.mUnit = mUnit;
    }

}
