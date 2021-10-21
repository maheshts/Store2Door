package com.android.store2door.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.store2door.ProductDetailActivity;
import com.android.store2door.R;
import com.android.store2door.ReportTranDetailActivity;
import com.android.store2door.adapter.PendingListAdapter;
import com.android.store2door.adapter.ReportTranListAdapter;
import com.android.store2door.api.ApiClient;
import com.android.store2door.api.ApiInterface;
import com.android.store2door.api.Callbacks.orderRequest.OrderRequestModel;
import com.android.store2door.api.Callbacks.pendingOrder.PendingOrderResponse;
import com.android.store2door.api.Constant;
import com.android.store2door.utils.BaseFragment;
import com.android.store2door.utils.CommonFunction;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportTransactionsFragment extends BaseFragment {

    private RecyclerView rvPendingList;
    private TextView tvEmptyList;


    public ReportTransactionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        rvPendingList = view.findViewById(R.id.rvPendingList);
        tvEmptyList = view.findViewById(R.id.tvEmptyList);

        rvPendingList.setHasFixedSize(true);
        // use a linear layout manager
        if (rvPendingList.getLayoutManager() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            rvPendingList.setLayoutManager(layoutManager);
            rvPendingList.setItemAnimator(new DefaultItemAnimator());
        }

        getPendingOrder();
        return view;
    }

    private void getPendingOrder() {

        if (isConnectingToInternet(mContext)) {
            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);

            Call<OrderRequestModel> call = apiService.ordersHistory(sessionManager.getLoginModel().getToken());
            showProgressDialog();
            call.enqueue(new Callback<OrderRequestModel>() {
                @Override
                public void onResponse(Call<OrderRequestModel> call, Response<OrderRequestModel> response) {
                    hideProgressDialog();
                    OrderRequestModel responseBody = response.body();
                    if (responseBody != null) {
                        if (responseBody.getStatus() == 1 && responseBody.getData().size() != 0) {
                            tvEmptyList.setVisibility(View.GONE);
                            ReportTranListAdapter pendingListAdapter = new ReportTranListAdapter(responseBody.getData()
                                    , mContext, position -> {

                                startActivityForResult(new Intent(mContext, ReportTranDetailActivity.class)
                                        .putExtra(Constant.ItemModel, responseBody.getData().get(position))
                                                .putExtra(Constant.fromHistory, true), Constant.ActivityResult_1);
                            },false);
                            rvPendingList.setAdapter(pendingListAdapter);
                        } else {
                            tvEmptyList.setVisibility(View.VISIBLE);
//                            tvEmptyList.setText(responseBody.getMessage());
//                            CommonFunction.showToastSingle(mContext, responseBody.getMessage());
                        }
                    }

                }

                @Override
                public void onFailure(Call<OrderRequestModel> call, Throwable t) {
                    t.getMessage();
                    hideProgressDialog();
                }
            });
        } else {
            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));

        }
    }
}
