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
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.database.DatabaseHelperUser;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

public class SplashScreenActivity extends AppCompatActivity
{
    private static int SPLASH_TIME_OUT = 5000;
    private static final int REQUEST = 112;
    TextView txtVersionCode;
    private Dialog dialog;
    //SharedPreferences sharedPreferences;
    //SharedPreferences.Editor editor;
    private Context context;
    private DatabaseHelperUser databaseHelperUser;
    private String questionVersion;
    private SharedPreferences prefs = null;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        prefs = getSharedPreferences(Constants.PREFS_NAME+"_FirstRun", MODE_PRIVATE);
        context=getApplicationContext();
        databaseHelperUser = new DatabaseHelperUser(context,this);

        if(new MyGlobals(this).isNetworkConnected())
            databaseHelperUser.getAllotment();
        //databaseHelperUser.getAllotment();

        if (Build.VERSION.SDK_INT >= 23)
        {
            String[] PERMISSIONS =
                    {
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    };

            //Checks if there are permissions granted or not
            if (!hasPermissions(SplashScreenActivity.this, PERMISSIONS))
            {
                ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST);
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

        // boolean firstRun=sharedPreferences.getBoolean(Constants.FIRST_RUN,true);
        //proceed();

      /*  final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);*/
        /*AsyncTaskBar task = new AsyncTaskBar();
        task.setProgressBar(progressBar);
        task.execute();
*/
        //Stores PERMISSIONS in a String array
    }


    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @SuppressLint("ApplySharedPref")
    @Override
    protected void onResume()
    {
        super.onResume();
        if (prefs.getBoolean(Constants.FIRST_RUN, true))
        {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            databaseHelperUser.getAllotment();
            prefs.edit().putBoolean(Constants.FIRST_RUN, false).commit();
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
                        Log.d("TAG", "Grant Permissions ");
                        proceed();
                    }


                else
                    {
                    Log.d("TAG", "PERMISSIONS Denied");
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("You must Allow all the requested Permissions. Application will exit now");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            finish();
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



    private void proceed()
    {
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }



 /*   public class AsyncTaskBar extends AsyncTask<Void, Integer, Void>
    {

        ProgressBar bar;
        int progress_status;

        public void setProgressBar(ProgressBar bar)
        {
            this.bar = bar;
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            while (progress_status < 100)
            {
                //databaseHelperUser.getAllotment();
                progress_status +=2;
                publishProgress(progress_status);
                SystemClock.sleep(300);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            if (this.bar != null) {
                bar.setProgress(values[0]);
            }
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //Toast.makeText(Main.getApp(), "Invoke onPostExecute()", Toast.LENGTH_SHORT).show();
            //Main.getApp().txt_percentage.setText("download complete");
        }

    }*/
}
