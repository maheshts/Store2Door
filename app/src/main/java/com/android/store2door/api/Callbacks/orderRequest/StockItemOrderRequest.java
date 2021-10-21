
package com.android.store2door.api.Callbacks.orderRequest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class StockItemOrderRequest implements Serializable {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("deleted_at")
    private String mDeletedAt;
    @SerializedName("franchise_id")
    private String mFranchiseId;
    @SerializedName("id")
    private String mId;
    @SerializedName("order_id")
    private String mOrderId;
    @SerializedName("price")
    private PriceOrderRequest mPriceOrderRequest;
    @SerializedName("price_id")
    private String mPriceId;
    @SerializedName("product")
    private ProductOrderRequest mProductOrderRequest;
    @SerializedName("product_id")
    private String mProductId;
    @SerializedName("quantity")
    private String mQuantity;
    @SerializedName("shop_price_id")
    private String mShopPriceId;
    @SerializedName("shop_quantity")
    private int mShopQuantity;
    @SerializedName("shop_unit_price")
    private int mShopUnitPrice;
    @SerializedName("sub_total")
    private String mSubTotal;
    @SerializedName("unit_price")
    private String mUnitPrice;
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

    public String getFranchiseId() {
        return mFranchiseId;
    }

    public void setFranchiseId(String franchiseId) {
        mFranchiseId = franchiseId;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getOrderId() {
        return mOrderId;
    }

    public void setOrderId(String orderId) {
        mOrderId = orderId;
    }

    public PriceOrderRequest getPrice() {
        return mPriceOrderRequest;
    }

    public void setPrice(PriceOrderRequest priceOrderRequest) {
        mPriceOrderRequest = priceOrderRequest;
    }

    public String getPriceId() {
        return mPriceId;
    }

    public void setPriceId(String priceId) {
        mPriceId = priceId;
    }

    public ProductOrderRequest getProduct() {
        return mProductOrderRequest;
    }

    public void setProduct(ProductOrderRequest productOrderRequest) {
        mProductOrderRequest = productOrderRequest;
    }

    public String getProductId() {
        return mProductId;
    }

    public void setProductId(String productId) {
        mProductId = productId;
    }

    public String getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String quantity) {
        mQuantity = quantity;
    }

    public String getShopPriceId() {
        return mShopPriceId;
    }

    public void setShopPriceId(String shopPriceId) {
        mShopPriceId = shopPriceId;
    }

    public int getShopQuantity() {
        return mShopQuantity;
    }

    public void setShopQuantity(int shopQuantity) {
        mShopQuantity = shopQuantity;
    }

    public int getShopUnitPrice() {
        return mShopUnitPrice;
    }

    public void setShopUnitPrice(int shopUnitPrice) {
        mShopUnitPrice = shopUnitPrice;
    }

    public String getSubTotal() {
        return mSubTotal;
    }

    public void setSubTotal(String subTotal) {
        mSubTotal = subTotal;
    }

    public String getUnitPrice() {
        return mUnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
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
