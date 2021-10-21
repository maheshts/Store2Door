
package com.android.store2door.api.Callbacks.getProduct;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DataProduct {

    @SerializedName("current_page")
    private String mCurrentPage;
    @SerializedName("data")
    private List<DatumProduct> mData;
    @SerializedName("first_page_url")
    private String mFirstPageUrl;
    @SerializedName("from")
    private String mFrom;
    @SerializedName("last_page")
    private String mLastPage;
    @SerializedName("last_page_url")
    private String mLastPageUrl;
    @SerializedName("next_page_url")
    private String mNextPageUrl;
    @SerializedName("path")
    private String mPath;
    @SerializedName("per_page")
    private String mPerPage;
    @SerializedName("prev_page_url")
    private String mPrevPageUrl;
    @SerializedName("to")
    private String mTo;
    @SerializedName("total")
    private String mTotal;

    public String getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(String currentPage) {
        mCurrentPage = currentPage;
    }

    public List<DatumProduct> getData() {
        return mData;
    }

    public void setData(List<DatumProduct> data) {
        mData = data;
    }

    public String getFirstPageUrl() {
        return mFirstPageUrl;
    }

    public void setFirstPageUrl(String firstPageUrl) {
        mFirstPageUrl = firstPageUrl;
    }

    public String getFrom() {
        return mFrom;
    }

    public void setFrom(String from) {
        mFrom = from;
    }

    public String getLastPage() {
        return mLastPage;
    }

    public void setLastPage(String lastPage) {
        mLastPage = lastPage;
    }

    public String getLastPageUrl() {
        return mLastPageUrl;
    }

    public void setLastPageUrl(String lastPageUrl) {
        mLastPageUrl = lastPageUrl;
    }

    public String getNextPageUrl() {
        return mNextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        mNextPageUrl = nextPageUrl;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public String getPerPage() {
        return mPerPage;
    }

    public void setPerPage(String perPage) {
        mPerPage = perPage;
    }

    public String getPrevPageUrl() {
        return mPrevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        mPrevPageUrl = prevPageUrl;
    }

    public String getTo() {
        return mTo;
    }

    public void setTo(String to) {
        mTo = to;
    }

    public String getTotal() {
        return mTotal;
    }

    public void setTotal(String total) {
        mTotal = total;
    }

}
