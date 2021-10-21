package com.android.store2door.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.store2door.DashboardActivity;
import com.android.store2door.R;
import com.android.store2door.adapter.ProductPageAdapter;
import com.android.store2door.api.ApiClient;
import com.android.store2door.api.ApiInterface;
import com.android.store2door.api.Callbacks.common.CommonResponse;
import com.android.store2door.api.Callbacks.getProduct.DatumProduct;
import com.android.store2door.api.Callbacks.getProduct.ProductListAdapterModel;
import com.android.store2door.api.Callbacks.getProduct.ProductListModel;
import com.android.store2door.api.Constant;
import com.android.store2door.utils.BaseFragment;
import com.android.store2door.utils.CommonFunction;
import com.android.store2door.utils.PaginationListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener {

    private TextView tvEmptyList;

    private String nextPageUrl="http://13.127.142.159/v2_0/api/shop/products";
    private ProductPageAdapter productPageAdapter;
    private int offsetValue = 0;
    private boolean isLoading = false;
    private int TOTAL_PAGES;
    private int TOTAL_LISTCOUNT;


    private boolean isLastPage = false;

    private SwipeRefreshLayout sl_swipeRefresh_student;

    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        setHasOptionsMenu(true);
        RecyclerView rvPendingList = view.findViewById(R.id.rvPendingList);
        tvEmptyList = view.findViewById(R.id.tvEmptyList);
        Button btImportButton = view.findViewById(R.id.btImportButton);
        sl_swipeRefresh_student = view.findViewById(R.id.sl_swipeRefresh_student);

        tvEmptyList.setVisibility(View.GONE);

        sl_swipeRefresh_student.setOnRefreshListener(this);

        rvPendingList.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rvPendingList.setLayoutManager(layoutManager);
        rvPendingList.setItemAnimator(new DefaultItemAnimator());


        productPageAdapter = new ProductPageAdapter(getActivity());
        rvPendingList.setAdapter(productPageAdapter);

        getProductsPage(nextPageUrl,"");

        /*
         * add scroll listener while user reach in bottom load more will call
         */
        rvPendingList.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                offsetValue = offsetValue + TOTAL_LISTCOUNT;

                if (!TextUtils.isEmpty(nextPageUrl))
                    getProductsPage(nextPageUrl,"");

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

        btImportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    List<DatumProduct> datumProducts=productPageAdapter.updateList();
//                    System.out.println(datumProducts);
                    JSONArray jsonArray = new JSONArray();

                    for (int i = 0; i < datumProducts.size(); i++) {
                        if (datumProducts.get(i).isSelected()) {

                            for (int j = 0; j < datumProducts.get(i).getPrice().size(); j++) {

                                if ( datumProducts.get(i).getPrice().get(j).isSelected()){
                                    JSONObject jsonObject = new JSONObject();

                                    jsonObject.put(Constant.product_id, datumProducts.get(i).getPrice().get(j).getProductId());
                                    jsonObject.put(Constant.price_id, datumProducts.get(i).getPrice().get(j).getId());
                                    jsonObject.put(Constant.price, datumProducts.get(i).getPrice().get(j).getPrice());
                                    jsonObject.put(Constant.qty, datumProducts.get(i).getPrice().get(j).getNewquantity());
                                    jsonArray.put(jsonObject);
                                }
                            }

                        }
                    }

//                    System.out.println(jsonArray);
                    getProductsImport(jsonArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        return view;
    }

    private void getProductsImport(JSONArray jsonArray) {

        if (isConnectingToInternet(mContext)) {
            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);

            Call<CommonResponse> call = apiService.getProductsImport(sessionManager.getLoginModel().getToken(), jsonArray);
            showProgressDialog();
            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    hideProgressDialog();
                    CommonResponse responseBody = response.body();
                    if (responseBody != null) {
                        if (responseBody.getStatus() == 1) {
                            DashboardActivity.CURRENT_TAG = getResources().getString(R.string.purchase_order);

                            ((DashboardActivity) mContext).loadHomeFragment(new DashboardFragment());
                        } else {
                            CommonFunction.showToastSingle(mContext, responseBody.getMessage());
                        }
                    }

                }

                @Override
                public void onFailure(Call<CommonResponse> call, Throwable t) {
                    t.getMessage();
                    hideProgressDialog();
                }
            });
        } else {
            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));

        }
    }

//    private void getProducts(String search) {
//
//        if (isConnectingToInternet(mContext)) {
//            sl_swipeRefresh_student.setRefreshing(true);
//            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);
//
//            Call<ProductListModel> call = apiService.getProducts(sessionManager.getLoginModel().getToken(),search);
////            showProgressDialog();
//            call.enqueue(new Callback<ProductListModel>() {
//                @Override
//                public void onResponse(Call<ProductListModel> call, Response<ProductListModel> response) {
//                    hideProgressDialog();
//                    sl_swipeRefresh_student.setRefreshing(false);
//                    ProductListModel responseBody = response.body();
//                    if (responseBody != null) {
//                        if (responseBody.getStatus() == 1 && responseBody.getData().getData().size() != 0) {
//                            tvEmptyList.setVisibility(View.GONE);
//                            nextPageUrl = responseBody.getData().getNextPageUrl();
//                            TOTAL_PAGES = Integer.parseInt(responseBody.getData().getTotal());
//                            TOTAL_LISTCOUNT = Integer.parseInt(responseBody.getData().getPerPage());
//                            if (offsetValue != 0) {
//                                productPageAdapter.removeLoadingFooter();
//                                isLoading = false;
//                            }
//                            productPageAdapter.addAll(ProductFinalList(responseBody.getData().getData()));
//
//                            if (offsetValue
//                                    <= TOTAL_PAGES - TOTAL_LISTCOUNT)
//                                productPageAdapter.addLoadingFooter();
//                            else isLastPage = true;
//
//                        } else {
//                            tvEmptyList.setVisibility(View.VISIBLE);
////                            tvEmptyList.setText(responseBody.getMessage());
//                        }
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<ProductListModel> call, Throwable t) {
//                    t.getMessage();
//                    hideProgressDialog();
//                }
//            });
//        } else {
//            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));
//
//        }
//    }

    private void getProductsPage(String urlString,String search) {

        if (isConnectingToInternet(mContext)) {
            if (productPageAdapter.getItemCount() == 0){
                showProgressDialog();
            }

            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);

            Call<ProductListModel> call = apiService.getProductsPage(sessionManager.getLoginModel().getToken(), urlString,search);
//            showProgressDialog();
            call.enqueue(new Callback<ProductListModel>() {
                @Override
                public void onResponse(Call<ProductListModel> call, Response<ProductListModel> response) {
                    hideProgressDialog();
                    sl_swipeRefresh_student.setRefreshing(false);
                    ProductListModel responseBody = response.body();
                    if (responseBody != null) {
                        if (responseBody.getStatus() == 1 && responseBody.getData().getData().size() != 0) {

                            nextPageUrl = responseBody.getData().getNextPageUrl();
                            TOTAL_PAGES = Integer.parseInt(responseBody.getData().getLastPage());
                            TOTAL_LISTCOUNT = Integer.parseInt(responseBody.getData().getPerPage());
                            if (offsetValue != 0) {
                                productPageAdapter.removeLoadingFooter();
                                isLoading = false;
                            }
                            productPageAdapter.addAll(responseBody.getData().getData());

                            if (offsetValue
                                    <= TOTAL_PAGES - TOTAL_LISTCOUNT)
                                productPageAdapter.addLoadingFooter();
                            else isLastPage = true;

                        }
                    }

                }

                @Override
                public void onFailure(Call<ProductListModel> call, Throwable t) {
                    t.getMessage();
                    hideProgressDialog();
                }
            });
        } else {
            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));

        }
    }


    private List<ProductListAdapterModel> ProductFinalList(List<DatumProduct> datumProductList) {
        List<ProductListAdapterModel> listAdapterModels = new ArrayList<>();
        for (int i = 0; i < datumProductList.size(); i++) {
//            for (int j = 0; j < datumProductList.get(i).getPrice().size(); j++) {
                ProductListAdapterModel productListAdapterModel = new ProductListAdapterModel();
                productListAdapterModel.setDescription(datumProductList.get(i).getDescription());
                productListAdapterModel.setName(datumProductList.get(i).getName());
                productListAdapterModel.setImage(datumProductList.get(i).getImage());
//                productListAdapterModel.setPrice(datumProductList.get(i).getPrice().get(j).getPrice());
//                productListAdapterModel.setUnit(datumProductList.get(i).getPrice().get(j).getUnit());
//                productListAdapterModel.setQuantity(datumProductList.get(i).getPrice().get(j).getQuantity());
//                productListAdapterModel.setPriceId(datumProductList.get(i).getPrice().get(j).getId());
//                productListAdapterModel.setProductId(datumProductList.get(i).getPrice().get(j).getProductId());
//                productListAdapterModel.setIsAdded(datumProductList.get(i).getPrice().get(j).getIsAdded());
//                listAdapterModels.add(productListAdapterModel);
//            }
            listAdapterModels.add(productListAdapterModel);

        }
        return listAdapterModels;
    }

    @Override
    public void onRefresh() {

        offsetValue = 0;
        productPageAdapter.clear();

        nextPageUrl="http://13.127.142.159/v2_0/api/shop/products";
        if (isConnectingToInternet(mContext)) {
            getProductsPage(nextPageUrl,"");
        } else {
            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));
        }
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
             /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                offsetValue = 0;
                productPageAdapter.clear();
                if (isConnectingToInternet(mContext)) {
                    getProductsPage(nextPageUrl,query);
                } else {
                    CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));
                }
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
                return false;
            }
        });
}
}
