package com.android.store2door.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.store2door.R;
import com.android.store2door.adapter.ImportPageAdapter;
import com.android.store2door.api.ApiClient;
import com.android.store2door.api.ApiInterface;
import com.android.store2door.api.Callbacks.importProduct.ImportListModel;
import com.android.store2door.api.Constant;
import com.android.store2door.utils.BaseFragment;
import com.android.store2door.utils.CommonFunction;
import com.android.store2door.utils.PaginationListener;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImportListFragment extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvPendingList;
    private TextView tvEmptyList;


    ImportPageAdapter importPageAdapter;

    public ImportListFragment() {
        // Required empty public constructor
    }

    private String nextPageUrl = "http://13.127.142.159/v2_0/api/shop/products/import";
    private int offsetValue = 0;
    private boolean isLoading = false;
    private int TOTAL_PAGES;
    private int TOTAL_LISTCOUNT;


    private boolean isLastPage = false;

    private SwipeRefreshLayout sl_swipeRefresh_student;

    @Override
    public void onRefresh() {

        offsetValue = 0;
        importPageAdapter.clear();

        nextPageUrl = "http://13.127.142.159/v2_0/api/shop/products/import";
        if (isConnectingToInternet(mContext)) {
            getProductsPage(nextPageUrl);
        } else {
            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));
        }
    }

    public void onRefreshList() {

        offsetValue = 0;
        importPageAdapter.clear();

        nextPageUrl = "http://13.127.142.159/v2_0/api/shop/products/import";
        if (isConnectingToInternet(mContext)) {
            getProductsPage(nextPageUrl);
        } else {
            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        onRefreshList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        RecyclerView rvPendingList = view.findViewById(R.id.rvPendingList);
        tvEmptyList = view.findViewById(R.id.tvEmptyList);
        view.findViewById(R.id.btImportButton).setVisibility(View.GONE);
        sl_swipeRefresh_student = view.findViewById(R.id.sl_swipeRefresh_student);

        tvEmptyList.setVisibility(View.GONE);

        sl_swipeRefresh_student.setOnRefreshListener(this);

        rvPendingList.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rvPendingList.setLayoutManager(layoutManager);
        rvPendingList.setItemAnimator(new DefaultItemAnimator());


        importPageAdapter = new ImportPageAdapter(getActivity());
        rvPendingList.setAdapter(importPageAdapter);

        /*
         * add scroll listener while user reach in bottom load more will call
         */
        rvPendingList.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                offsetValue = offsetValue + TOTAL_LISTCOUNT;

                if (!TextUtils.isEmpty(nextPageUrl))
                    getProductsPage(nextPageUrl);

            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });


//        getProductsPage(nextPageUrl);
        return view;
    }

//    private void getProductsImportList() {
//
//        if (isConnectingToInternet(mContext)) {
//            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);
//
//            System.out.println(sessionManager.getLoginModel().getToken());
//            Call<ImportListModel> call = apiService.getProductsImportList(sessionManager.getLoginModel().getToken(),nextPageUrl);
//            showProgressDialog();
//            call.enqueue(new Callback<ImportListModel>() {
//                @Override
//                public void onResponse(Call<ImportListModel> call, Response<ImportListModel> response) {
//                    hideProgressDialog();
//                    ImportListModel responseBody = response.body();
//                    if (responseBody != null) {
//                        if (responseBody.getStatus() == 1 && responseBody.getData().getData().size() != 0) {
//                            tvEmptyList.setVisibility(View.GONE);
//                            ImportListAdapter importListAdapter = new ImportListAdapter(responseBody.getData()
//                                    , mContext, position -> {
//
//                                startActivityForResult(new Intent(mContext, ImportDetailActivity.class)
//                                        .putExtra(Constant.ItemModel, responseBody.getData().getData().get(position))
//                                                .putExtra(Constant.fromHistory, true), Constant.ActivityResult_1);
//                            });
//                            rvPendingList.setAdapter(importListAdapter);
//                        } else {
//                            tvEmptyList.setVisibility(View.VISIBLE);
////                            tvEmptyList.setText(responseBody.getMessage());
////                            CommonFunction.showToastSingle(mContext, responseBody.getMessage());
//                        }
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<ImportListModel> call, Throwable t) {
//                    t.getMessage();
//                    hideProgressDialog();
//                }
//            });
//        } else {
//            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));
//
//        }
//    }


    private void getProductsPage(String urlString) {


        if (isConnectingToInternet(mContext)) {

            if (importPageAdapter.getItemCount() == 0) {
                showProgressDialog();
            }

            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);

            Call<ImportListModel> call = apiService.getProductsImportList(sessionManager.getLoginModel().getToken()
                    , urlString);
//            showProgressDialog();
            call.enqueue(new Callback<ImportListModel>() {
                @Override
                public void onResponse(Call<ImportListModel> call, Response<ImportListModel> response) {
                    hideProgressDialog();
                    sl_swipeRefresh_student.setRefreshing(false);
                    ImportListModel responseBody = response.body();
                    if (responseBody != null) {
                        if (responseBody.getStatus() == 1 && responseBody.getData().getData().size() != 0) {

                            nextPageUrl = responseBody.getData().getNextPageUrl();
                            TOTAL_PAGES = responseBody.getData().getLastPage();
                            TOTAL_LISTCOUNT = responseBody.getData().getPerPage();
                            if (offsetValue != 0) {
                                importPageAdapter.removeLoadingFooter();
                                isLoading = false;
                            }
                            importPageAdapter.addAll(responseBody.getData().getData());

                            if (offsetValue
                                    <= TOTAL_PAGES - TOTAL_LISTCOUNT)
                                importPageAdapter.addLoadingFooter();
                            else isLastPage = true;
                            tvEmptyList.setVisibility(View.GONE);

                        } else {
                            if (importPageAdapter.getItemCount() == 0) {
                                tvEmptyList.setVisibility(View.VISIBLE);
//                                tvEmptyList.setText(responseBody.getMessage());
                            }
                        }
                    }

                }

                @Override
                public void onFailure(Call<ImportListModel> call, Throwable t) {
                    t.getMessage();
                    hideProgressDialog();
                }
            });
        } else {
            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));

        }
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Constant.ActivityResult_1 && resultCode == RESULT_OK) {
//            offsetValue = 0;
//            importPageAdapter.clear();
//
//            nextPageUrl = "http://13.127.142.159/v2_0/api/shop/products/import";
//            if (isConnectingToInternet(mContext)) {
//                getProductsPage(nextPageUrl);
//            } else {
//                CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));
//            }
//        }
//    }
}
