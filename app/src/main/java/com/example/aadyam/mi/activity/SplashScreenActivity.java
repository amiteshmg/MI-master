package com.example.aadyam.mi.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreenActivity extends AppCompatActivity
{
    private static int SPLASH_TIME_OUT = 3000;
    private static final int REQUEST = 112;
    TextView txtVersionCode;
    Dialog dialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    DatabaseHelperUser databaseHelperUser;
    private String questionVersion;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        databaseHelperUser = new DatabaseHelperUser(getBaseContext());

        //databaseHelperUser.updateTables();

        //databaseHelperUser.createAndInitializeTables();

        databaseHelperUser.getQuestion();

        //databaseHelperUser.getAllotment();

        sharedPreferences = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Constants.intentFlag=true;

        //Stores PERMISSIONS in a String array

        if (Build.VERSION.SDK_INT >= 23)
        {
            String[] PERMISSIONS =
                    {
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.INTERNET
                    };

            //Checks if there are permissions granted or not

            if (!hasPermissions(SplashScreenActivity.this, PERMISSIONS))
            {
                ActivityCompat.requestPermissions( this, PERMISSIONS, REQUEST);
            }

            else
            {
                proceed();
            }
        }

        else
        {
            proceed();
        }


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST:
                {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    {
                    Log.d("TAG", " @@@ PERMISSIONS grant ");
                    proceed();
                    }

                    else
                    {
                        Log.d("TAG", " @@@ PERMISSIONS Denied ");
                        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("You must Allow all Permissions.Application will exit now");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { finish();
                        }
                    });
                        builder.setCancelable(false);
                        dialog = builder.show();
                    }
                }
        }
    }


    private static boolean hasPermissions(Context context, String... permissions)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null)
        {
            for (String permission : permissions)
            {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }

        return true;
    }


    public void proceed()
    {

        new Handler().postDelayed(new Runnable()
        {

            public void run()
            {

                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);

                startActivity(i);

                /*String loginFlag = new SharedValues(context).loginFlag();
                if (loginFlag.equalsIgnoreCase(Constants.strTrue))
                {
                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                }


                  else
                  {
                    //TODO : Here make the second intent activity once again to loginActivity once sharedpreference is done
                    Intent in = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(in);
                }*/


                finish();
            }
        }, SPLASH_TIME_OUT);
    }




}
