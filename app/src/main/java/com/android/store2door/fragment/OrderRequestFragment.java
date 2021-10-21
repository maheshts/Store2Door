package com.android.store2door.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.store2door.OrderRequestDetailActivity;
import com.android.store2door.R;
import com.android.store2door.adapter.OrderRequstListAdapter;
import com.android.store2door.api.ApiClient;
import com.android.store2door.api.ApiInterface;
import com.android.store2door.api.Callbacks.orderRequest.OrderRequestModel;
import com.android.store2door.api.Constant;
import com.android.store2door.utils.BaseFragment;
import com.android.store2door.utils.CommonFunction;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class OrderRequestFragment extends BaseFragment {

    private RecyclerView rvPendingList;
    private TextView tvEmptyList;


    public OrderRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

        return view;
    }

    private void getOrderRequest() {

        if (isConnectingToInternet(mContext)) {
            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);

            Call<OrderRequestModel> call = apiService.getOrderRequest(sessionManager.getLoginModel().getToken());
            showProgressDialog();
            call.enqueue(new Callback<OrderRequestModel>() {
                @Override
                public void onResponse(Call<OrderRequestModel> call, Response<OrderRequestModel> response) {
                    hideProgressDialog();
                    OrderRequestModel responseBody = response.body();
                    if (responseBody != null) {
                        if (responseBody.getStatus() == 1 && responseBody.getData().size() != 0) {

                            tvEmptyList.setVisibility(View.GONE);
                            OrderRequstListAdapter orderRequstListAdapter = new OrderRequstListAdapter(
                                    responseBody.getData()
                                    , mContext, new OrderRequstListAdapter.OnClickListener() {
                                @Override
                                public void clickLayout(int position) {

                                    startActivityForResult(new Intent(mContext, OrderRequestDetailActivity.class)
                                                    .putExtra(Constant.ItemModel, responseBody.getData().get(position))
                                            , Constant.ActivityResult_1);
                                }
                            });
                            rvPendingList.setAdapter(orderRequstListAdapter);

                        }else {
                            tvEmptyList.setVisibility(View.VISIBLE);

                        }

                    } else {
                        tvEmptyList.setVisibility(View.VISIBLE);
//                            tvEmptyList.setText(responseBody.getMessage());
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


    @Override
    public void onResume() {
        super.onResume();
        if (rvPendingList.getAdapter() != null) {
            rvPendingList.setAdapter(null);
        }
        getOrderRequest();

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Constant.ActivityResult_1 && resultCode == RESULT_OK) {
//            rvPendingList.setAdapter(null);
//            getOrderRequest();
//        }
//    }
}
