package com.xt.samplefragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void FragmentList(View view) {
        startActivity(new Intent(this, FragmentListSampleActivity.class));
    }

    public void FragmentNestList(View view) {
        startActivity(new Intent(this, FragmentNestListSampleActivity.class));
    }

    public void viewPagerFragment(View view) {
        startActivity(new Intent(this, ViewPagerFragmentSampleActivity.class));
    }
    public void fragmentViewPagerFragment(View view) {
        startActivity(new Intent(this, FragmentViewPagerFragmentSampleActivity.class));
    }
}
