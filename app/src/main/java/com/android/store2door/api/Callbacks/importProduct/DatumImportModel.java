
package com.android.store2door.api.Callbacks.importProduct;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class DatumImportModel implements Serializable {

    @SerializedName("brand")
    private String mBrand;
    @SerializedName("category")
    private List<CategoryImportModel> mCategoryImportModel;
    @SerializedName("certification")
    private String mCertification;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("id")
    private Long mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("model_name")
    private Object mModelName;
    @SerializedName("name")
    private String mName;
    @SerializedName("orders")
    private Long mOrders;
    @SerializedName("shelf_age")
    private String mShelfAge;
    @SerializedName("shopproducts")
    private List<ShopproductImportModel> mShopproducts;
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

    public List<CategoryImportModel> getCategory() {
        return mCategoryImportModel;
    }

    public void setCategory(List<CategoryImportModel> categoryImportModel) {
        mCategoryImportModel = categoryImportModel;
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

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public Object getModelName() {
        return mModelName;
    }

    public void setModelName(Object modelName) {
        mModelName = modelName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getOrders() {
        return mOrders;
    }

    public void setOrders(Long orders) {
        mOrders = orders;
    }

    public String getShelfAge() {
        return mShelfAge;
    }

    public void setShelfAge(String shelfAge) {
        mShelfAge = shelfAge;
    }

    public List<ShopproductImportModel> getShopproducts() {
        return mShopproducts;
    }

    public void setShopproducts(List<ShopproductImportModel> shopproducts) {
        mShopproducts = shopproducts;
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

}
