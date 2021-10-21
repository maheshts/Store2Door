package com.android.store2door;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.store2door.adapter.OrderRequestStockListAdapter;
import com.android.store2door.api.ApiClient;
import com.android.store2door.api.ApiInterface;
import com.android.store2door.api.Callbacks.common.CommonResponse;
import com.android.store2door.api.Callbacks.orderRequest.DatumOrderRequest;
import com.android.store2door.api.Callbacks.orderRequest.OrderRequestAdapterModel;
import com.android.store2door.api.Callbacks.orderRequest.PriceOrderRequest;
import com.android.store2door.api.Callbacks.orderRequest.StockItemOrderRequest;
import com.android.store2door.api.Constant;
import com.android.store2door.utils.BaseActivity;
import com.android.store2door.utils.CommonFunction;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRequestDetailActivity extends BaseActivity implements View.OnClickListener {

    DatumOrderRequest datum;

    TextView tvFullNameDetail;
    TextView tvMobileDetail;
    TextView tvEmailDetail;
    TextView tvTotalItemCountDetail;
    TextView tvSubTotalDetail;
    TextView tvTotalAmountDetail;
    TextView tvDeliveryNoteDetail;
    LinearLayout llDeliveryNoteLayout;


    RecyclerView rvProductListDetail;

    boolean fromHistory;

    TextView tvDeclineOrder;
    TextView tvAcceptOrder;

    int changeValuePosition;

    OrderRequestStockListAdapter orderRequestStockListAdapter;

    boolean isChangeValue = false;
    List<StockItemOrderRequest> orderRequestAdapterModels;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_request_detail);

        datum = (DatumOrderRequest) getIntent().getSerializableExtra(Constant.ItemModel);
        fromHistory = getIntent().getBooleanExtra(Constant.fromHistory, false);

        tvDeclineOrder = findViewById(R.id.tvDeclineOrder);
        tvAcceptOrder = findViewById(R.id.tvAcceptOrder);
        llDeliveryNoteLayout = findViewById(R.id.llDeliveryNoteLayout);
        tvDeliveryNoteDetail = findViewById(R.id.tvDeliveryNoteDetail);
        tvFullNameDetail = findViewById(R.id.tvFullNameDetail);
        tvMobileDetail = findViewById(R.id.tvMobileDetail);
        tvEmailDetail = findViewById(R.id.tvEmailDetail);
        tvTotalItemCountDetail = findViewById(R.id.tvTotalItemCountDetail);
        tvSubTotalDetail = findViewById(R.id.tvSubTotalDetail);
        tvTotalAmountDetail = findViewById(R.id.tvTotalAmountDetail);
        rvProductListDetail = findViewById(R.id.rvProductListDetail);

        rvProductListDetail.setHasFixedSize(true);
        rvProductListDetail.setNestedScrollingEnabled(false);
        // use a linear layout manager
        if (rvProductListDetail.getLayoutManager() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            rvProductListDetail.setLayoutManager(layoutManager);
            rvProductListDetail.setItemAnimator(new DefaultItemAnimator());
        }

        if (datum != null) {
            tvFullNameDetail.setText(datum.getFranchise().getFirstName() + " " + datum.getFranchise().getLastName());
            tvEmailDetail.setText(getResources().getString(R.string.email) + " " + datum.getFranchise().getEmail());
            tvMobileDetail.setText(getResources().getString(R.string.ph) + " " + datum.getFranchise().getPhone());

            orderRequestAdapterModels = datum.getStockItems();
            orderRequestStockListAdapter = new OrderRequestStockListAdapter(
                    orderRequestAdapterModels
                    , mContext, new OrderRequestStockListAdapter.OnClickListener() {
                @Override
                public void clickLayout(int position) {
//                    startActivityForResult(new Intent(mContext, OrderRequestDetailActivity.class)
//                                    .putExtra(Constant.ItemModel, responseBody.getData().get(position))
//                            , Constant.ActivityResult_1);
                }

                @Override
                public void clickChangeButton(int position, String price, String quntity) {

                }
            });
            rvProductListDetail.setAdapter(orderRequestStockListAdapter);

            tvTotalItemCountDetail.setText(datum.getNumberOfItem() + " " + getResources().getString(R.string.items));
            tvSubTotalDetail.setText(mContext.getResources().getString(R.string.rs) + datum.getTotal());
            tvTotalAmountDetail.setText(mContext.getResources().getString(R.string.rs) + datum.getTotal());
            if (!TextUtils.isEmpty(datum.getDeliveryNote())) {
                tvDeliveryNoteDetail.setText(datum.getDeliveryNote());
                llDeliveryNoteLayout.setVisibility(View.VISIBLE);
            } else {
                llDeliveryNoteLayout.setVisibility(View.GONE);

            }

        }


        tvDeclineOrder.setOnClickListener(this);
        tvAcceptOrder.setOnClickListener(this);
        initToolbar();

    }


    private void initToolbar() {
        Toolbar tbToolbarLayout = findViewById(R.id.tbToolbarLayout);
        setSupportActionBar(tbToolbarLayout);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(R.string.orders_Request_details));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvDeclineOrder:
                DeliveryDialog(false);
                break;
            case R.id.tvAcceptOrder:
                DeliveryDialog(true);
                break;
        }
    }

    public void DeliveryDialog(boolean isAccept) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.confim_popup_layout);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        TextView tvTitlePopup = dialog.findViewById(R.id.tvTitlePopup);
        TextView tvDescriptionPopup = dialog.findViewById(R.id.tvDescriptionPopup);
        TextView tvCancelPopup = dialog.findViewById(R.id.tvCancelPopup);
        TextView tvSubmitPopup = dialog.findViewById(R.id.tvSubmitPopup);


        if (isAccept) {
            tvTitlePopup.setText(getResources().getString(R.string.accept));
            tvSubmitPopup.setBackgroundColor(getResources().getColor(R.color.color_4CAF50));
            tvDescriptionPopup.setText(getResources().getString(R.string.accept_confirmation));
        } else {
            tvTitlePopup.setText(getResources().getString(R.string.decline));
            tvSubmitPopup.setBackgroundColor(getResources().getColor(R.color.color_F44336));
            tvDescriptionPopup.setText(getResources().getString(R.string.decline_confirmation));
        }

        tvCancelPopup.setOnClickListener(v -> dialog.dismiss());
        tvSubmitPopup.setOnClickListener(v -> {
            dialog.dismiss();
            getOrderRequestAccept(isAccept);
        });

        dialog.show();

    }

    public void ChangeDialog(String orderId, String productId, String priceId, String productprice, String qty) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.confim_popup_layout);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        TextView tvTitlePopup = dialog.findViewById(R.id.tvTitlePopup);
        TextView tvDescriptionPopup = dialog.findViewById(R.id.tvDescriptionPopup);
        TextView tvCancelPopup = dialog.findViewById(R.id.tvCancelPopup);
        TextView tvSubmitPopup = dialog.findViewById(R.id.tvSubmitPopup);

        tvTitlePopup.setText(getResources().getString(R.string.change_order));
        tvSubmitPopup.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tvDescriptionPopup.setText(getResources().getString(R.string.change_order_description));


        tvCancelPopup.setOnClickListener(v -> dialog.dismiss());
        tvSubmitPopup.setOnClickListener(v -> {
            dialog.dismiss();
            getOrderRequestChange(orderId, productId, priceId, productprice, qty);
        });

        dialog.show();

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getOrderRequestAccept(boolean isAccept) {

        if (isConnectingToInternet(mContext)) {
            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);

            Call<CommonResponse> call;

            if (isAccept) {
                call = apiService.getOrderRequestAccept(sessionManager.getLoginModel().getToken()
                        , String.valueOf(datum.getId()));
            } else {
                call = apiService.getOrderRequestDecline(sessionManager.getLoginModel().getToken()
                        , String.valueOf(datum.getId()));
            }
            showProgressDialog();
            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    hideProgressDialog();
                    System.out.println(response.body());
                    CommonResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        if (loginResponse.getStatus() == 1) {
                            isChangeValue = true;
                            onBackPressed();
                        }
                        CommonFunction.showToastSingle(mContext, loginResponse.getMessage());
                    }

                }

                @Override
                public void onFailure(Call<CommonResponse> call, Throwable t) {
                    hideProgressDialog();
                }
            });
        } else {
            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));

        }
    }

    private void getOrderRequestChange(String orderId, String productId, String priceId, String productprice, String qty) {

        if (isConnectingToInternet(mContext)) {
            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);

            Call<CommonResponse> call = apiService.getOrderRequestChange(sessionManager.getLoginModel().getToken()
                    , orderId, productId, priceId, qty
                    , productprice);

            showProgressDialog();
            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    hideProgressDialog();
                    System.out.println(response.body());
                    CommonResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        if (loginResponse.getStatus() == 1) {
                            isChangeValue = true;

                            float updateTotalPrice = 0;
                            for (int i = 0; i < datum.getStockItems().size(); i++) {
                                if (orderId.equalsIgnoreCase(datum.getStockItems().get(i).getOrderId())
                                        && priceId.equalsIgnoreCase(datum.getStockItems().get(i).getPriceId())) {

                                    datum.getStockItems().get(i).setShopUnitPrice(Integer.parseInt(productprice));
                                    datum.getStockItems().get(i).setShopQuantity(Integer.parseInt(qty));
                                    datum.setStockItems(datum.getStockItems());


                                }

                                updateTotalPrice = updateTotalPrice + (datum.getStockItems().get(i).getShopQuantity()
                                        * datum.getStockItems().get(i).getShopUnitPrice());
                            }

                            datum.setTotal(updateTotalPrice);
                            tvTotalAmountDetail.setText(mContext.getResources().getString(R.string.rs) + datum.getTotal());
                            orderRequestStockListAdapter.updateList(datum.getStockItems());
//                             orderRequestStockListAdapter.notifyDataSetChanged();
                        } else {

                            CommonFunction.showToastSingle(mContext, loginResponse.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<CommonResponse> call, Throwable t) {
                    hideProgressDialog();
                }
            });
        } else {
            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));

        }
    }

    @Override
    public void onBackPressed() {
        if (isChangeValue) {
            setResult(RESULT_OK, new Intent());
            super.onBackPressed();
        } else {
            super.onBackPressed();

        }

    }
}
