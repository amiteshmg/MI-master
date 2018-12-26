package com.example.aadyam.mi.activity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.CameraUtils;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.Utils.SharedValues;
import com.example.aadyam.mi.model.Distributor;
import com.example.aadyam.mi.rest.ApiClient;
import com.example.aadyam.mi.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import butterknife.BindView;


public class LoginActivity extends AppCompatActivity
{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
   EditText editText;
    Button button;
    String number;
    SharedPreferences sharedPreferences;
    private TextView txtDescription;

    private ImageView imgPreview;



    @Override
    public void onBackPressed()
    {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        button=findViewById(R.id.btn_otp_generate);

        editText=findViewById(R.id.input_number);

        //TODO : Code for sharedPreference . Uncomment later on

        if (!CameraUtils.isDeviceSupportCamera(getApplicationContext()))
        {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();
            // will close the app if the device doesn't have camera
            finish();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               // getDistributor();
                //   login();
            }
        });


    }





    /*private void login()
    {

        if(!validate())
        {
            onLoginFailed();
        }

        button.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        //TODO : Implement sharedpreferences later on
        *//*
        number=editText.toString();

        sharedPreferences.edit().putString("mobileNo",number);
        *//*

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        getDistributor();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


   *//* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }*//*

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        button.setEnabled(true);
    }

    public void onLoginSuccess() {
        button.setEnabled(true);
        finish();
    }


    private boolean validate()
    {
        String number=editText.getText().toString();

        boolean valid=true;

        if(number.isEmpty() || !Patterns.PHONE.matcher(number).matches())
        {
            editText.setError("Please enter a valid mobile number");
            valid = false;
        }else
        {
            editText.setError(null);
        }


        return valid;
    }
*/
    public void getDistributor()
    {
        number = editText.getText().toString();


        ApiInterface apiInterface;

        apiInterface=ApiClient.getClient().create(ApiInterface.class);

        //TODO :take number from login page

         Call<Distributor> call = apiInterface.getDistributorDetails(number);

        call.enqueue(new Callback<Distributor>()
        {

            @SuppressLint("CommitPrefEdits")
            @Override
            public void onResponse(@NonNull Call<Distributor> call, @NonNull Response<Distributor> response)
            {

                if(response.body().getDistributorList().isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Empty list", Toast.LENGTH_SHORT).show();
                }


                if(response.body().getDistributorList().size()==1)
                {

                    try
                    {
                        wait(3000);
                    }

                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this, "Retofit response OTP user"+response.body().getDistributorList().get(0).getResult(), Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences=getSharedPreferences(Constants.PREFS_NAME,MODE_PRIVATE);

                sharedPreferences.edit().putString("Number",number);

                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

                }

                else if(response.body().getDistributorList().size()>0)
                {
                    Toast.makeText(LoginActivity.this, "Some error occurred! Please try again!", Toast.LENGTH_SHORT).show();
                }


                else
                {
                    Toast.makeText(LoginActivity.this,"Number not Registered!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Distributor> call, @NonNull Throwable t) {
                Log.d("ERROR",t.getMessage());
                Toast.makeText(LoginActivity.this, "Server is Facing Some issues .Please try again After sometime "+t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void proceed()
    {

        new Handler().postDelayed(new Runnable() {

            public void run() {

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);

                String loginFlag = new SharedValues(getApplicationContext()).loginFlag();




              /*  if (loginFlag.equalsIgnoreCase(Constants.strTrue)) {


                        Intent intent = new Intent(SplashScreenActivity.this, NavigationDrawerActivitySerEng.class);
                        startActivity(intent);



                } else {

                    Intent i = new Intent(SplashScreenActivity.this, OTPActivity.class);
                    startActivity(i);
                }*/

                finish();
            }
        }, 2000);
    }


}
