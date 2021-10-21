package com.android.store2door.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hitesh Kushwah on 05/01/18.
 */

public class JsonUtils {

    public static int getIntFromJson(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getStringFromJson(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static JSONObject getJsonObjectFromJson(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public static JSONObject getJsonObjectFromResponse(Object data) {
        try {
            Log.e("data", String.valueOf(data));
            // Log.e("data", String.valueOf(data));
            JSONObject jObj = new JSONObject(String.valueOf(data));
            Log.e("data_json", jObj.toString());
            return jObj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public static int getStatusFromJson(JSONObject jsonObject) {
        try {
            return jsonObject.getInt("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean isErrorExists(JSONObject jsonObject) {
        try {
            int status = jsonObject.getInt("status");
            if (status == 0)
                return false;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static JSONArray getJsonArray(String jsonArray) {
        try {
            return new JSONArray(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    public static <T> T getClass(Class<T> clazz, String jsonObj) {
        JsonParser parser = new JsonParser();
        JsonElement mJson = parser.parse(jsonObj);
        Gson gson = new Gson();
        T classReturn = null;
        try {
            classReturn = gson.fromJson(mJson, clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return classReturn;

    }

    public static <T> List<T> getListClass(Class<T> clazz, String jsonArray) {
        JSONArray ja = getJsonArray(jsonArray);
        List<T> list = new ArrayList<>();
        if (ja.length() > 0) {
            for (int i = 0; i < ja.length(); i++) {
                try {
                    list.add(getClass(clazz, ja.getJSONObject(i).toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;

    }
}
