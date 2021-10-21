
package com.android.store2door.api.Callbacks.common;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class GetState {

    @SerializedName("country_id")
    private Long mCountryId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;

    public Long getCountryId() {
        return mCountryId;
    }

    public void setCountryId(Long countryId) {
        mCountryId = countryId;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
