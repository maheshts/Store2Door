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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.store2door.adapter.ProductListAdapter;
import com.android.store2door.api.ApiClient;
import com.android.store2door.api.ApiInterface;
import com.android.store2door.api.Callbacks.common.CommonResponse;
import com.android.store2door.api.Callbacks.pendingOrder.Datum;
import com.android.store2door.api.Constant;
import com.android.store2door.utils.BaseActivity;
import com.android.store2door.utils.CommonFunction;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {

    Datum datum;

    TextView tvFullNameDetail;
    TextView tvMobileDetail;
    TextView tvEmailDetail;
    TextView tvTotalItemCountDetail;
    TextView tvSubTotalDetail;
    TextView tvTotalAmountDetail;
    TextView tvDeliveryNoteDetail;
    LinearLayout llDeliveryNoteLayout;

    Button btDeliveryButton;

    RecyclerView rvProductListDetail;

    boolean fromHistory;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        datum = (Datum) getIntent().getSerializableExtra(Constant.ItemModel);
        fromHistory = getIntent().getBooleanExtra(Constant.fromHistory, false);

        llDeliveryNoteLayout = findViewById(R.id.llDeliveryNoteLayout);
        tvDeliveryNoteDetail = findViewById(R.id.tvDeliveryNoteDetail);
        tvFullNameDetail = findViewById(R.id.tvFullNameDetail);
        tvMobileDetail = findViewById(R.id.tvMobileDetail);
        tvEmailDetail = findViewById(R.id.tvEmailDetail);
        tvTotalItemCountDetail = findViewById(R.id.tvTotalItemCountDetail);
        tvSubTotalDetail = findViewById(R.id.tvSubTotalDetail);
        tvTotalAmountDetail = findViewById(R.id.tvTotalAmountDetail);
        btDeliveryButton = findViewById(R.id.btDeliveryButton);
        rvProductListDetail = findViewById(R.id.rvProductListDetail);

        rvProductListDetail.setHasFixedSize(true);
        rvProductListDetail.setNestedScrollingEnabled(false);
        // use a linear layout manager
        if (rvProductListDetail.getLayoutManager() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            rvProductListDetail.setLayoutManager(layoutManager);
            rvProductListDetail.setItemAnimator(new DefaultItemAnimator());
        }

        if (fromHistory) {
            btDeliveryButton.setVisibility(View.GONE);
            llDeliveryNoteLayout.setVisibility(View.VISIBLE);
        } else {
            btDeliveryButton.setVisibility(View.VISIBLE);
            llDeliveryNoteLayout.setVisibility(View.GONE);
        }
        if (datum != null) {
            tvFullNameDetail.setText(datum.getFranchise().getFirstName() + " " + datum.getFranchise().getLastName());
            tvEmailDetail.setText(getResources().getString(R.string.email) + " " + datum.getFranchise().getEmail());
            tvMobileDetail.setText(getResources().getString(R.string.ph) + " " + datum.getFranchise().getPhone());

            if (datum.getStockItems() != null && datum.getStockItems().size() != 0)
                rvProductListDetail.setAdapter(new ProductListAdapter(datum.getStockItems(), mContext));

            tvTotalItemCountDetail.setText(datum.getNumberOfItem() + " " + getResources().getString(R.string.items));
            tvSubTotalDetail.setText(mContext.getResources().getString(R.string.rs) + datum.getTotal());
            tvTotalAmountDetail.setText(mContext.getResources().getString(R.string.rs) + datum.getTotal());
            if (!TextUtils.isEmpty(datum.getDeliveryNote())) {
                llDeliveryNoteLayout.setVisibility(View.VISIBLE);
                tvDeliveryNoteDetail.setText(datum.getDeliveryNote());
            }else {
                llDeliveryNoteLayout.setVisibility(View.GONE);

            }
        }else {
            llDeliveryNoteLayout.setVisibility(View.GONE);

        }


        btDeliveryButton.setOnClickListener(this);
        initToolbar();

    }

    private void initToolbar() {
        Toolbar tbToolbarLayout = findViewById(R.id.tbToolbarLayout);
        setSupportActionBar(tbToolbarLayout);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(R.string.purchase_Orders_details));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btDeliveryButton:
                DeliveryDialog();
                break;
        }
    }

    public void DeliveryDialog() {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.delivery_popup_layout);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        EditText etNoteDelivery = dialog.findViewById(R.id.etNoteDelivery);
        TextView tvCancelButton = dialog.findViewById(R.id.tvCancelButton);
        TextView tvSubmitButton = dialog.findViewById(R.id.tvSubmitButton);

        tvCancelButton.setOnClickListener(v -> dialog.dismiss());
        tvSubmitButton.setOnClickListener(v -> {

            String noteString = etNoteDelivery.getText().toString();
            if (TextUtils.isEmpty(noteString)) {

                CommonFunction.showToastSingle(mContext, getResources().getString(R.string.please_enter_your_note));
            } else {
                getDelivered(noteString);
            }
        });

        dialog.show();

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDelivered(String noteString) {

        if (isConnectingToInternet(mContext)) {
            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);

            Call<CommonResponse> call = apiService.getDelivered(sessionManager.getLoginModel().getToken()
                    , String.valueOf(datum.getId()), noteString);
            showProgressDialog();
            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    hideProgressDialog();
                    System.out.println(response.body());
                    CommonResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        if (loginResponse.getStatus() == 1) {

                            setResult(RESULT_OK, new Intent());

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


}
