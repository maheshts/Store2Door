
package com.android.store2door.api.Callbacks.pendingOrder;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Datum implements Serializable {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("deleted_at")
    private String mDeletedAt;
    @SerializedName("delivery_note")
    private String mDeliveryNote;
    @SerializedName("franchise_id")
    private Long mFranchiseId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("number_of_item")
    private Long mNumberOfItem;
    @SerializedName("order_number")
    private String mOrderNumber;
    @SerializedName("shop_id")
    private Long mShopId;
    @SerializedName("status")
    private int mStatus;
    @SerializedName("franchise")
    private FranchiseModel mFranchise;
    @SerializedName("stock_items")
    private List<StockItem> mStockItems;
    @SerializedName("total")
    private Long mTotal;
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

    public String getDeliveryNote() {
        return mDeliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        mDeliveryNote = deliveryNote;
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

    public Long getNumberOfItem() {
        return mNumberOfItem;
    }

    public void setNumberOfItem(Long numberOfItem) {
        mNumberOfItem = numberOfItem;
    }

    public String getOrderNumber() {
        return mOrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        mOrderNumber = orderNumber;
    }

    public Long getShopId() {
        return mShopId;
    }

    public void setShopId(Long shopId) {
        mShopId = shopId;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public List<StockItem> getStockItems() {
        return mStockItems;
    }

    public void setStockItems(List<StockItem> stockItems) {
        mStockItems = stockItems;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public FranchiseModel getFranchise() {
        return mFranchise;
    }

    public void setFranchise(FranchiseModel franchise) {
        mFranchise = franchise;
    }

}
