package com.udacity.gradle.builditbigger.free;


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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.thesam.me.passedjock.PassedJock;
import com.udacity.gradle.builditbigger.EndPointAsynTask;
import com.udacity.gradle.builditbigger.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {
    public String loadedJock = null;
    private ProgressBar progressBar;
    private Button tellJockBtn;
    public boolean testFlag = false;
    PublisherInterstitialAd mPublisherInterstitialAd = null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mPublisherInterstitialAd = new PublisherInterstitialAd(getActivity());
        mPublisherInterstitialAd.setAdUnitId("ca-app-pub-7867604826748291/8122977163");

        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                progressBar.setVisibility(View.VISIBLE);
                tellJock();
                requestNewInterstitial();

            }
        });
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mPublisherInterstitialAd.show();
            }
        });
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                requestNewInterstitial();
            }
        });


        requestNewInterstitial();
        tellJockBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPublisherInterstitialAd.isLoaded()) {
                    mPublisherInterstitialAd.show();

                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    tellJock();
                }

            }
        });
        requestNewInterstitial();
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        progressBar = (ProgressBar) root.findViewById(R.id.joke_progressbar);
        tellJockBtn = (Button) root.findViewById(R.id.telljock);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        return root;
    }

    private void requestNewInterstitial() {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice("076017F1CEF87E5F7E45ECA6F5ACBABF")
                .build();

        mPublisherInterstitialAd.loadAd(adRequest);
    }

    // void to launch asyntask activity
    private void tellJock() {
        new EndPointAsynTask().execute((Runnable) this);
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