package com.example.aadyam.mi.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Class for Shared Preference
 */
class PrefManager {

    private Context context;

    PrefManager(Context context) {
        this.context = context;
    }

    public void saveLoginDetails(String mobile, String OTP) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Mobile", mobile);
        editor.putString("OTP", OTP);
        editor.commit();
    }

    public String getNumber()
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");
    }

    public boolean isUserLogedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("Email", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password", "").isEmpty();
        return isEmailEmpty || isPasswordEmpty;
    }
}