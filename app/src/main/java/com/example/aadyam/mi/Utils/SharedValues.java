package com.example.aadyam.mi.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedValues
{
    private Context context;

    public SharedValues(Context mcontext)
    {
        context = mcontext;
    }

    public void saveSharedPreference(String key, String value)
    {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public String loginFlag()
    {

        SharedPreferences pref = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);

        return pref.getString(Constants.loginFlag, "");

    }

}
