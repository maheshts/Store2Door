
package com.android.store2door.api.Callbacks.common;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class GetCities {

    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("state_id")
    private Long mStateId;

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

    public Long getStateId() {
        return mStateId;
    }

    public void setStateId(Long stateId) {
        mStateId = stateId;
    }

}
