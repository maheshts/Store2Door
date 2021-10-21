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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.store2door.adapter.ImportPriceListAdapter;
import com.android.store2door.api.ApiClient;
import com.android.store2door.api.ApiInterface;
import com.android.store2door.api.Callbacks.common.CommonResponse;
import com.android.store2door.api.Callbacks.importProduct.DatumImportModel;
import com.android.store2door.api.Callbacks.productImportList.DatumImportList;
import com.android.store2door.api.Constant;
import com.android.store2door.utils.BaseActivity;
import com.android.store2door.utils.CommonFunction;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImportDetailActivity extends BaseActivity {

    DatumImportModel datum;

    ImageView ivProductImage;
    TextView tvProductTitle;
    TextView tvProductDescription;


    RecyclerView rvPriceListImport;

    ImportPriceListAdapter importPriceListAdapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_detail);

        datum = (DatumImportModel) getIntent().getSerializableExtra(Constant.ItemModel);

        ivProductImage = findViewById(R.id.ivProductImage);

        tvProductDescription = findViewById(R.id.tvProductDescription);
        tvProductTitle = findViewById(R.id.tvProductTitle);
        rvPriceListImport = findViewById(R.id.rvPriceListImport);

        if (datum != null) {
            tvProductTitle.setText(datum.getName());
            tvProductDescription.setText(datum.getDescription());

            Picasso.get().load(Constant.BASEURL_image + datum.getImage())
                    .placeholder(R.drawable.splash_bg)
                    .error(R.drawable.splash_bg).into(ivProductImage);

            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            rvPriceListImport.setLayoutManager(layoutManager);
            importPriceListAdapter = new ImportPriceListAdapter(datum.getShopproducts(), mContext);
            rvPriceListImport.setAdapter(importPriceListAdapter);
        }

        initToolbar();

    }


    private void initToolbar() {
        Toolbar tbToolbarLayout = findViewById(R.id.tbToolbarLayout);
        setSupportActionBar(tbToolbarLayout);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(datum.getName());
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }


    public void DeliveryDialog(boolean isUpdate, int id, String productprice, String qty) {
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


        if (isUpdate) {
            tvTitlePopup.setText(getResources().getString(R.string.update));
            tvSubmitPopup.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            tvDescriptionPopup.setText(getResources().getString(R.string.update_confirmation));
        } else {
            tvTitlePopup.setText(getResources().getString(R.string.delete));
            tvSubmitPopup.setBackgroundColor(getResources().getColor(R.color.color_F44336));
            tvDescriptionPopup.setText(getResources().getString(R.string.delete_confirmation));
        }

        tvCancelPopup.setOnClickListener(v -> dialog.dismiss());
        tvSubmitPopup.setOnClickListener(v -> {
            dialog.dismiss();
            getOrderRequestAccept(isUpdate, id, productprice, qty);
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

    private void getOrderRequestAccept(boolean isUpdate, int id, String productprice, String qty) {

        if (isConnectingToInternet(mContext)) {
            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);

            Call<CommonResponse> call;

            if (isUpdate) {
                call = apiService.getProductsImportEdit(sessionManager.getLoginModel().getToken()
                        , String.valueOf(id),
                        qty, productprice);
            } else {
                call = apiService.getProductsImportDelete(sessionManager.getLoginModel().getToken()
                        , String.valueOf(id));
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
//                            setResult(RESULT_OK, new Intent());
//                            onBackPressed();

                            if (isUpdate) {
                                for (int i = 0; i < datum.getShopproducts().size(); i++) {
                                    if (datum.getShopproducts().get(i).getId() == id) {
                                        datum.getShopproducts().get(i).setPrice(productprice);
                                        datum.getShopproducts().get(i).setQty(qty);
                                        datum.setShopproducts(datum.getShopproducts());
                                    }
                                }
                                importPriceListAdapter.updateList(datum.getShopproducts());

                            } else {
                                for (int i = 0; i < datum.getShopproducts().size(); i++) {
                                    if (datum.getShopproducts().get(i).getId() == id) {
                                        datum.getShopproducts().remove(datum.getShopproducts().get(i));
                                    }
                                }
                                importPriceListAdapter.updateList(datum.getShopproducts());
                            }
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
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}
