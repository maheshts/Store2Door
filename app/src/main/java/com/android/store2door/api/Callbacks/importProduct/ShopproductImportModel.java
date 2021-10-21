
package com.android.store2door.api.Callbacks.importProduct;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class ShopproductImportModel implements Serializable {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("id")
    private int mId;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("price_id")
    private Long mPriceId;
    @SerializedName("product_id")
    private Long mProductId;
    @SerializedName("qty")
    private String mQty;
    @SerializedName("shop_id")
    private Long mShopId;
    @SerializedName("unit")
    private UnitImportModel mUnitImportModel;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public Long getPriceId() {
        return mPriceId;
    }

    public void setPriceId(Long priceId) {
        mPriceId = priceId;
    }

    public Long getProductId() {
        return mProductId;
    }

    public void setProductId(Long productId) {
        mProductId = productId;
    }

    public String getQty() {
        return mQty;
    }

    public void setQty(String qty) {
        mQty = qty;
    }

    public Long getShopId() {
        return mShopId;
    }

    public void setShopId(Long shopId) {
        mShopId = shopId;
    }

    public UnitImportModel getUnit() {
        return mUnitImportModel;
    }

    public void setUnit(UnitImportModel unitImportModel) {
        mUnitImportModel = unitImportModel;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
