package com.android.store2door;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.store2door.api.ApiClient;
import com.android.store2door.api.ApiInterface;
import com.android.store2door.api.Callbacks.loginResponse.LoginResponse;
import com.android.store2door.utils.BaseActivity;
import com.android.store2door.utils.CommonFunction;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    TextInputLayout tlEmailLogin;
    TextInputLayout tlPasswordLogin;

    EditText etEmailLogin;
    EditText etPasswordLogin;

    ImageView ivShowPassLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tlEmailLogin = findViewById(R.id.tlEmailLogin);
        tlPasswordLogin = findViewById(R.id.tlPasswordLogin);
        etEmailLogin = findViewById(R.id.etEmailLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        ivShowPassLogin = findViewById(R.id.ivShowPassLogin);
        findViewById(R.id.tvLoginButton).setOnClickListener(this);
        ivShowPassLogin.setOnClickListener(this);

        ivShowPassLogin.setImageResource(R.drawable.visibility_button);
        etPasswordLogin.addTextChangedListener(new CommonTextWatcher(etPasswordLogin));
        etEmailLogin.addTextChangedListener(new CommonTextWatcher(etEmailLogin));


    }

    private class CommonTextWatcher implements TextWatcher {

        private View view;

        private CommonTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            switch (view.getId()) {
                case R.id.etEmailLogin:
                    tlEmailLogin.setError(null);
                    break;
                case R.id.etPasswordLogin:
                    tlPasswordLogin.setError(null);
                    break;
            }
        }

        public void afterTextChanged(Editable editable) {
        }

    }

    @Override
    public void onClick(View view) {
        CommonFunction.hideKeyboardFrom(mContext, view);
        switch (view.getId()) {
            case R.id.tvLoginButton:
                boolean isValid = true;
                String emailString = etEmailLogin.getText().toString();
                String passwordString = etPasswordLogin.getText().toString();

                if (TextUtils.isEmpty(emailString)) {
                    isValid = false;
                    tlEmailLogin.setError(getResources().getString(R.string.valid_email_phone));
                }

                if (TextUtils.isEmpty(passwordString)) {
                    isValid = false;
                    tlPasswordLogin.setError(getResources().getString(R.string.valid_password));
                }


                if (CommonFunction.emailValidator(emailString) && emailString.length() != 10) {
                    isValid = false;
                    tlEmailLogin.setError(getResources().getString(R.string.valid_email_phone_valid));
                }

                if (isValid) {
                    sendLoginRequest(emailString, passwordString);
                }
                break;
            case R.id.ivShowPassLogin:
                if (etPasswordLogin.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
                    // show password
                    etPasswordLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivShowPassLogin.setImageResource(R.drawable.visibility_button);
                    etPasswordLogin.setSelection(etPasswordLogin.length());

                } else {
                    // hide password
                    etPasswordLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivShowPassLogin.setImageResource(R.drawable.ic_hide);
                    etPasswordLogin.setSelection(etPasswordLogin.length());
                }
                break;
        }
    }

    private void sendLoginRequest(String email, String password) {

        if (isConnectingToInternet(mContext)) {
            ApiInterface apiService = ApiClient.getClient(mContext).create(ApiInterface.class);

            Call<LoginResponse> call = apiService.loginRequest(email, password);
            showProgressDialog();
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    hideProgressDialog();
                    System.out.println(response.body());
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null) {


                        if (loginResponse.getStatus() == 1) {
                            sessionManager.setLoginModel(loginResponse.getData());
                            sessionManager.SetLoginSession();
                            Intent in = new Intent(mContext, DashboardActivity.class);
                            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(in);
                        } else {
                            CommonFunction.showToastSingle(mContext, loginResponse.getMessage());
                        }
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    hideProgressDialog();
                }
            });
        } else {
            CommonFunction.showToastSingle(mContext, getResources().getString(R.string.net_connection));

        }
    }

}
