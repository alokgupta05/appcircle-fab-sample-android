package com.example.appcircle_sample_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addFabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFabButton = findViewById(R.id.add_fab);
        addFabButton.setOnClickListener(view -> {
            startActivity(
                    FlutterActivity
                            .withCachedEngine("fab_engine_id")
                            .build(MainActivity.this)
            );
        });
        showHomeItem(true);
        SampleFragment newFragment = new SampleFragment();
        newFragment.setActivity(this);
        this.replaceFragment(newFragment, false);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.rootLayout, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void showHomeItem(boolean isEnabled) {
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(isEnabled);
        this.getSupportActionBar().setDisplayShowHomeEnabled(isEnabled);
    }

    public String getAppVersionName() {
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "NOT_FOUND";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
