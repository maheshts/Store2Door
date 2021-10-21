package com.android.store2door.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.android.store2door.DashboardActivity;
import com.android.store2door.DashboardClickInterface;
import com.android.store2door.ProductDetailActivity;
import com.android.store2door.R;
import com.android.store2door.adapter.PendingListAdapter;
import com.android.store2door.api.ApiClient;
import com.android.store2door.api.ApiInterface;
import com.android.store2door.api.Callbacks.pendingOrder.PendingOrderResponse;
import com.android.store2door.api.Constant;
import com.android.store2door.utils.BaseFragment;
import com.android.store2door.utils.CommonFunction;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment {

    private RecyclerView rvPendingList;
    private TextView tvEmptyList;

    private DashboardClickInterface dashboardInterface;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        if (activity instanceof DashboardClickInterface) {
            dashboardInterface = (DashboardClickInterface) activity;
        }
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

            Call<PendingOrderResponse> call = apiService.getPendingOrder(sessionManager.getLoginModel().getToken());
            showProgressDialog();
            call.enqueue(new Callback<PendingOrderResponse>() {
                @Override
                public void onResponse(Call<PendingOrderResponse> call, Response<PendingOrderResponse> response) {
                    hideProgressDialog();
                    PendingOrderResponse responseBody = response.body();
                    if (responseBody != null) {
                        if (responseBody.getStatus() == 1 && responseBody.getData().size() != 0) {

                            tvEmptyList.setVisibility(View.GONE);
                            PendingListAdapter pendingListAdapter = new PendingListAdapter(responseBody.getData()
                                    , mContext, position -> {

                                startActivityForResult(new Intent(mContext, ProductDetailActivity.class).putExtra(Constant.ItemModel
                                        , responseBody.getData().get(position))
                                        .putExtra(Constant.fromHistory, false), Constant.ActivityResult_1);
                            },true);
                            rvPendingList.setAdapter(pendingListAdapter);
                        } else {

                            tvEmptyList.setVisibility(View.VISIBLE);
                            rvPendingList.setVisibility(View.GONE);
//                            tvEmptyList.setText(responseBody.getMessage());
//                            CommonFunction.showToastSingle(mContext, responseBody.getMessage());
                        }

                        if (dashboardInterface != null && responseBody.getImportedProductCount() == 0){
                            dashboardInterface.DashboardClick(getResources().getString(R.string.product_list));
                        }
                    }






                }

                @Override
                public void onFailure(Call<PendingOrderResponse> call, Throwable t) {
                    t.getMessage();
                    hideProgressDialog();
                }
            });
        } else {
            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.ActivityResult_1 && resultCode == RESULT_OK) {
            getPendingOrder();

        }
    }
}
