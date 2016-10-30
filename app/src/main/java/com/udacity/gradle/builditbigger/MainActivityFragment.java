package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.TellJock;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.thesam.me.passedjock.PassedJock;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public String loadedJock = null;
    private ProgressBar progressBar;
    private Button tellJockBtn;
    public boolean testFlag = false;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        progressBar = (ProgressBar) root.findViewById(R.id.joke_progressbar);
        tellJockBtn = (Button) root.findViewById(R.id.telljock);
        tellJockBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                tellJock();
            }
        });

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    // void to launch asyntask activity
    private void tellJock() {
        new EndPointAsynTask().execute(this);
    }

    public void launchIntentJock() {
        if (!testFlag) {
            TellJock tellJock = new TellJock();
            Intent jockIntent = new Intent(getActivity(), PassedJock.class);
            jockIntent.putExtra(getActivity().getString(R.string.jockintent), tellJock.getJokes());
            getActivity().startActivity(jockIntent);
            progressBar.setVisibility(View.GONE);
        }
    }
}
