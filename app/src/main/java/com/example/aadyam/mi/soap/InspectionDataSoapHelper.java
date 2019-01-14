package com.example.aadyam.mi.soap;

import android.content.Context;

import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

/*import com.hpgasmi.db.GenericDBControllerNew;
import com.hpgasmi.db.PersonalnfoDB;
import com.hpgasmi.db.PhotoDB;
import com.hpgasmi.db.QuestionAnswerDB;
import com.hpgasmi.user.navigationdrawersubmenu.LogAllModel;*/

import com.example.aadyam.mi.Utils.Constants;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;


public class InspectionDataSoapHelper
{


        private static final String TAG = "InspectionDataSoapHelper";
        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;
       /* ArrayList<LogAllModel> logArrayList = new ArrayList<LogAllModel>();
        ArrayList<LogAllModel> logArrayListException = new ArrayList<LogAllModel>();*/
        String currentDateandTime;


        private class AsyncCallWS extends AsyncTask<Void, Void, String> {

            String NAMESPACE;
            String METHOD_NAME;
            String URL;
            String SOAP_ACTION;
            LinkedHashMap<Byte, Byte> parameters;
            Context mContext;

            public AsyncCallWS(Context mContext, String NAMESPACE, String METHOD_NAME, String URL, String SOAP_ACTION, LinkedHashMap<Byte,
                    Byte> parameters) {
                this.mContext = mContext;
                this.NAMESPACE = NAMESPACE;
                this.METHOD_NAME = METHOD_NAME;
                this.URL = URL;
                this.SOAP_ACTION = SOAP_ACTION;
                this.parameters = parameters;
            }

            @Override
            protected String doInBackground(Void... params) {

                SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmssSS", Locale.ENGLISH);
                currentDateandTime = sdf.format(new Date());

                calculate(mContext, NAMESPACE, METHOD_NAME, URL, SOAP_ACTION, parameters);
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected void onProgressUpdate(Void... values) {
            }

        }

        public void calculate(Context context, String NAMESPACE, String METHOD_NAME, String URL, String SOAP_ACTION, LinkedHashMap<Byte, Byte> parameters) {


            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();

            try
            {

             /*   if (logArrayList.size() > 0) {
                    logArrayList.clear();
                }*/

                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
                addParameters(parameters, Request);

             /*
                try {
                    // added by Vinayak
                    LogAllModel logDetailModel = new LogAllModel();
                    logDetailModel.setLogPriority("");
                    logDetailModel.setLogFor("Inspection Data Service");
                    logDetailModel.setLogClass("InspectionDataSoapHelper");
                    logDetailModel.setLog_method("InspectionData");
                    logDetailModel.setLog_data(parameters.toString());
                    logDetailModel.setLog_status("");
                    logDetailModel.setLogSubmited("");
                    logDetailModel.setException("");
                    logDetailModel.setLogKey("");
                    logArrayList.add(logDetailModel);
                    System.out.println("logArrayList@@" + logArrayList);
                    new Logger(context).saveLog(logArrayList);

                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }


                */


                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;

                MarshalBase64 marshal = new MarshalBase64();
                marshal.register(soapEnvelope);

                soapEnvelope.setOutputSoapObject(Request);
                soapEnvelope.implicitTypes = true;
                HttpTransportSE transport = new HttpTransportSE(URL);
                transport.debug = true;
                transport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");

                transport.call(SOAP_ACTION, soapEnvelope);

                SoapPrimitive resultString = (SoapPrimitive) soapEnvelope.getResponse();

                System.out.println("InspectionData_Response" + resultString);

                String response = resultString.toString();


                sharedPreferences.edit().remove("flagSubmit").commit();  // upload photo fragment

/*
                if (!response.equalsIgnoreCase("Fail")) {
                    //Success
                    QuestionAnswerDB.getInstance(context).deleteRecord(response);
                    PersonalnfoDB.getInstance(context).deletePersonalInfo(response);
                    PhotoDB.getInstance(context).deletePhotos(response);

                }*/

            } catch (Exception ex) {

                /*if (logArrayListException.size() > 0) {
                    logArrayListException.clear();*/
                }

             /*   try {
                    // added by Vinayak
                    LogAllModel logDetailModel = new LogAllModel();
                    logDetailModel.setLogPriority("");
                    logDetailModel.setLogFor("Exception");
                    logDetailModel.setLogClass("InspectionDataSoapHelper");
                    logDetailModel.setLog_method("InspectionData");
                    logDetailModel.setLog_data(parameters.toString());
                    logDetailModel.setLog_status("");
                    logDetailModel.setLogSubmited("");
                    logDetailModel.setException(ex.toString());
                    logDetailModel.setLogKey("");

                    logArrayListException.add(logDetailModel);

                    new Logger(context).saveLog(logArrayListException);

                } catch (Exception e) {

                    e.printStackTrace();
                }*/
            }


        //

        public String getSoapRequest(Context context, String NAMESPACE, String METHOD_NAME, String URL, String SOAP_ACTION, LinkedHashMap<Byte, Byte> parameters) {
           // int event = 0;
            String response = "";
            AsyncCallWS task = new AsyncCallWS(context, NAMESPACE, METHOD_NAME, URL, SOAP_ACTION, parameters);
            try {
                response = task.execute().get();
            }

            catch (InterruptedException e)
            {
                Log.d(Constants.TAG, "getSoapRequest: "+e.toString());
                e.printStackTrace();
            }

            catch (ExecutionException e)
            {
                Log.d(Constants.TAG, "getSoapRequest: "+e.toString());
                e.printStackTrace();
            }

            return response;

        }





        private void addParameters(LinkedHashMap<Byte, Byte> parameters, SoapObject request)
        {
            Iterator<Entry<Byte, Byte>> iterator = parameters.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<Byte, Byte> entry = iterator.next();
                request.addProperty(String.valueOf(entry.getKey()), entry.getValue());
            }
        }

    }

