package com.sharkBytesLab.myPlanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sharkBytesLab.myPlanner.databinding.ActivityTermsBinding;

public class TermsActivity extends AppCompatActivity {

    private ActivityTermsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTermsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportActionBar().hide();

        try {

            binding.termsWebView.getSettings().setJavaScriptEnabled(true);
            binding.termsWebView.loadUrl("file:///android_asset/terms.html");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TermsActivity.this, MenuActivity.class));
        finish();
    }
}