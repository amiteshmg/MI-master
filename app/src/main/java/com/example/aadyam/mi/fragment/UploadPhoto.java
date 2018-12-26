package com.example.aadyam.mi.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aadyam.mi.Utils.CameraUtils;
import com.example.aadyam.mi.database.DatabaseHelperUser;
import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import static android.support.v7.app.AppCompatActivity.RESULT_CANCELED;
import static android.support.v7.app.AppCompatActivity.RESULT_OK;
import static com.example.aadyam.mi.activity.MainActivity.IMAGE_EXTENSION;
import static com.example.aadyam.mi.activity.MainActivity.MEDIA_TYPE_IMAGE;


/**
 * A simple {@link Fragment} subclass.
 */


public class UploadPhoto extends Fragment
{
    private static String imageStoragePath;
    private static final int BITMAP_SAMPLE_SIZE = 8;
    private static final int REGULATOR_CODE = 1;
    private static final int STOVE_CODE = 2;
    private static final int HOSE_CODE = 3;
    private static final int INSTALLATION_CODE = 4;
    private Bundle bundle;
    private static final String KEY_IMAGE_STORAGE_PATH = "image_path";
    private ImageView stove_iv,regulator_iv,hose_iv,installation_iv,signature_iv;
    public static final String GALLERY_DIRECTORY_NAME = "MI_Images";

    Button submit,save;
    LinearLayout layout;
    SignaturePad signaturePad;
    private int mCurCheckPosition=0;


    public UploadPhoto()
    {
        // Required empty public constructor
    }








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upload_photo, container, false);
    }





    @Override
    public void onPause()
    {
        super.onPause();
        // Toast.makeText(getContext(), "onPause()", Toast.LENGTH_SHORT).show();

        //TODO enter code to save image to database in Base64 format conversion
    }




    @Override
    public void onStart() {
        super.onStart();

        Log.i("FRAGMENT LIFECYCLE","onStart()");
        //Toast.makeText(getContext(), "onStart()", Toast.LENGTH_SHORT).show();
    }









    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState)
    {

     //   submit=view.findViewById(R.id.button_next);
        submit.setText("Submit");
       // save=view.findViewById(R.id.button_save);

        super.onViewCreated(view, savedInstanceState);

        //signaturePad=view.findViewById(R.id.);


        new MyGlobals(getContext()).getJSON();
        this.bundle=savedInstanceState;
        stove_iv=view.findViewById(R.id.stove_iv);
        regulator_iv=view.findViewById(R.id.regulator_iv);
        hose_iv=view.findViewById(R.id.hose_iv);
        installation_iv=view.findViewById(R.id.installation_iv);
        signature_iv=view.findViewById(R.id.signature_iv);

        layout=view.findViewById(R.id.signature_layout);
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

                    restoreFromBundle(savedInstanceState,STOVE_CODE);
                }

                else
                {
                    requestCameraPermission(MEDIA_TYPE_IMAGE,STOVE_CODE);
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

                    restoreFromBundle(savedInstanceState,REGULATOR_CODE);
                }

                else
                {
                    requestCameraPermission(MEDIA_TYPE_IMAGE,REGULATOR_CODE);
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

                    restoreFromBundle(savedInstanceState,INSTALLATION_CODE);
                }
                else
                {
                    requestCameraPermission(MEDIA_TYPE_IMAGE,INSTALLATION_CODE);
                }

            }
        });


        hose_iv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                if (CameraUtils.checkPermissions(getContext()))
                {
                    captureImage(HOSE_CODE);
                    restoreFromBundle(savedInstanceState,HOSE_CODE);
                }


                else
                {
                    requestCameraPermission(MEDIA_TYPE_IMAGE,HOSE_CODE);
                }

            }
        });


       /* signature_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadPhoto fragment = new UploadPhoto();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.signature_layout, fragment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                //IntentFilter intentFilter
            }
        });*/



        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // new QuestionAdapter(getContext()).updateAnswer(Constants.UPLOAD_PHOTO_FRAG_CODE);
            }
        });
    }





    private void requestCameraPermission(final int type,final int CODE)
    {
        Dexter.withActivity(getActivity()).withPermissions(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {

            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report)
            {
                if (report.areAllPermissionsGranted())
                {
                    if (type == MEDIA_TYPE_IMAGE )
                    {
                        // capture regulator picture
                        if(CODE==REGULATOR_CODE)
                            captureImage(REGULATOR_CODE);

                            // capture stove picture
                        else if(CODE==STOVE_CODE)
                            captureImage(STOVE_CODE);

                            // capture hose picture
                        else if(CODE==HOSE_CODE)
                            captureImage(HOSE_CODE);

                            // capture installation picture
                        else if(CODE==INSTALLATION_CODE)
                            captureImage(INSTALLATION_CODE);
                    }


                    else
                    {
                        if(CODE==REGULATOR_CODE)
                            requestCameraPermission(MEDIA_TYPE_IMAGE,REGULATOR_CODE);

                        else if(CODE==STOVE_CODE)
                            requestCameraPermission(MEDIA_TYPE_IMAGE,REGULATOR_CODE);

                        else if(CODE==HOSE_CODE)
                            requestCameraPermission(MEDIA_TYPE_IMAGE,REGULATOR_CODE);

                        else if(CODE==INSTALLATION_CODE)
                            requestCameraPermission(MEDIA_TYPE_IMAGE,REGULATOR_CODE);
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putString(KEY_IMAGE_STORAGE_PATH, imageStoragePath);
        outState.putInt("curChoice", mCurCheckPosition);

    }





  /*  public void setPhotoView(int ImageViewCode)
    {

        String imageString= new DatabaseHelperUser(getContext()).getPhotoEntry(ImageViewCode);

        Toast.makeText(getContext(), ""+imageString, Toast.LENGTH_SHORT).show();
        if(imageString==null)
        {
            return;
        }
        else{


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
    }*/


    /**
     * Saving stored image path to saved instance state
     */



    /**
     * Restoring image path from saved instance state
     */

    //TODO : ERROR nullpointer
   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);
    }*/


    /*@Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);
    }*/



    public void restoreFromBundle(Bundle savedInstanceState,int CODE) {
        if (savedInstanceState != null)
        {
            if (savedInstanceState.containsKey(KEY_IMAGE_STORAGE_PATH))
            {
                imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);

                if (!TextUtils.isEmpty(imageStoragePath)) {
                    if (imageStoragePath.substring(imageStoragePath.lastIndexOf(".")).equals("." + IMAGE_EXTENSION) ) {
                        previewCapturedImage(CODE);
                    }
                }
            }
        }
    }


    private void previewCapturedImage(int CODE) {
        DatabaseHelperUser databaseHelperUser=new DatabaseHelperUser(getContext());

        try
        {
            //Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
            //imgPreview.setVisibility(View.VISIBLE);
            switch (CODE)
            {
                case REGULATOR_CODE:
                    Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
                    regulator_iv.setImageBitmap(bitmap);
                    //Bitmap bm = BitmapFactory.decodeFile(imageStoragePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                    Toast.makeText(getContext(), ""+encodedImage, Toast.LENGTH_SHORT).show();

                    String filename=CameraUtils.getOutputMediaFile().getName();

                    databaseHelperUser.setPhotos(filename,encodedImage, REGULATOR_CODE);



                    break;

                case STOVE_CODE:
                    Bitmap bitmap1 = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
                    stove_iv.setImageBitmap(bitmap1);
                    ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.JPEG, 60, baos1); //bm is the bitmap object
                    byte[] b1 = baos1.toByteArray();
                    String encodedImage1 = Base64.encodeToString(b1, Base64.DEFAULT);
                    Toast.makeText(getContext(), ""+encodedImage1, Toast.LENGTH_SHORT).show();

                    String filename1=CameraUtils.getOutputMediaFile().getName();
                    databaseHelperUser.setPhotos(filename1,encodedImage1, STOVE_CODE);


                    break;

                case HOSE_CODE:
                    Bitmap bitmap2 = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
                    hose_iv.setImageBitmap(bitmap2);
                    ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                    bitmap2.compress(Bitmap.CompressFormat.JPEG, 60, baos2); //bm is the bitmap object
                    byte[] b2 = baos2.toByteArray();
                    String encodedImage2 = Base64.encodeToString(b2, Base64.DEFAULT);
                    Toast.makeText(getContext(), ""+encodedImage2, Toast.LENGTH_SHORT).show();

                    String filename2=CameraUtils.getOutputMediaFile().getName();
                    databaseHelperUser.setPhotos(filename2,encodedImage2, HOSE_CODE);
                    break;

                case INSTALLATION_CODE:
                    Bitmap bitmap3 = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);
                    installation_iv.setImageBitmap(bitmap3);
                    ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
                    bitmap3.compress(Bitmap.CompressFormat.JPEG, 60, baos3); //bm is the bitmap object
                    byte[] b3 = baos3.toByteArray();
                    String encodedImage3 = Base64.encodeToString(b3, Base64.DEFAULT);
                    Toast.makeText(getContext(), ""+encodedImage3, Toast.LENGTH_SHORT).show();
                    String filename3=CameraUtils.getOutputMediaFile().getName();
                    databaseHelperUser.setPhotos(filename3,encodedImage3, INSTALLATION_CODE);
                    break;
            }

            // Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);








        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }



    private void captureImage(int CODE) {

        switch (CODE)
        {
            case REGULATOR_CODE:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File file = CameraUtils.getOutputMediaFile();
                if (file != null)
                {
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
                if (file1 != null) {
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
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REGULATOR_CODE)
        {
            if (resultCode == RESULT_OK )
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
            if (resultCode == RESULT_OK )
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

        }



        else if (requestCode == INSTALLATION_CODE)
        {
            if (resultCode == RESULT_OK )
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
                Toast.makeText(getContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            }




            else
            {
                // failed to capture image
                Toast.makeText(getContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }

        }



        else if (requestCode == STOVE_CODE)
        {
            if (resultCode == RESULT_OK )
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
                Toast.makeText(getContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            }



            else
            {
                // failed to capture image
                Toast.makeText(getContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }

}

