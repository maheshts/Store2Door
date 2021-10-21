
package com.android.store2door.api.Callbacks.orderRequest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class ProductOrderRequest implements Serializable {

    @SerializedName("brand")
    private String mBrand;
    @SerializedName("certification")
    private String mCertification;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("deleted_at")
    private String mDeletedAt;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("franchise_id")
    private Long mFranchiseId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("model_name")
    private String mModelName;
    @SerializedName("name")
    private String mName;
    @SerializedName("orders")
    private Long mOrders;
    @SerializedName("parent_id")
    private Long mParentId;
    @SerializedName("published")
    private String mPublished;
    @SerializedName("shelf_age")
    private String mShelfAge;
    @SerializedName("sku")
    private String mSku;
    @SerializedName("slug")
    private String mSlug;
    @SerializedName("special_note")
    private String mSpecialNote;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getBrand() {
        return mBrand;
    }

    public void setBrand(String brand) {
        mBrand = brand;
    }

    public String getCertification() {
        return mCertification;
    }

    public void setCertification(String certification) {
        mCertification = certification;
    }

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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
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

    public Long getOrders() {
        return mOrders;
    }

    public void setOrders(Long orders) {
        mOrders = orders;
    }

    public Long getParentId() {
        return mParentId;
    }

    public void setParentId(Long parentId) {
        mParentId = parentId;
    }

    public String getPublished() {
        return mPublished;
    }

    public void setPublished(String published) {
        mPublished = published;
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

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
