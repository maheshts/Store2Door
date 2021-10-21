package com.android.store2door.api;


import com.android.store2door.api.Callbacks.common.CommonResponse;
import com.android.store2door.api.Callbacks.getProduct.ProductListModel;
import com.android.store2door.api.Callbacks.importProduct.ImportListModel;
import com.android.store2door.api.Callbacks.loginResponse.LoginResponse;
import com.android.store2door.api.Callbacks.orderRequest.OrderRequestModel;
import com.android.store2door.api.Callbacks.pendingOrder.PendingOrderResponse;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface ApiInterface {

    @POST("login")
    @FormUrlEncoded
    Call<LoginResponse> loginRequest(@Field("login_id") String user_id, @Field("password") String password);


    @POST("orders/pending")
    Call<PendingOrderResponse> getPendingOrder(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("orders/delivered")
    Call<CommonResponse> getDelivered(@Header("Authorization") String authHeader,
                                      @Field("order_id") String order_id,
                                      @Field("delivery_note") String delivery_note);

    @POST("logout")
    Call<CommonResponse> logout(@Header("Authorization") String authHeader);


    @GET("orders")
    Call<OrderRequestModel> ordersHistory(@Header("Authorization") String authHeader);


    @FormUrlEncoded
    @POST("products")
    Call<ProductListModel> getProducts(@Header("Authorization") String authHeader
            , @Field("search") String search);

    @FormUrlEncoded
    @POST()
    Call<ProductListModel> getProductsPage(@Header("Authorization") String authHeader,
                                           @Url() String url  , @Field("search") String search);


    @FormUrlEncoded
    @POST("products/import")
    Call<CommonResponse> getProductsImport(@Header("Authorization") String authHeader,
                                           @Field("data") JSONArray jsonArray);

    @GET()
    Call<ImportListModel> getProductsImportList(@Header("Authorization") String authHeader, @Url() String url );

    @FormUrlEncoded
    @POST("products/import/delete")
    Call<CommonResponse> getProductsImportDelete(@Header("Authorization") String authHeader,
                                                 @Field("import_id") String import_id);

    @FormUrlEncoded
    @POST("products/import/edit")
    Call<CommonResponse> getProductsImportEdit(@Header("Authorization") String authHeader,
                                               @Field("import_id") String order_id,
                                               @Field("qty") String qty,
                                               @Field("price") String price);


    @GET("orders/request")
    Call<OrderRequestModel> getOrderRequest(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("orders/request/change")
    Call<CommonResponse> getOrderRequestChange(@Header("Authorization") String authHeader,
                                               @Field("order_id") String order_id,
                                               @Field("product_id") String product_id,
                                               @Field("price_id") String price_id,
                                               @Field("qty") String qty,
                                               @Field("price") String price);

    @FormUrlEncoded
    @POST("orders/request/accept")
    Call<CommonResponse> getOrderRequestAccept(@Header("Authorization") String authHeader,
                                               @Field("order_id") String order_id);

    @FormUrlEncoded
    @POST("orders/request/decline")
    Call<CommonResponse> getOrderRequestDecline(@Header("Authorization") String authHeader,
                                                @Field("order_id") String order_id);


    @FormUrlEncoded
    @POST("orders/payment")
    Call<CommonResponse> getOrderPayment(@Header("Authorization") String authHeader,
                                         @Field("order_id") String order_id);


}

