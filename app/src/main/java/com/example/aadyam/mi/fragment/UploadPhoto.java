package com.example.aadyam.mi.fragment;

import android.Manifest;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.system.ErrnoException;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.aadyam.mi.Global.GPSTracker;
import com.example.aadyam.mi.Utils.CameraUtils;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.activity.MainActivity;
import com.example.aadyam.mi.soap.InspectionDataSoapHelper;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.IntStream;


import static android.content.Context.COMPANION_DEVICE_SERVICE;
import static android.support.v7.app.AppCompatActivity.RESULT_CANCELED;
import static android.support.v7.app.AppCompatActivity.RESULT_OK;
import static com.example.aadyam.mi.activity.MainActivity.IMAGE_EXTENSION;
import static com.example.aadyam.mi.activity.MainActivity.MEDIA_TYPE_IMAGE;


/**
 * A simple {@link Fragment} subclass.
 */


@SuppressWarnings("ALL")
public class UploadPhoto extends Fragment
{

    private static final int SIGNATURE = 5;
    private static final String ALLOTED_ID = "allottedId";
    private static String imageStoragePath;
    private static final int BITMAP_SAMPLE_SIZE = 8;
    private static final int REGULATOR_CODE = 1;
    private static final int STOVE_CODE = 2;
    private static final int HOSE_CODE = 3;
    private static final int INSTALLATION_CODE = 4;
    private static final String KEY_IMAGE_STORAGE_PATH = "image_path";
    private ImageView stove_iv, regulator_iv, hose_iv, installation_iv, signature_iv;

    public static final String GALLERY_DIRECTORY_NAME = "MI_Images";
    private SignaturePad userInput;
    String encoded4;
    private ArrayList<String> imageArray,fileNameArray;
    ArrayList<byte[]> imageByte;

    EditText instruction_editText;
    SignaturePad signaturePad;
    int count=0;
    Bundle savedInstanceState;
    private Context context;//=getContext();
    private Bitmap regulatorBitmap,stoveBitmap,hoseBitmap,installationBitmap,signatureBitmap;
    private ProgressDialog progressDialog;
    Button submit;
    SharedPreferences sharedPreferences;
    //Button save;

    public UploadPhoto()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upload_photo, container, false);
    }

  /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(savedInstanceState==null)
        this.savedInstanceState=savedInstanceState;
    }*/

    @Override
    public void onPause()
    {
        super.onPause();
        // Toast.makeText(getContext(), "onPause()", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStart()
    {
        super.onStart();
      /*  context=getContext();
        sharedPreferences=context.getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);
        final String uniqueNo=sharedPreferences.getString(Constants.UNIQUE_CONSUMER_NO,null);
        String allottedId=sharedPreferences.getString(Constants.ALLOTED_ID,null);
        initializeComponents(getView(),savedInstanceState);
        setSavedPhotos(allottedId);*/
        Log.i("FRAGMENT LIFECYCLE", "onStart()");
        //Toast.makeText(getContext(), "onStart()", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onViewCreated(@NonNull View view, final Bundle savedInstanceState)
    {
        this.savedInstanceState=savedInstanceState;
        context=getContext();
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);
        sharedPreferences=context.getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);

        final String allottedId=sharedPreferences.getString(Constants.ALLOTED_ID,null);

        /*if(imageArray!=null)
        {

            if(setPhotoView(Constants.REGULATOR_CODE,allottedId))
                setPhotoView(Constants.REGULATOR_CODE,allottedId);

            if(setPhotoView(Constants.STOVE_CODE,allottedId))
                setPhotoView(Constants.STOVE_CODE,allottedId);

            if(setPhotoView(Constants.HOSE_CODE,allottedId))
                setPhotoView(Constants.HOSE_CODE,allottedId);

            if(setPhotoView(Constants.INSTALLATION_CODE,allottedId))
                setPhotoView(Constants.INSTALLATION_CODE,allottedId);

            if(setPhotoView(Constants.SIGNATURE,allottedId))
                setPhotoView(Constants.SIGNATURE,allottedId);
        }
*/

        final String uniqueNo=sharedPreferences.getString(Constants.UNIQUE_CONSUMER_NO,null);
        initializeComponents(view,savedInstanceState);
        LinearLayout layout = view.findViewById(R.id.signature_layout);

        //setSavedPhotos(allottedId);

        stove_iv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (CameraUtils.checkPermissions(getContext()))
                {
                    captureImage(STOVE_CODE);
                    // restoring storage image path from saved instance state
                    // otherwise the path will be null on device rotation
                    restoreFromBundle(savedInstanceState, STOVE_CODE);
                }

                else
                {
                    requestCameraPermission(MEDIA_TYPE_IMAGE, STOVE_CODE);
                }

            }
        });


        regulator_iv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (CameraUtils.checkPermissions(getContext()))
                {
                    captureImage(REGULATOR_CODE);
                    // restoring storage image path from saved instance state
                    // otherwise the path will be null on device rotation
                    restoreFromBundle(savedInstanceState, REGULATOR_CODE);
                }

                else
                {
                    requestCameraPermission(MEDIA_TYPE_IMAGE, REGULATOR_CODE);
                }
            }
        });


        installation_iv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (CameraUtils.checkPermissions(getContext()))
                {
                    captureImage(INSTALLATION_CODE);
                    // restoring storage image path from saved instance state
                    // otherwise the path will be null on device rotation

                    restoreFromBundle(savedInstanceState, INSTALLATION_CODE);
                }

                else
                {
                    requestCameraPermission(MEDIA_TYPE_IMAGE, INSTALLATION_CODE);
                }

            }
        });


        hose_iv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if (CameraUtils.checkPermissions(getContext()))
                {
                    captureImage(HOSE_CODE);
                    restoreFromBundle(savedInstanceState, HOSE_CODE);
                }

                else
                {
                    requestCameraPermission(MEDIA_TYPE_IMAGE, HOSE_CODE);
                }
            }
        });



        signature_iv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(getContext());
                View promptsView = li.inflate(R.layout.signature_capture_layout, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                userInput = (SignaturePad) promptsView.findViewById(R.id.signaturePad1);
                userInput.setOnSignedListener(new SignaturePad.OnSignedListener()
                {
                    @Override
                    public void onStartSigning()
                    {

                    }

                    @Override
                    public void onSigned()
                    {
                        signatureBitmap=userInput.getSignatureBitmap();
                    }

                    @Override
                    public void onClear()
                    {

                    }
                });

                // set dialog message
                alertDialogBuilder.setCancelable(false).setPositiveButton("Submit", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog,int id)
                    {
                        signatureBitmap=userInput.getSignatureBitmap();
                        signature_iv.setImageBitmap(signatureBitmap);
                        DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(context);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream .toByteArray();
                        encoded4 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        count++;
                        Log.d(Constants.TAG, "saveCapturedImage: " +userInput.getSignatureBitmap());
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog,int id)
                            {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
                //  restoreFromBundle(savedInstanceState, SIGNATURE);
            }
        });


        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                progressDialog.show();
                if(hoseBitmap!=null && regulatorBitmap!=null && installationBitmap!=null && signatureBitmap!=null && stoveBitmap!=null)
                {
                    ArrayList<byte[]> imageByteArray=saveCapturedImage(stoveBitmap,regulatorBitmap,hoseBitmap,installationBitmap,signatureBitmap);
                    //saveCapturedImage(stoveBitmap,regulatorBitmap,hoseBitmap,installationBitmap,signatureBitmap);
                    String dateString = new MyGlobals(getContext()).getCurrentDate();
                    GPSTracker gps = new GPSTracker(context);
                    DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(context);
                    double latitude,longitude;
                    String allotted_id=sharedPreferences.getString(Constants.ALLOTED_ID,null);
                    String unique=sharedPreferences.getString(Constants.UNIQUE_CONSUMER_NO,null);
                    int count=databaseHelperUser.getFragmentSaveEntries(allotted_id);

                    if(count==1)
                    {
                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();
                        String instruction=instruction_editText.getText().toString();
                        // \n is for new line
                        //  Toast.makeText(context, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                        databaseHelperUser.putExtraAllotedUserData(allotted_id,instruction,latitude,longitude);

                        String personalInfo=databaseHelperUser.getPersonalJsonString(unique);
                        String answerInfo=databaseHelperUser.getAnswerJsonString(unique);

                        // Toast.makeText(context, ""+consumerInfo, Toast.LENGTH_SHORT).show();

                        Log.d(Constants.TAG, "onClick: "+personalInfo);
                        Log.d(Constants.TAG, "onClick: "+answerInfo);
                        // Toast.makeText(context, ""+personalInfo, Toast.LENGTH_SHORT).show();
                        // Toast.makeText(context, ""+answerInfo, Toast.LENGTH_SHORT).show();
                        boolean isSuccess;
                            int[] unsafeIdArray={3,10,13,16,21,23,24,30,32};
                            int [] unsafeValuesArray=new int[unsafeIdArray.length];

                            int unsafeCount = 0;
                            for(int i=0;i<unsafeIdArray.length;i++)
                            {
                                unsafeValuesArray[i]=sharedPreferences.getInt(Constants.ANSWER+unsafeIdArray[i],0);
                                if(sharedPreferences.getInt(Constants.ANSWER+unsafeIdArray[i],0)==1)
                                {
                                    unsafeCount++;
                                }
                            }

                        //check whether the unsafe flag array contains 1 in the array
                        //boolean contains = IntStream.of(unsafeValuesArray).anyMatch(x -> x == 1);

                        if(new MyGlobals(context).isNetworkConnected() && unsafeCount==0)
                        {
                            String consumerInfo=databaseHelperUser.getConsumerJsonString(allotted_id,latitude,longitude,instruction,dateString,true);
                            Log.d(Constants.TAG, "onClick: "+consumerInfo);
                            isSuccess = inspectionWebService(false,consumerInfo, personalInfo, answerInfo, allotted_id,imageByteArray);
                            if(isSuccess)
                            {
                                Toast.makeText(context, "Entry uploaded sucessfully as SAFE!", Toast.LENGTH_SHORT).show();
                              //  databaseHelperUser.deleteAllTableEntries(allotted_id, uniqueNo);
                                progressDialog.dismiss();

                                Intent i = new Intent(getActivity(), MainActivity.class);
                                // set the new task and clear flags
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            }


                            else
                            {
                                Toast.makeText(context, "Server Response SAFE : Fail ", Toast.LENGTH_SHORT).show();
                            }
                        }


                             else if(new MyGlobals(context).isNetworkConnected() && unsafeCount>0)
                            {
                                String consumerInfo=databaseHelperUser.getConsumerJsonString(allotted_id,latitude,longitude,instruction,dateString,true);
                                Log.d(Constants.TAG, "onClick: "+consumerInfo);
                                isSuccess = inspectionWebService(true,consumerInfo, personalInfo, answerInfo, allotted_id,imageByteArray);
                                if(isSuccess)
                                {
                                    Toast.makeText(context, "Entry uploaded sucessfully as UNSAFE!", Toast.LENGTH_SHORT).show();
                                    databaseHelperUser.deleteAllTableEntries(allotted_id, uniqueNo);
                                    progressDialog.dismiss();
                                    Intent i = new Intent(getActivity(), MainActivity.class);
                                    // set the new task and clear flags
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                }

                                else
                                {
                                    Toast.makeText(context, "Server Response UNSAFE : Fail", Toast.LENGTH_SHORT).show();
                                }
                            }


                        else if(!new MyGlobals(context).isNetworkConnected())
                        {
                            progressDialog.dismiss();
                            Toast.makeText(context, "No Internet Connection detected! Entry Saved as Offline. Submit later when internet connection is turned ON", Toast.LENGTH_SHORT).show();
                        }

                        else
                            {
                            progressDialog.dismiss();
                            Toast.makeText(context, "UNHANDLED ERROR!", Toast.LENGTH_SHORT).show();
                            }
                    }



                    else
                    {
                        // Can't get location.
                        // GPS or network is not enabled.
                        // Ask user to enable GPS/network in settings.
                        Toast.makeText(context, "Answer all mandatory fields!", Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        //gps.showSettingsAlert();
                    }

                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                }

                else /*if(hoseBitmap==null && regulatorBitmap==null && installationBitmap==null && signatureBitmap==null && stoveBitmap==null)*/
                {
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();

                    Toast.makeText(context, "Capturing All images is Mandatory!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    @Override
    public void setRetainInstance(boolean retain)
    {
        super.setRetainInstance(retain);
    }


    private void initializeComponents(View view, Bundle savedInstanceState)
    {
        context=getContext();
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);
        submit = view.findViewById(R.id.button_submit);
        instruction_editText=view.findViewById(R.id.consumer_instruction_editext);
        super.onViewCreated(view, savedInstanceState);
        assert context != null;
        stove_iv = view.findViewById(R.id.stove_iv);
        regulator_iv = view.findViewById(R.id.regulator_iv);
        hose_iv = view.findViewById(R.id.hose_iv);
        installation_iv = view.findViewById(R.id.installation_iv);
        signature_iv = view.findViewById(R.id.signature_iv);
    }



    private void setSavedPhotos(String allottedId)
    {
        DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(getContext());
        if(databaseHelperUser.isPhotoEntryPresent(allottedId))
        {
            //ArrayList<String> imageString= databaseHelperUser.getPhotoEntry(allottedId);
            imageArray=databaseHelperUser.getPhotoEntry(allottedId);
            Bitmap[] decodedByte=new Bitmap[5];
            ArrayList<byte[]> decodedString=new ArrayList<>();

            for(int i=0;i<5;i++)
            {
                decodedString.add(Base64.decode(imageArray.get(i), Base64.DEFAULT));
                decodedByte[i]= BitmapFactory.decodeByteArray(decodedString.get(i), 0, decodedString.get(i).length);
            }


            regulator_iv.setImageBitmap(decodedByte[0]);
            regulatorBitmap=decodedByte[0];
            hose_iv.setImageBitmap(decodedByte[1]);
            hoseBitmap=decodedByte[1];
            installation_iv.setImageBitmap(decodedByte[2]);
            installationBitmap=decodedByte[2];
            stove_iv.setImageBitmap(decodedByte[3]);
            stoveBitmap=decodedByte[3];
            signature_iv.setImageBitmap(decodedByte[4]);
            signatureBitmap=decodedByte[4];
        }
    }


    private void requestCameraPermission(final int type, final int CODE)
    {
        Dexter.withActivity(getActivity()).withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener()
        {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report)
            {
                if (report.areAllPermissionsGranted())
                {
                    if (type == MEDIA_TYPE_IMAGE)
                    {
                        // capture regulator picture
                        if (CODE == REGULATOR_CODE)
                            captureImage(REGULATOR_CODE);

                            // capture stove picture
                        else if (CODE == STOVE_CODE)
                            captureImage(STOVE_CODE);

                            // capture hose picture
                        else if (CODE == HOSE_CODE)
                            captureImage(HOSE_CODE);

                            // capture installation picture
                        else if (CODE == INSTALLATION_CODE)
                            captureImage(INSTALLATION_CODE);
                    }


                    else
                    {
                        if  (CODE == REGULATOR_CODE)
                            requestCameraPermission(MEDIA_TYPE_IMAGE, REGULATOR_CODE);

                        else if (CODE == STOVE_CODE)
                            requestCameraPermission(MEDIA_TYPE_IMAGE, STOVE_CODE);

                        else if (CODE == HOSE_CODE)
                            requestCameraPermission(MEDIA_TYPE_IMAGE, HOSE_CODE);

                        else if (CODE == INSTALLATION_CODE)
                            requestCameraPermission(MEDIA_TYPE_IMAGE, INSTALLATION_CODE);
                    }


                }

                else if (report.isAnyPermissionPermanentlyDenied())
                {
                    showPermissionsAlert();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<com.karumi.dexter.listener.PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }


    private void showPermissionsAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Permissions required!")
                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        CameraUtils.openSettings(getContext());
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                }).show();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState)
    {
        super.onViewStateRestored(savedInstanceState);

        SharedPreferences sharedPreferences=getContext().getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);
        String allottedId=sharedPreferences.getString(Constants.ALLOTED_ID,null);

        if(savedInstanceState!=null)
        {
            // Retrieve the user email value from bundle.
            //setSavedPhotos(allottedId);
            // Do not need below code, because android os will automatically save and restore view objects value that has id attribute.
            // EditText userEmailInputBox = getActivity().findViewById(R.id.fragment_instance_state_user_email_edit_box);
            //userEmailInputBox.setText(userEmail);
            // Log the retrieved user email value.
            //this.logDebugInfo("Fragment onViewStateRestored method is called.Retrieved user input email is " + userEmail);
        }
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        // save file url in bundle as it will be null on screen orientation
        // changes
        if(outState!=null)
        {
            // Retrieve the user email value from bundle.
            //  setPhotoView() = savedInstanceState.getString(this.USER_EMAIL_KEY);
            imageArray=outState.getStringArrayList(Constants.IMAGE_ARRAY);
            // Do not need below code, because android os will automatically save and restore view objects value that has id attribute.
            // EditText userEmailInputBox = getActivity().findViewById(R.id.fragment_instance_state_user_email_edit_box);
            //userEmailInputBox.setText(userEmail);
            // Log the retrieved user email value.
            //this.logDebugInfo("Fragment onViewStateRestored method is called.Retrieved user input email is " + userEmail)
        }

        else
        {
            outState.putStringArrayList(Constants.IMAGE_ARRAY, imageArray);
            outState.putString(KEY_IMAGE_STORAGE_PATH, imageStoragePath);
            int mCurCheckPosition = 0;
            outState.putInt("curChoice", mCurCheckPosition);
        }

    }



    public void setPhotoView(int ImageViewCode,String allottedId)
    {


    }



    public void restoreFromBundle(Bundle savedInstanceState, int CODE)
    {
        if (savedInstanceState!= null)
        {
            if (savedInstanceState.containsKey(KEY_IMAGE_STORAGE_PATH))
            {
                imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);

                if (!TextUtils.isEmpty(imageStoragePath))
                {
                    if (imageStoragePath.substring(imageStoragePath.lastIndexOf(".")).equals("." + IMAGE_EXTENSION))
                    {
                        previewCapturedImage(CODE);
                    }
                }
            }
        }
    }


    public byte[] imageToByteArray(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 65, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private int getCountOfCapturedImages()
    {
        return count;
    }

    private  ArrayList<byte[]>  saveCapturedImage(Bitmap bitmap1,Bitmap bitmap2,Bitmap bitmap3,Bitmap bitmap4,Bitmap bitmap5)
    {
        imageArray=new ArrayList<>();
        fileNameArray=new ArrayList<>();

//        if(!imageByte.isEmpty())
  //      imageByte.clear();

        imageByte=new ArrayList<>();


        DatabaseHelperUser databaseHelperUser = new DatabaseHelperUser(getContext());

            imageByte.add(imageToByteArray(bitmap1));
            imageByte.add(imageToByteArray(bitmap2));
            imageByte.add(imageToByteArray(bitmap3));
            imageByte.add(imageToByteArray(bitmap4));
            imageByte.add(imageToByteArray(bitmap5));

        String[] CODE = new String[imageByte.size()];

            for(int i =0;i<imageByte.size();i++)
            {
                int j=i+1;
                String filename = CameraUtils.getOutputMediaFile(j).getName();
                String encodedImage1 = Base64.encodeToString(imageByte.get(i), Base64.DEFAULT);
                fileNameArray.add(filename);
                imageArray.add(encodedImage1);
                databaseHelperUser.setPhotos(filename, encodedImage1, ""+j);
            }

        return  imageByte;


    }



    //set image to imageView of respective types
    private void previewCapturedImage(int CODE)
    {
        try
        {
            switch (CODE)
            {
                case REGULATOR_CODE:
                    regulatorBitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
                    regulator_iv.setImageBitmap(regulatorBitmap);

                    break;

                case STOVE_CODE:
                    stoveBitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
                    stove_iv.setImageBitmap(stoveBitmap);
                    break;

                case HOSE_CODE:
                    hoseBitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
                    hose_iv.setImageBitmap(hoseBitmap);
                    break;

                case INSTALLATION_CODE:
                    installationBitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
                    installation_iv.setImageBitmap(installationBitmap);
                    break;

                case SIGNATURE:
                    signatureBitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
                    signature_iv.setImageBitmap(stoveBitmap);
                    break;
            }
        }

        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    private void captureImage(int CODE)
    {
        //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //File file;

        switch (CODE)
        {
            case REGULATOR_CODE:
               /* *//*File*//* file = CameraUtils.getOutputMediaFile(REGULATOR_CODE);
               *//* if (file != null)
                {
                    imageStoragePath = file.getAbsolutePath();
                }

                Uri fileUri = CameraUtils.getOutputMediaFileUri(getContext(), file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                // start the image capture Intent
                startActivityForResult(intent, REGULATOR_CODE);*/

                savePhotoToGallery(REGULATOR_CODE);
                break;

            case STOVE_CODE:
              /* // Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                *//*File*//* file = CameraUtils.getOutputMediaFile(STOVE_CODE);

                if (file != null)
                {
                    imageStoragePath = file.getAbsolutePath();
                }


                Uri fileUri1 = CameraUtils.getOutputMediaFileUri(getContext(), file1);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri1);
                // start the image capture Intent
                startActivityForResult(intent, STOVE_CODE);*/

                savePhotoToGallery(STOVE_CODE);
                break;

            case HOSE_CODE:
             /* //  Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                *//*File*//* file = CameraUtils.getOutputMediaFile(HOSE_CODE);
                if (file != null)
                {
                    imageStoragePath = file.getAbsolutePath();
                }

                Uri fileUri2 = CameraUtils.getOutputMediaFileUri(getContext(), file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri2);
                // start the image capture Intent
                startActivityForResult(intent, HOSE_CODE);*/
                savePhotoToGallery(HOSE_CODE);
                break;

            case INSTALLATION_CODE:
              /* // Intent intent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               *//* File*//* file = CameraUtils.getOutputMediaFile(INSTALLATION_CODE);
                if (file != null)
                {
                    imageStoragePath = file.getAbsolutePath();
                }


                Uri fileUri3 = CameraUtils.getOutputMediaFileUri(getContext(), file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri3);

                // start the image capture Intent
                startActivityForResult(intent, INSTALLATION_CODE);*/
                savePhotoToGallery(INSTALLATION_CODE);
                break;

            case SIGNATURE:
               /* File file = CameraUtils.getOutputMediaFile(SIGNATURE);
                if (file != null)
                {
                    imageStoragePath = file.getAbsolutePath();
                }

                Uri fileUri4 = CameraUtils.getOutputMediaFileUri(getContext(), file);
                //intent1.putExtra(MediaStore.EXTRA_OUTPUT, fileUri4);*/
                savePhotoToGallery(SIGNATURE);
                break;
        }
    }

    private void savePhotoToGallery(int Code)
    {
        File file = CameraUtils.getOutputMediaFile(Code);

        if (file != null)
        {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getContext(), file);

        if(Code!=SIGNATURE) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            // start the image capture Intent
            startActivityForResult(intent, Code);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REGULATOR_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getContext(), imageStoragePath);
                // successfully captured the image
                // display it in image view
                previewCapturedImage(REGULATOR_CODE);
            }


            else if (resultCode == RESULT_CANCELED)
            {
                // user cancelled Image capture
                Toast.makeText(getContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            }


            else
            {
                // failed to capture image
                Toast.makeText(getContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
            }
        }


        else if (requestCode == HOSE_CODE)
        {

            if (resultCode == RESULT_OK)
            {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getContext(), imageStoragePath);
                // successfully captured the image
                // display it in image view
                previewCapturedImage(HOSE_CODE);
            }

            else if (resultCode == RESULT_CANCELED)
            {
                // user cancelled Image capture
                Toast.makeText(getContext(), "User cancelled image capture", Toast.LENGTH_SHORT).show();
            }

            else
            {
                // failed to capture image
                Toast.makeText(getContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
            }

        }

        else if (requestCode == INSTALLATION_CODE)
        {

            if (resultCode == RESULT_OK)
            {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getContext(), imageStoragePath);
                // successfully captured the image
                // display it in image view
                previewCapturedImage(INSTALLATION_CODE);
            }

            else if (resultCode == RESULT_CANCELED)
            {
                // user cancelled Image capture
                Toast.makeText(getContext(), "User cancelled image capture", Toast.LENGTH_SHORT).show();
            }


            else
            {
                // failed to capture image
                Toast.makeText(getContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
            }

        }

        else if (requestCode == STOVE_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getContext(), imageStoragePath);
                // successfully captured the image
                // display it in image view
                previewCapturedImage(STOVE_CODE);
            }


            else if (resultCode == RESULT_CANCELED)
            {
                // user cancelled Image capture
                Toast.makeText(getContext(), "User cancelled image capture", Toast.LENGTH_SHORT).show();
            }


            else
            {
                // failed to capture image
                Toast.makeText(getContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
            }

        }




        else if (requestCode == Constants.SIGNATURE)
        {
            if (resultCode == RESULT_OK)
            {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getContext(), imageStoragePath);
                // successfully captured the image
                // display it in image view
                previewCapturedImage(STOVE_CODE);
            }

            else if (resultCode == RESULT_CANCELED)
            {
                // user cancelled Image capture
                Toast.makeText(getContext(), "User cancelled image capture", Toast.LENGTH_SHORT).show();
            }
            else
            {
                // failed to capture image
                Toast.makeText(getContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
            }

        }
    }



    public void replaceFragment(Fragment fragment,boolean addToBackStack,Context context)
    {
        android.app.FragmentTransaction transaction = ((FragmentActivity) context).getFragmentManager().beginTransaction();
        if (addToBackStack)
        {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }


        //transaction.add(R.id., fragment);
        transaction.commit();
        ((FragmentActivity) context).getFragmentManager().executePendingTransactions();

    }



    private boolean inspectionWebService(boolean unsafeFlag,String consumerInfo,String personalInfo,String answerInfo,String allotedId,ArrayList<byte[]> imageByteArray)
    {
        boolean token = false;
        try
        {

            // Mobile completed flag service
            AQuery aQuery = new AQuery(getContext());
            String url = Constants.InspCompletedFlagInMobile + "AllotmentId=" +allotedId+   "&" + "IsCompleteFlag=" + "1";
            Date currentTime = Calendar.getInstance().getTime();
            String date = currentTime.toString();
            //Constants.printResponseLog(InspectionDataService.this, " InspCompletedFlagInMobile Service - " + "AllotmentId=" + strAllotmentId, "Request -", date.toString(), " Seq - 001");

            aQuery.ajax(url, JSONObject.class, 60000, new AjaxCallback<JSONObject>()
            {
                public void callback(String url, JSONObject object, AjaxStatus status)
                {
                    super.callback(url, object, status);
                    timeout(60000);
                    System.out.println("ResponseInspectionMobileFlag" + object);
                        if (object != null)
                        {
                            Date currentTime = Calendar.getInstance().getTime();
                            String date = currentTime.toString();
                            // Constants.printResponseLog(InspectionDataService.this, " InspCompletedFlagInMobile Service - " + "", "Response - Success", date.toString(), " Seq - 001");
                        }
                }
            });


            LinkedHashMap recordHashMap = new LinkedHashMap<>();
            recordHashMap.put("ConsumerInfo", consumerInfo);

            if(!unsafeFlag)
            {
                recordHashMap.put("PersonalInfo", personalInfo);
            }

            recordHashMap.put("QuestionsInfo", answerInfo);

            for(int i=0;i<fileNameArray.size();i++)
            {
                int j=i+1;
                recordHashMap.put("Img"+j, fileNameArray.get(i));
            }




            for(int i=0;i<imageByteArray.size();i++)
            {
                int j=i+1;
                recordHashMap.put("img"+j+"Byt", imageByteArray.get(i));
                String encodedString=Base64.encodeToString(imageByteArray.get(i),Base64.DEFAULT);
                Log.d(Constants.TAG, "inspectionWebService: "+encodedString);


            }


            InspectionDataSoapHelper helper=new InspectionDataSoapHelper();

            for(int i=0;i<recordHashMap.size();i++)
                Log.d(Constants.TAG, "inspectionWebService LinkedHashMap : "+recordHashMap.get(i));

            if(!unsafeFlag)
            {
                String response = helper.getSoapRequest(context, Constants.NAMESPACE, Constants.METHOD_INSPECTION_DATA_PostFile, Constants.ServerUrl_Soap, Constants.SOAP_ACTION_INSPECTION_DATA, recordHashMap);
                Log.d(Constants.TAG, "Server Response SAFE : " + response);
                //Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();
                if (response != null && !response.matches("fail|Fail|FAIL"))
                    token = true;

                else
                    token = false;
            }

            else if(unsafeFlag)
            {
                String response = helper.getSoapRequest(context, Constants.NAMESPACE, Constants.METHOD_INSPECTION_DATA_PostFile, Constants.ServerUrl_Soap, Constants.SOAP_ACTION_INSPECTION_DATA, recordHashMap);
                Log.d(Constants.TAG, "Server Response UNSAFE : " + response);
                //Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();
                if (response != null && !response.matches("fail|Fail|FAIL"))
                    token = true;

                else
                    token = false;
            }

           /* else if(unsafeFlag)
            {
                String response = helper.getSoapRequest(context, Constants.NAMESPACE, Constants.METHOD_UNSAFE_INSPECTION_DATA_PostFile, Constants.ServerUrl_Soap, Constants.SOAP_ACTION_UNSAFE_INSPECTION_DATA, recordHashMap);
                Log.d(Constants.TAG, "Server Response UNSAFE : " + response);
                if (response != null && !response.matches("fail|Fail|FAIL"))
                    token = true;
                else
                    token = false;
            }*/
            return token;
        }

        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Exception caught!"+e.getMessage(), Toast.LENGTH_SHORT).show();
                /* try {
                LogAllModel logDetailModel = new LogAllModel();
                logDetailModel.setLogPriority("High");
                logDetailModel.setLogFor("Exception");
                logDetailModel.setLogClass("Inspection Data Service");
                logDetailModel.setLog_method("InspectionData");
                logDetailModel.setLog_data("");
                logDetailModel.setLog_status("");
                logDetailModel.setLogSubmited("");
                logDetailModel.setException(e.toString());
                logDetailModel.setLogKey("");
                logArrayList.add(logDetailModel);
                new Logger(InspectionDataService.this).saveLog(logArrayList);*/
            System.out.println("Exception@@" + e.getMessage());
            token=false;
        }

        return token;
    }

}
