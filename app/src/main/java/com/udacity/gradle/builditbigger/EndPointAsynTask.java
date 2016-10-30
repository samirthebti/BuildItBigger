package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.example.thesam.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by Samir Thebti  on 30/10/16.
 * ----->> thebtisam@gmail.com <<-----
 */

public class EndPointAsynTask extends AsyncTask<MainActivityFragment, Void, String> {
    private MyApi myApi = null;
    private Context context;
    private MainActivityFragment fragment;

    @Override
    protected String doInBackground(MainActivityFragment... params) {
        if (myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
                    AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbiiger.appspot.com/_ah/api/");
            myApi = builder.build();
        }
        fragment = params[0];

        context = fragment.getActivity();
        try {
            return myApi.sayJock().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        fragment.loadedJock = result;
        fragment.launchIntentJock();
    }

}