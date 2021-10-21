
package com.android.store2door.api.Callbacks.productImportList;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class DatumImportList implements Serializable {

    @SerializedName("id")
    private String mId;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("price_id")
    private String mPriceId;
    @SerializedName("product")
    private ProductImportList mProductImportList;
    @SerializedName("product_id")
    private String mProductId;
    @SerializedName("qty")
    private String mQty;
    @SerializedName("unit")
    private UnitImportList mUnitImportList;

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

    public String getPriceId() {
        return mPriceId;
    }

    public void setPriceId(String priceId) {
        mPriceId = priceId;
    }

    public ProductImportList getProduct() {
        return mProductImportList;
    }

    public void setProduct(ProductImportList productImportList) {
        mProductImportList = productImportList;
    }

    public String getProductId() {
        return mProductId;
    }

    public void setProductId(String productId) {
        mProductId = productId;
    }

    public String getQty() {
        return mQty;
    }

    public void setQty(String qty) {
        mQty = qty;
    }

    public UnitImportList getUnit() {
        return mUnitImportList;
    }

    public void setUnit(UnitImportList unitImportList) {
        mUnitImportList = unitImportList;
    }

}
