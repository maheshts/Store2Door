
package com.android.store2door.api.Callbacks.pendingOrder;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class FranchiseModel implements Serializable {

    @SerializedName("email")
    private String mEmail;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("gstin_code")
    private String mGstinCode;
    @SerializedName("id")
    private Long mId;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("orders")
    private Long mOrders;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("title")
    private String mTitle;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getGstinCode() {
        return mGstinCode;
    }

    public void setGstinCode(String gstinCode) {
        mGstinCode = gstinCode;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public Long getOrders() {
        return mOrders;
    }

    public void setOrders(Long orders) {
        mOrders = orders;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
