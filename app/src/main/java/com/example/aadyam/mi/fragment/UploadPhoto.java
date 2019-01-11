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
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
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
import com.example.aadyam.mi.activity.soap.InspectionDataSoapHelper;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import static android.support.v7.app.AppCompatActivity.RESULT_CANCELED;
import static android.support.v7.app.AppCompatActivity.RESULT_OK;
import static com.example.aadyam.mi.activity.MainActivity.IMAGE_EXTENSION;
import static com.example.aadyam.mi.activity.MainActivity.MEDIA_TYPE_IMAGE;


/**
 * A simple {@link Fragment} subclass.
 */


public class UploadPhoto extends Fragment {
    private static final int SIGNATURE = 5;
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
    private Context context;//=getContext();
    private Bitmap regulatorBitmap,stoveBitmap,hoseBitmap,installationBitmap,signatureBitmap;
    private ProgressDialog progressDialog;
    Button submit;
    SharedPreferences sharedPreferences;
    //Button save;

    public UploadPhoto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upload_photo, container, false);
    }


    @Override
    public void onPause() {
        super.onPause();
        // Toast.makeText(getContext(), "onPause()", Toast.LENGTH_SHORT).show();

        //TODO enter code to save image to database in Base64 format conversion
    }


    @Override
    public void onStart() {
        super.onStart();

        Log.i("FRAGMENT LIFECYCLE", "onStart()");
        //Toast.makeText(getContext(), "onStart()", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        context=getContext();
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);

        submit = view.findViewById(R.id.button_submit);

        //save = view.findViewById(R.id.button_save);

        instruction_editText=view.findViewById(R.id.consumer_instruction_editext);
        super.onViewCreated(view, savedInstanceState);

        //signaturePad=view.findViewById(R.id.);
        assert context != null;
        sharedPreferences=context.getSharedPreferences(Constants.PREFS_NAME,Context.MODE_PRIVATE);

        final String uniqueNo=sharedPreferences.getString(Constants.UNIQUE_CONSUMER_NO,null);
        String allottedId=sharedPreferences.getString(Constants.ALLOTED_ID,null);

        // setPhotoView(Constants.REGULATOR_CODE,allottedId);
        // setPhotoView(Constants.STOVE_CODE,allottedId);
        // setPhotoView(Constants.HOSE_CODE,allottedId);
        // setPhotoView(Constants.INSTALLATION_CODE,allottedId);
        //  setPhotoView(Constants.SIGNATURE,allottedId);


        new MyGlobals(getContext()).getJSON();
        stove_iv = view.findViewById(R.id.stove_iv);
        regulator_iv = view.findViewById(R.id.regulator_iv);
        hose_iv = view.findViewById(R.id.hose_iv);
        installation_iv = view.findViewById(R.id.installation_iv);
        signature_iv = view.findViewById(R.id.signature_iv);

        LinearLayout layout = view.findViewById(R.id.signature_layout);
        stove_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CameraUtils.checkPermissions(getContext())) {
                    captureImage(STOVE_CODE);
                    // restoring storage image path from saved instance state
                    // otherwise the path will be null on device rotation

                    restoreFromBundle(savedInstanceState, STOVE_CODE);
                } else {
                    requestCameraPermission(MEDIA_TYPE_IMAGE, STOVE_CODE);
                }

            }
        });


        regulator_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CameraUtils.checkPermissions(getContext())) {
                    captureImage(REGULATOR_CODE);
                    // restoring storage image path from saved instance state
                    // otherwise the path will be null on device rotation

                    restoreFromBundle(savedInstanceState, REGULATOR_CODE);
                } else {
                    requestCameraPermission(MEDIA_TYPE_IMAGE, REGULATOR_CODE);
                }


            }
        });


        installation_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CameraUtils.checkPermissions(getContext())) {
                    captureImage(INSTALLATION_CODE);
                    // restoring storage image path from saved instance state
                    // otherwise the path will be null on device rotation

                    restoreFromBundle(savedInstanceState, INSTALLATION_CODE);
                } else {
                    requestCameraPermission(MEDIA_TYPE_IMAGE, INSTALLATION_CODE);
                }

            }
        });


        hose_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CameraUtils.checkPermissions(getContext())) {
                    captureImage(HOSE_CODE);
                    restoreFromBundle(savedInstanceState, HOSE_CODE);
                } else {
                    requestCameraPermission(MEDIA_TYPE_IMAGE, HOSE_CODE);
                }

            }
        });


        signature_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(getContext());
                View promptsView = li.inflate(R.layout.signature_capture_layout, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                userInput = (SignaturePad) promptsView.findViewById(R.id.signaturePad1);


                userInput.setOnSignedListener(new SignaturePad.OnSignedListener() {
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
                    public void onClear() {

                    }
                });

                // set dialog message
                alertDialogBuilder.setCancelable(false).setPositiveButton("Submit", new DialogInterface.OnClickListener()
                        {
                                    public void onClick(DialogInterface dialog,int id)
                                    {
                                        // get user input and set it to result
                                        // edit text
                                        signatureBitmap=userInput.getSignatureBitmap();
                                        signature_iv.setImageBitmap(signatureBitmap);
                                        DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(context);
                                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                        signatureBitmap.compress(Bitmap.CompressFormat.PNG, 60, byteArrayOutputStream);

                                        byte[] byteArray = byteArrayOutputStream .toByteArray();

                                        encoded4 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                                        //databaseHelperUser.setPhotos("Signature_",encoded,5);

                                        count++;

                                        //Log.i(Constants.TAG,userInput.getSignatureSvg());

                                        Toast.makeText(context, ""+userInput.getSignatureSvg(), Toast.LENGTH_SHORT).show();
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
              //  restoreFromBundle(savedInstanceState, SIGNATURE);

            }




               /* Signature fragment = new Signature();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.viewpager, fragment);

                fragmentTransaction.commit();
*/


                //IntentFilter intentFilter
                /*Signature signature=new Signature();
                FragmentManager fragmentManager = getFragmentManager();

                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.add(signature,"Signature");
                fragmentTransaction.show(signature);*/
                //fragmentTransaction.replace(R.id.viewpager,signature);


        });


        /*save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(hoseBitmap!=null && regulatorBitmap!=null && installationBitmap!=null && signatureBitmap!=null && stoveBitmap!=null)
                {
                    saveCapturedImage();

                }


                else
                    {
                    Toast.makeText(context, "Capturing All images is Mandatory!", Toast.LENGTH_SHORT).show();
                    }
                // new QuestionAdapter(getContext()).updateAnswer(Constants.UPLOAD_PHOTO_FRAG_CODE);
            }
        });
*/

        submit.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

              /*  boolean f1=sharedPreferences.getBoolean(Constants.CYLINDER_SAVE,false);
                boolean f2=sharedPreferences.getBoolean(Constants.REGULATOR_SAVE,false);
                boolean f3=sharedPreferences.getBoolean(Constants.RUBBER_HOSE_SAVE,false);
                boolean f4=sharedPreferences.getBoolean(Constants.STOVE_SAVE,false);
                boolean f5=sharedPreferences.getBoolean(Constants.GENERAL_SAVE,false);*/



                progressDialog.show();

                if(hoseBitmap!=null && regulatorBitmap!=null && installationBitmap!=null && signatureBitmap!=null && stoveBitmap!=null)
                {
                        //if(new MyGlobals(context).isNetworkConnected())
                    saveCapturedImage();

                    @SuppressLint("SimpleDateFormat")

                    DateFormat dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String dateString = dateFormat.format(date);

                    System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43

                    GPSTracker gps = new GPSTracker(context);
                    DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(context);
                    double latitude,longitude;
                    String allotted_id=sharedPreferences.getString(Constants.ALLOTED_ID,null);
                    //String inspection_id=sharedPreferences.getString(Constants.INSPECTION_ID,null);
                    String unique=sharedPreferences.getString(Constants.UNIQUE_CONSUMER_NO,null);

                        // Check if GPS enabled
                        //if(sharedPreferences.getInt(Constants.CYLINDER_SAVE,0)==1)
                        //if(gps.canGetLocation())
                        if(sharedPreferences.getInt(Constants.CYLINDER_SAVE,0)==1)
                        {
                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();
                        String instruction=instruction_editText.getText().toString();
                        // \n is for new line
                        Toast.makeText(context, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                        databaseHelperUser.putExtraAllotedUserData(allotted_id,"3528821"/*inspection_id*/,instruction,latitude,longitude);

                        String consumerInfo=databaseHelperUser.getConsumerJsonString(allotted_id,latitude,longitude,instruction,dateString);
                        String personalInfo=databaseHelperUser.getPersonalJsonString(unique);
                        String answerInfo=databaseHelperUser.getAnswerJsonString(unique);




                        Toast.makeText(context, ""+consumerInfo, Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, ""+personalInfo, Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, ""+answerInfo, Toast.LENGTH_SHORT).show();

                        boolean isSuccess=inspectionWebService(consumerInfo,personalInfo,answerInfo,allotted_id);

                        if(isSuccess)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(context, "Entry uploaded sucessfully!", Toast.LENGTH_SHORT).show();
                            databaseHelperUser.deleteAllTableEntries(allotted_id,uniqueNo);
                        }

                        else
                            {
                                Toast.makeText(context, "Exception! Internal error occured! Please try again ", Toast.LENGTH_SHORT).show();

                            }

                    }

                    else
                    {
                        if(progressDialog.isShowing())
                        progressDialog.dismiss();
                        // Can't get location.
                        // GPS or network is not enabled.
                        // Ask user to enable GPS/network in settings.
                        Toast.makeText(context, "Answer all mandatory fields!", Toast.LENGTH_SHORT).show();
                        //gps.showSettingsAlert();
                    }

                    if(progressDialog.isShowing())
                    progressDialog.dismiss();
                    //databaseHelperUser.putExtraAllotedUserData(instruction,latitude,longitude);
                }

                else
                {
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();

                    Toast.makeText(context, "Capturing All images is Mandatory!", Toast.LENGTH_SHORT).show();
                }





            }
        });




    }




    private void requestCameraPermission(final int type, final int CODE)
    {
        Dexter.withActivity(getActivity()).withPermissions(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {

            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report)
            {
                if (report.areAllPermissionsGranted()) {
                    if (type == MEDIA_TYPE_IMAGE) {
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
                        if (CODE == REGULATOR_CODE)
                            requestCameraPermission(MEDIA_TYPE_IMAGE, REGULATOR_CODE);

                        else if (CODE == STOVE_CODE)
                            requestCameraPermission(MEDIA_TYPE_IMAGE, REGULATOR_CODE);

                        else if (CODE == HOSE_CODE)
                            requestCameraPermission(MEDIA_TYPE_IMAGE, REGULATOR_CODE);

                        else if (CODE == INSTALLATION_CODE)
                            requestCameraPermission(MEDIA_TYPE_IMAGE, REGULATOR_CODE);
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
                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils.openSettings(getContext());
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putString(KEY_IMAGE_STORAGE_PATH, imageStoragePath);
        int mCurCheckPosition = 0;
        outState.putInt("curChoice", mCurCheckPosition);

    }



               public void setPhotoView(int ImageViewCode,String allottedId)
            {
                String imageString= new DatabaseHelperUser(context).getPhotoEntry(ImageViewCode,allottedId);
                Toast.makeText(getContext(), ""+imageString, Toast.LENGTH_SHORT).show();

                if(imageString!=null)
                {
                    byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            switch(ImageViewCode)
            {
                case REGULATOR_CODE:
                    regulator_iv.setImageBitmap(decodedByte);
                    break;
                case HOSE_CODE:
                    hose_iv.setImageBitmap(decodedByte);
                    break;
                case INSTALLATION_CODE:
                    installation_iv.setImageBitmap(decodedByte);
                    break;
                case STOVE_CODE:
                    stove_iv.setImageBitmap(decodedByte);
                    break;
            }
        }
    }




    public void restoreFromBundle(Bundle savedInstanceState, int CODE)
    {
        if (savedInstanceState != null)
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




    private int getCountOfCapturedImages()
    {
        return count;
    }

    private void saveCapturedImage()
    {

        imageArray=new ArrayList<>();
        fileNameArray=new ArrayList<>();
        imageByte=new ArrayList<>();


        DatabaseHelperUser databaseHelperUser = new DatabaseHelperUser(getContext());


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        regulatorBitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos); //bm is the bitmap object

        imageByte.add(baos.toByteArray());
        String encodedImage = Base64.encodeToString(imageByte.get(0), Base64.DEFAULT);
        Toast.makeText(getContext(), "" + encodedImage, Toast.LENGTH_SHORT).show();
        String filename = CameraUtils.getOutputMediaFile().getName();
        fileNameArray.add(filename);
        imageArray.add(encodedImage);



        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        stoveBitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos1); //bm is the bitmap object
        imageByte.add(baos1.toByteArray());
        String encodedImage1 = Base64.encodeToString(imageByte.get(1), Base64.DEFAULT);
        Toast.makeText(getContext(), "" + encodedImage, Toast.LENGTH_SHORT).show();
        String filename1 = CameraUtils.getOutputMediaFile().getName();
        fileNameArray.add(filename1);
        imageArray.add(encodedImage1);

        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        hoseBitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos2); //bm is the bitmap object
        imageByte.add(baos2.toByteArray());
        String encodedImage2 = Base64.encodeToString(imageByte.get(2), Base64.DEFAULT);
        Toast.makeText(getContext(), "" + encodedImage2, Toast.LENGTH_SHORT).show();
        String filename2 = CameraUtils.getOutputMediaFile().getName();
        fileNameArray.add(filename2);
        imageArray.add(encodedImage2);



        ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
        installationBitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos3); //bm is the bitmap object
        imageByte.add(baos3.toByteArray());
        String encodedImage3 = Base64.encodeToString(imageByte.get(3), Base64.DEFAULT);
        Toast.makeText(getContext(), "" + encodedImage, Toast.LENGTH_SHORT).show();
        String filename3 = CameraUtils.getOutputMediaFile().getName();
        fileNameArray.add(filename3);
        imageArray.add(encodedImage3);


        ByteArrayOutputStream baos4 = new ByteArrayOutputStream();
        signatureBitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos4); //bm is the bitmap object
        imageByte.add(baos4.toByteArray());
        String encodedImage4 = Base64.encodeToString(imageByte.get(4), Base64.DEFAULT);
        Toast.makeText(getContext(), "" + encodedImage, Toast.LENGTH_SHORT).show();
        String filename4 = CameraUtils.getOutputMediaFile().getName();
        fileNameArray.add(filename4);
        imageArray.add(encodedImage4);

        if(!new MyGlobals(context).isNetworkConnected()) {
            String[] CODE = {"1", "2", "3", "4", "5"};
            databaseHelperUser.setPhotos(fileNameArray, imageArray, CODE);
        }

    }


    //TODO Checkpoint Function

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
        switch (CODE)
        {
                case REGULATOR_CODE:
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File file = CameraUtils.getOutputMediaFile();
                if (file != null) {
                    imageStoragePath = file.getAbsolutePath();
                }

                Uri fileUri = CameraUtils.getOutputMediaFileUri(getContext(), file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                // start the image capture Intent
                startActivityForResult(intent, REGULATOR_CODE);
                break;

            case STOVE_CODE:
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File file1 = CameraUtils.getOutputMediaFile();
                if (file1 != null)
                {
                    imageStoragePath = file1.getAbsolutePath();
                }

                Uri fileUri1 = CameraUtils.getOutputMediaFileUri(getContext(), file1);
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, fileUri1);

                // start the image capture Intent
                startActivityForResult(intent1, STOVE_CODE);
                break;

            case HOSE_CODE:
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File file2 = CameraUtils.getOutputMediaFile();
                if (file2 != null) {
                    imageStoragePath = file2.getAbsolutePath();
                }

                Uri fileUri2 = CameraUtils.getOutputMediaFileUri(getContext(), file2);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, fileUri2);

                // start the image capture Intent
                startActivityForResult(intent2, HOSE_CODE);
                break;

            case INSTALLATION_CODE:
                Intent intent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file3 = CameraUtils.getOutputMediaFile();
                if (file3 != null)
                {
                    imageStoragePath = file3.getAbsolutePath();
                }


                Uri fileUri3 = CameraUtils.getOutputMediaFileUri(getContext(), file3);
                intent3.putExtra(MediaStore.EXTRA_OUTPUT, fileUri3);

                // start the image capture Intent
                startActivityForResult(intent3, INSTALLATION_CODE);
                break;

            case SIGNATURE:
                File file4 = CameraUtils.getOutputMediaFile();
                if (file4 != null) {
                    imageStoragePath = file4.getAbsolutePath();
                }

                Uri fileUri4 = CameraUtils.getOutputMediaFileUri(getContext(), file4);

                //intent1.putExtra(MediaStore.EXTRA_OUTPUT, fileUri4);

                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REGULATOR_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getContext(), imageStoragePath);
                // successfully captured the image
                // display it in image view
                previewCapturedImage(REGULATOR_CODE);
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
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
                Toast.makeText(getContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            }

            else
                {
                // failed to capture image
                Toast.makeText(getContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == INSTALLATION_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getContext(), imageStoragePath);
                // successfully captured the image
                // display it in image view
                previewCapturedImage(INSTALLATION_CODE);
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }

        } else if (requestCode == STOVE_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getContext(), imageStoragePath);
                // successfully captured the image
                // display it in image view
                previewCapturedImage(STOVE_CODE);
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }

        }




        else if (requestCode == Constants.SIGNATURE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getContext(), imageStoragePath);
                // successfully captured the image
                // display it in image view
                previewCapturedImage(STOVE_CODE);
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
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








    private boolean inspectionWebService(String consumerInfo,String personalInfo,String answerInfo,String allotedId)
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
                aQuery.ajax(url, JSONObject.class, 60000, new AjaxCallback<JSONObject>() {
                    public void callback(String url, JSONObject object, AjaxStatus status) {
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
                recordHashMap.put("PersonalInfo", personalInfo);
                recordHashMap.put("QuestionsInfo", answerInfo);

                if (fileNameArray.size() == 5) {

                    recordHashMap.put("Img1", fileNameArray.get(0));
                    recordHashMap.put("Img2", fileNameArray.get(1));
                    recordHashMap.put("Img3", fileNameArray.get(2));
                    recordHashMap.put("Img4", fileNameArray.get(3));
                    recordHashMap.put("Img5", fileNameArray.get(4));
                }



            if (imageByte.size() > 4)
            {
                recordHashMap.put("img1Byt", imageByte.get(0));
                recordHashMap.put("img2Byt", imageByte.get(1));
                recordHashMap.put("img3Byt", imageByte.get(2));
                recordHashMap.put("img4Byt", imageByte.get(3));
                recordHashMap.put("img5Byt", imageByte.get(4));
            }

                InspectionDataSoapHelper helper=new InspectionDataSoapHelper();

                for(int i=0;i<recordHashMap.size();i++)
                Log.d(Constants.TAG, "inspectionWebService LinkedHashMap : "+recordHashMap.get(i));
                String response=helper.getSoapRequest(context, Constants.NAMESPACE, Constants.METHOD_INSPECTION_DATA_PostFile, Constants.ServerUrl_Soap, Constants.SOAP_ACTION_INSPECTION_DATA, recordHashMap);


                Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();
                if(response==null)
                token=true;

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
