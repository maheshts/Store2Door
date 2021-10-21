package com.android.store2door.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonFunction {

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Validate email with regular expression
     *
     * @param email password for validation
     * @return true valid email, false invalid email
     */
    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return !matcher.matches();
    }

    private static Toast toast;

    /**
     * Show single toast when multiple click
     *
     * @param context activity
     * @param text    message
     */
    @SuppressLint("ShowToast")
    public static void showToastSingle(Context context, String text) {

        try {
            toast.getView().isShown();     // true if visible
            toast.setText(text);
        } catch (Exception e) {         // invisible if exception
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        toast.show();

    }

    /**
     * Validate password with regular expression
     * with min 6 digit
     *
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public static boolean passwordValidate(final String password) {
        boolean matcher;
        matcher = password.length() >= 8;
        return !matcher;
    }
}
