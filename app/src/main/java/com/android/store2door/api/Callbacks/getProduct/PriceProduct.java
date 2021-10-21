
package com.android.store2door.api.Callbacks.getProduct;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PriceProduct {

    @SerializedName("cart_item")
    private String mCartItem;
    @SerializedName("id")
    private String mId;
    @SerializedName("is_added")
    private String mIsAdded;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("product_id")
    private String mProductId;
    @SerializedName("quantity")
    private String mQuantity;

    public String getNewquantity() {
        return mNewquantity;
    }

    public void setNewquantity(String mNewquantity) {
        this.mNewquantity = mNewquantity;
    }

    @SerializedName("newquantity")
    private String mNewquantity="1";
    @SerializedName("unit")
    private String mUnit;

    public String getCartItem() {
        return mCartItem;
    }

    public void setCartItem(String cartItem) {
        mCartItem = cartItem;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getIsAdded() {
        return mIsAdded;
    }

    public void setIsAdded(String isAdded) {
        mIsAdded = isAdded;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
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

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }

    @SerializedName("selected")
    private boolean mSelected=false;

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean mSelected) {
        this.mSelected = mSelected;
    }

}
