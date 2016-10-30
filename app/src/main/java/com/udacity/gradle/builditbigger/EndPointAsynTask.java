package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.TellJock;
import com.example.thesam.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.thesam.me.passedjock.PassedJock;

import java.io.IOException;

/**
 * Created by Samir Thebti  on 30/10/16.
 * ----->> thebtisam@gmail.com <<-----
 */

public class EndPointAsynTask extends AsyncTask<Context, Void, String> {
    private MyApi myApi = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
        if (myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
                    AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbiiger.appspot.com/_ah/api/");
            myApi = builder.build();
        }
        context = params[0];
        try {
            return myApi.sayJock().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        TellJock tellJock = new TellJock();
        Intent jockIntent = new Intent(context, PassedJock.class);
        jockIntent.putExtra(context.getString(R.string.jockintent), tellJock.getJokes());
        context.startActivity(jockIntent);
    }
}