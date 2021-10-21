package com.android.store2door;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.store2door.api.ApiClient;
import com.android.store2door.api.ApiInterface;
import com.android.store2door.api.Callbacks.common.CommonResponse;
import com.android.store2door.fragment.DashboardFragment;
import com.android.store2door.fragment.ImportListFragment;
import com.android.store2door.fragment.OrderRequestFragment;
import com.android.store2door.fragment.ProductListFragment;
import com.android.store2door.fragment.ReportTransactionsFragment;
import com.android.store2door.utils.BaseActivity;
import com.android.store2door.utils.CommonFunction;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends BaseActivity implements  DashboardClickInterface{


    public static String CURRENT_TAG;
    boolean doubleBackToExitPressedOnce = false;
    private Handler mHandler;

    Toolbar tbToolbarLayout;
    DrawerLayout dlDrawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView nvNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mHandler = new Handler();
        CURRENT_TAG = getResources().getString(R.string.purchase_order);

        initToolbar();

        loadHomeFragment(new DashboardFragment());

    }

    private void initToolbar() {

        tbToolbarLayout = findViewById(R.id.tbToolbarLayout);
        setSupportActionBar(tbToolbarLayout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        initDrawerMenu();
    }

    private void initDrawerMenu() {
        nvNavigationView = findViewById(R.id.nvNavigationView);

        View headerLayout = nvNavigationView.getHeaderView(0); // 0-index header

        TextView tvNameTop = headerLayout.findViewById(R.id.tvNameTop);
        TextView tvNumberTop = headerLayout.findViewById(R.id.tvNumberTop);
        TextView tvEmailAddressTop = headerLayout.findViewById(R.id.tvEmailAddressTop);
        TextView tvAddressTop = headerLayout.findViewById(R.id.tvAddressTop);

        tvNameTop.setText(sessionManager.getLoginModel().getData().getTitle());
        tvNumberTop.setText(sessionManager.getLoginModel().getData().getPhone());
        tvEmailAddressTop.setText(sessionManager.getLoginModel().getData().getEmail());
        tvAddressTop.setText(sessionManager.getLoginModel().getData().getAddress() + " "
                + sessionManager.getLoginModel().getData().getGetCities().getName() + ", \n "
                + sessionManager.getLoginModel().getData().getGetState().getName() + ", "
                + sessionManager.getLoginModel().getData().getGetCountry().get(0).getName());

        dlDrawerLayout = findViewById(R.id.dlDrawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dlDrawerLayout, tbToolbarLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        dlDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        nvNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
                int item_id = item.getItemId();
                if (item_id == R.id.navOptionDashboard) {
                    CURRENT_TAG = getResources().getString(R.string.purchase_order);
                    loadHomeFragment(new DashboardFragment());
                } else if (item_id == R.id.navOptionProductList) {
                    CURRENT_TAG = getResources().getString(R.string.product_list);
                    loadHomeFragment(new ProductListFragment());
                } else if (item_id == R.id.navOptionImportProductList) {
                    CURRENT_TAG = getResources().getString(R.string.import_product_list);
                    loadHomeFragment(new ImportListFragment());
                } else if (item_id == R.id.navOptionOrderRequestList) {
                    CURRENT_TAG = getResources().getString(R.string.order_request_list);
                    loadHomeFragment(new OrderRequestFragment());
                } else if (item_id == R.id.navOptionTransaction) {
                    CURRENT_TAG = getResources().getString(R.string.reports_transactions);
                    loadHomeFragment(new ReportTransactionsFragment());
                } else if (item_id == R.id.navOptionLogout) {
                    new AlertDialog.Builder(mContext, R.style.AlertDialogTheme)
                            .setIcon(R.drawable.ic_iconfinder_logout)
                            .setTitle(getResources().getString(R.string.logout))
                            .setMessage(getResources().getString(R.string.logout_message))
                            .setPositiveButton(getResources().getString(R.string.yes_text), (dialog, which) ->
                                    getlogout())
                            .setNegativeButton(getResources().getString(R.string.no_text), null)
                            .show();

                }
                dlDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(CURRENT_TAG);
        }
    }

    @Override
    public void onBackPressed() {
        if (dlDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            dlDrawerLayout.closeDrawers();
            return;
        }

        if (!CURRENT_TAG.equalsIgnoreCase(getResources().getString(R.string.purchase_order))) {
            CURRENT_TAG = getResources().getString(R.string.purchase_order);
            loadHomeFragment(new DashboardFragment());
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                moveTaskToBack(true);
                return;
            }
            doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press again to close " +
                    getResources().getString(R.string.app_name), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        }
    }

    private void getlogout() {

        if (isConnectingToInternet(mContext)) {
            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);

            Call<CommonResponse> call = apiService.logout(sessionManager.getLoginModel().getToken());
            showProgressDialog();
            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    hideProgressDialog();
                    System.out.println(response.body());
                    CommonResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        if (loginResponse.getStatus() == 1) {
                            sessionManager.logoutUser();
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

    public void loadHomeFragment(Fragment fragment) {

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            dlDrawerLayout.closeDrawers();
            return;
        }
        Runnable mPendingRunnable = () -> {

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(CURRENT_TAG);
            }
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContentFrame, fragment, CURRENT_TAG);
            fragmentTransaction.commitAllowingStateLoss();
        };

        mHandler.post(mPendingRunnable);
        dlDrawerLayout.closeDrawers();
        invalidateOptionsMenu();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flContentFrame);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void DashboardClick(String position) {
        if (position.equalsIgnoreCase(getResources().getString(R.string.product_list))) {
            CURRENT_TAG = getResources().getString(R.string.product_list);
            loadHomeFragment(new ProductListFragment());

        }

    }
}
