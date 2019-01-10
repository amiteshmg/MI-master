package com.example.aadyam.mi.activity;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.example.aadyam.mi.activity.session.AlertDialogManager;
import com.example.aadyam.mi.activity.session.SessionManager;
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
    AlertDialogManager alert;
    SessionManager sessionManager;


    SharedPreferences sharedPreferences;
    private TextView txtDescription;

    private ImageView imgPreview;
    private EditText mobileNoEditText;
    private AlertDialog.Builder builder;

    @Override
    public void onBackPressed()
    {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        sessionManager=new SessionManager(getApplicationContext());



        button=findViewById(R.id.btn_otp_generate);

       builder=new AlertDialog.Builder(getBaseContext());
        builder.setMessage("Device Not Registered!");

        builder.setCancelable(true);

        mobileNoEditText=findViewById(R.id.input_number);

       alert = new AlertDialogManager();

        //TODO : Code for sharedPreference . Uncomment later on

        if (!CameraUtils.isDeviceSupportCamera(getApplicationContext()))
        {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();
            // will close the app if the device doesn't have camera
            finish();
        }


        button.setOnClickListener(new View.OnClickListener()
        {
           /* @Override
            public void onClick(View v)
            {
                if(mobileNoEditText.getText().toString()=="9823844616")
                {

                    LayoutInflater li = LayoutInflater.from(getBaseContext());
                    View promptsView = li.inflate(R.layout.input_dialog, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            getBaseContext());

                    // set prompts.xml to alertdialog builder
                    alertDialogBuilder.setView(promptsView);

                    final EditText userInput = (EditText) promptsView
                            .findViewById(R.id.passCodeEditText);

                    // set dialog message
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // get user input and set it to result
                                            // edit text

                                            if(userInput.getText().toString()=="7979")
                                            {

                                              proceed();
                                            }
                                            //result.setText(userInput.getText());
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                }

                else
                    {
                        builder.show();
                }


                // getDistributor();
                //   login();
            }
        });
*/


            public void onClick(View arg0)
            {
                // Get username, password from EditText
                String mobileNo = mobileNoEditText.getText().toString();
                //String password = txtPassword.getText().toString();

                // Check if username, password is filled
                if(mobileNo.trim().length() > 0 )
                {
                    // For testing puspose username, password is checked with sample data
                    // username = test
                    // password = test
                    if(mobileNo.equals("9823844616") ){

                        // Creating user login session
                        // For testing i am stroing name, email as follow
                        // Use user real data
                        sessionManager.createLoginSession("Amitesh Gadade");

                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();

                    }else{
                        // username / password doesn't match
                        alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is incorrect", false);
                    }
                }else{
                    // user didn't entered username or password
                    // Show alert asking him to enter the details
                    alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);
                }

            }
        });

    }


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
                    Toast.makeText(LoginActivity.this, "Some fail occurred! Please try again!", Toast.LENGTH_SHORT).show();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void proceed()
    {

        new Handler().postDelayed(new Runnable() {

            public void run() {

                SharedPreferences sharedPreferences=getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(Constants.MOBILE_NO,"9823844616");
                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }


}
