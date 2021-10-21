
package com.android.store2door.api.Callbacks.loginResponse;

import com.android.store2door.api.Callbacks.common.GetCities;
import com.android.store2door.api.Callbacks.common.GetCountry;
import com.android.store2door.api.Callbacks.common.GetState;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class DataResponse {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("city_id")
    private Long mCityId;
    @SerializedName("country_id")
    private Long mCountryId;
     @SerializedName("email")
    private String mEmail;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("get_cities")
    private GetCities mGetCities;
    @SerializedName("get_country")
    private List<GetCountry> mGetCountry;
    @SerializedName("get_state")
    private GetState mGetState;
    @SerializedName("id")
    private Long mId;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("logo")
    private Object mLogo;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("state_id")
    private Long mStateId;
    @SerializedName("status")
    private Long mStatus;
    @SerializedName("title")
    private String mTitle;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Long getCityId() {
        return mCityId;
    }

    public void setCityId(Long cityId) {
        mCityId = cityId;
    }

    public Long getCountryId() {
        return mCountryId;
    }

    public void setCountryId(Long countryId) {
        mCountryId = countryId;
    }

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

    public GetCities getGetCities() {
        return mGetCities;
    }

    public void setGetCities(GetCities getCities) {
        mGetCities = getCities;
    }

    public List<GetCountry> getGetCountry() {
        return mGetCountry;
    }

    public void setGetCountry(List<GetCountry> getCountry) {
        mGetCountry = getCountry;
    }

    public GetState getGetState() {
        return mGetState;
    }

    public void setGetState(GetState getState) {
        mGetState = getState;
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

    public Object getLogo() {
        return mLogo;
    }

    public void setLogo(Object logo) {
        mLogo = logo;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public Long getStateId() {
        return mStateId;
    }

    public void setStateId(Long stateId) {
        mStateId = stateId;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }


}
