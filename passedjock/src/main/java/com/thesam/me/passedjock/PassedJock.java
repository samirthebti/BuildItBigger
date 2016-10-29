package com.thesam.me.passedjock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PassedJock extends AppCompatActivity {
    private String jock;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passed_jock);
        Intent intent = getIntent();
        jock = intent.getStringExtra(getString(R.string.jockintent));
        if (!jock.isEmpty() || jock != null) {
            textView = (TextView) findViewById(R.id.jock_text_view);
            textView.setText(jock);
        }
    }
}
