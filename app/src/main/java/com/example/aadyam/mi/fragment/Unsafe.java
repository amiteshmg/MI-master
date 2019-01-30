package com.example.aadyam.mi.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.aadyam.mi.Global.MyGlobals;
import com.example.aadyam.mi.R;
import com.example.aadyam.mi.Utils.Constants;
import com.example.aadyam.mi.model.QuestionList;
import com.example.aadyam.mi.model.RetrofitError;
import com.example.aadyam.mi.model.UnsafeQuestion;
import com.example.aadyam.mi.rest.ApiClient;
import com.example.aadyam.mi.rest.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Unsafe extends Fragment
{
    LinearLayout unsafeLayout;
    List<QuestionList> unsafeList;
    SharedPreferences sharedPreferences;

    public Unsafe()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_unsafe, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        unsafeLayout=view.findViewById(R.id.unsafe_linear_layout);
        getUnsafeQuestions();

       // new MyGlobals(getContext()).dynamicQuestion(unsafeLayout,getContext(),view,unsafeList);
    }

    public void getUnsafeQuestions()
    {
        List<QuestionList> questionList;
        ApiInterface apiInterface;
        apiInterface= ApiClient.getClient().create(ApiInterface.class);


        sharedPreferences=getActivity().getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);

        Call<UnsafeQuestion> call = apiInterface.getUnsafeQuestionDetails(sharedPreferences.getInt(Constants.INSPECTION_ID,0));


        call.enqueue(new Callback<UnsafeQuestion>()
        {
            @Override
            public void onResponse(@NonNull Call<UnsafeQuestion> call, @NonNull Response<UnsafeQuestion> response)
            {
                /*ProgressDialog progressDialog=new ProgressDialog(getContext());
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
*/

                if (response.code() == 404)
                {
                    Gson gson = new GsonBuilder().create();
                    RetrofitError pojo;
                    try
                    {
                        pojo = gson.fromJson(response.errorBody().string(), RetrofitError.class);
                        Toast.makeText(getContext(), pojo.getInfo(), Toast.LENGTH_LONG).show();
                    }

                    catch (IOException e)
                    {
                        Toast.makeText(getContext(), "IOException Caught!", Toast.LENGTH_SHORT).show();
                    }
                }

                else if(response.code()==200)
                {
                    assert response.body() != null;
                    List<QuestionList> list = response.body().getQuestionList();
                    MyGlobals myGlobals = new MyGlobals(getContext());
                    //progressDialog.dismiss();
                    myGlobals.dynamicQuestion(unsafeLayout, getContext(), list, true);
                }
            }


            @Override
            public void onFailure(@NonNull Call<UnsafeQuestion> call, @NonNull Throwable t)
            {
                Log.d("ERROR",t.getMessage());
                Toast.makeText(getContext(), "no internet!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
