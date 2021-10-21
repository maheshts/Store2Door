package com.android.store2door.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.android.store2door.DashboardActivity;
import com.android.store2door.LoginActivity;
import com.android.store2door.api.Callbacks.loginResponse.Data;
import com.google.gson.Gson;

public class SessionManager {

    // Context
    private Context _context;

    // Shared Preferences reference
    private static SharedPreferences preferences;

    // Editor reference for Shared preferences
    private SharedPreferences.Editor editor;

    // Shared pref  file name
    private static final String PREF_NAME = "Store2DoorPreferences";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";


    // Constructor
    @SuppressLint("CommitPrefEdits")
    SessionManager(Context context) {
        this._context = context;
        int PRIVATE_MODE = 0;
        preferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    // Check for login
    private boolean islogin() {
        return preferences.getBoolean(IS_LOGIN, false);
    }

    public void setLoginModel(Data userModel) {
        Gson gson = new Gson();
        String json = gson.toJson(userModel);
        editor.putString("LoginModel", json);
        editor.commit();
    }

    public Data getLoginModel() {
        Gson gson = new Gson();
        String json = preferences.getString("LoginModel", "");
        return gson.fromJson(json, Data.class);
    }


    public void SetLoginSession() {
        editor.putBoolean(IS_LOGIN, true);
        editor.apply();
    }


    public void CheckLogin() {
        if (this.islogin()) {
            Intent in = new Intent(_context, DashboardActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(in);
        } else {
          Intent in = new Intent(_context, LoginActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(in);
        }
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        editor.clear();
        editor.apply();
        Intent intent = new Intent(_context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(intent);

    }

}
