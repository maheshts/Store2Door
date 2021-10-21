
package com.android.store2door.api.Callbacks.importProduct;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class DataImportModel implements Serializable {

    @SerializedName("current_page")
    private int mCurrentPage;
    @SerializedName("data")
    private List<DatumImportModel> mData;
    @SerializedName("first_page_url")
    private String mFirstPageUrl;
    @SerializedName("from")
    private int mFrom;
    @SerializedName("last_page")
    private int mLastPage;
    @SerializedName("last_page_url")
    private String mLastPageUrl;
    @SerializedName("next_page_url")
    private String mNextPageUrl;
    @SerializedName("path")
    private String mPath;
    @SerializedName("per_page")
    private int mPerPage;
    @SerializedName("prev_page_url")
    private String mPrevPageUrl;
    @SerializedName("to")
    private int mTo;
    @SerializedName("total")
    private int mTotal;

    public int getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(int currentPage) {
        mCurrentPage = currentPage;
    }

    public List<DatumImportModel> getData() {
        return mData;
    }

    public void setData(List<DatumImportModel> data) {
        mData = data;
    }

    public String getFirstPageUrl() {
        return mFirstPageUrl;
    }

    public void setFirstPageUrl(String firstPageUrl) {
        mFirstPageUrl = firstPageUrl;
    }

    public int getFrom() {
        return mFrom;
    }

    public void setFrom(int from) {
        mFrom = from;
    }

    public int getLastPage() {
        return mLastPage;
    }

    public void setLastPage(int lastPage) {
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

    public int getPerPage() {
        return mPerPage;
    }

    public void setPerPage(int perPage) {
        mPerPage = perPage;
    }

    public String getPrevPageUrl() {
        return mPrevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        mPrevPageUrl = prevPageUrl;
    }

    public int getTo() {
        return mTo;
    }

    public void setTo(int to) {
        mTo = to;
    }

    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int total) {
        mTotal = total;
    }

}
