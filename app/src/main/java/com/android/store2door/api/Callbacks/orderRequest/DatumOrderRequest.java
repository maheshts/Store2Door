
package com.android.store2door.api.Callbacks.orderRequest;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DatumOrderRequest implements Serializable {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("deleted_at")
    private String mDeletedAt;
    @SerializedName("delivery_note")
    private String mDeliveryNote;
    @SerializedName("franchise")
    private FranchiseOrderRequest mFranchiseOrderRequest;
    @SerializedName("franchise_id")
    private Long mFranchiseId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("number_of_item")
    private Long mNumberOfItem;
    @SerializedName("order_number")
    private String mOrderNumber;
    @SerializedName("payment_status")
    private String mPaymentStatus;
    @SerializedName("shop_id")
    private Long mShopId;
    @SerializedName("status")
    private int mStatus;
    @SerializedName("stock_items")
    private List<StockItemOrderRequest> mStockItemOrderRequests;
    @SerializedName("total")
    private float mTotal;
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

    public FranchiseOrderRequest getFranchise() {
        return mFranchiseOrderRequest;
    }

    public void setFranchise(FranchiseOrderRequest franchiseOrderRequest) {
        mFranchiseOrderRequest = franchiseOrderRequest;
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

    public String getPaymentStatus() {
        return mPaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        mPaymentStatus = paymentStatus;
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

    public List<StockItemOrderRequest> getStockItems() {
        return mStockItemOrderRequests;
    }

    public void setStockItems(List<StockItemOrderRequest> stockItemOrderRequests) {
        mStockItemOrderRequests = stockItemOrderRequests;
    }

    public float getTotal() {
        return mTotal;
    }

    public void setTotal(float total) {
        mTotal = total;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
