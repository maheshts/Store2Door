
package com.android.store2door.api.Callbacks.pendingOrder;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class StockItem implements Serializable {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("deleted_at")
    private String mDeletedAt;
    @SerializedName("franchise_id")
    private Long mFranchiseId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("order_id")
    private Long mOrderId;
    @SerializedName("price")
    private Price mPrice;
    @SerializedName("price_id")
    private Long mPriceId;
    @SerializedName("product")
    private Product mProduct;
    @SerializedName("product_id")
    private Long mProductId;
    @SerializedName("quantity")
    private Long mQuantity;
    @SerializedName("sub_total")
    private Long mSubTotal;
    @SerializedName("unit_price")
    private Long mUnitPrice;
    @SerializedName("unit_quantity")
    private String mUnitQuantity;
    @SerializedName("unit_type")
    private String mUnitType;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDeletedAt() {
        return mDeletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        mDeletedAt = deletedAt;
    }

    public Long getFranchiseId() {
        return mFranchiseId;
    }

    public void setFranchiseId(Long franchiseId) {
        mFranchiseId = franchiseId;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getOrderId() {
        return mOrderId;
    }

    public void setOrderId(Long orderId) {
        mOrderId = orderId;
    }

    public Price getPrice() {
        return mPrice;
    }

    public void setPrice(Price price) {
        mPrice = price;
    }

    public Long getPriceId() {
        return mPriceId;
    }

    public void setPriceId(Long priceId) {
        mPriceId = priceId;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }

    public Long getProductId() {
        return mProductId;
    }

    public void setProductId(Long productId) {
        mProductId = productId;
    }

    public Long getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Long quantity) {
        mQuantity = quantity;
    }

    public Long getSubTotal() {
        return mSubTotal;
    }

    public void setSubTotal(Long subTotal) {
        mSubTotal = subTotal;
    }

    public Long getUnitPrice() {
        return mUnitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        mUnitPrice = unitPrice;
    }

    public String getUnitQuantity() {
        return mUnitQuantity;
    }

    public void setUnitQuantity(String unitQuantity) {
        mUnitQuantity = unitQuantity;
    }

    public String getUnitType() {
        return mUnitType;
    }

    public void setUnitType(String unitType) {
        mUnitType = unitType;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
