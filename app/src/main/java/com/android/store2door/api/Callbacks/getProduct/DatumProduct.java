
package com.android.store2door.api.Callbacks.getProduct;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DatumProduct {

    @SerializedName("brand")
    private String mBrand;
    @SerializedName("category")
    private List<CategoryProduct> mCategoryProduct;
    @SerializedName("certification")
    private String mCertification;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("model_name")
    private String mModelName;
    @SerializedName("name")
    private String mName;
    @SerializedName("orders")
    private String mOrders;
    @SerializedName("price")
    private List<PriceProduct> mPriceProduct;
    @SerializedName("shelf_age")
    private String mShelfAge;
    @SerializedName("sku")
    private String mSku;
    @SerializedName("slug")
    private String mSlug;
    @SerializedName("special_note")
    private String mSpecialNote;

    public String getBrand() {
        return mBrand;
    }

    public void setBrand(String brand) {
        mBrand = brand;
    }

    public List<CategoryProduct> getCategory() {
        return mCategoryProduct;
    }

    public void setCategory(List<CategoryProduct> categoryProduct) {
        mCategoryProduct = categoryProduct;
    }

    public String getCertification() {
        return mCertification;
    }

    public void setCertification(String certification) {
        mCertification = certification;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getModelName() {
        return mModelName;
    }

    public void setModelName(String modelName) {
        mModelName = modelName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getOrders() {
        return mOrders;
    }

    public void setOrders(String orders) {
        mOrders = orders;
    }

    public List<PriceProduct> getPrice() {
        return mPriceProduct;
    }

    public void setPrice(List<PriceProduct> priceProduct) {
        mPriceProduct = priceProduct;
    }

    public String getShelfAge() {
        return mShelfAge;
    }

    public void setShelfAge(String shelfAge) {
        mShelfAge = shelfAge;
    }

    public String getSku() {
        return mSku;
    }

    public void setSku(String sku) {
        mSku = sku;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getSpecialNote() {
        return mSpecialNote;
    }

    public void setSpecialNote(String specialNote) {
        mSpecialNote = specialNote;
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
