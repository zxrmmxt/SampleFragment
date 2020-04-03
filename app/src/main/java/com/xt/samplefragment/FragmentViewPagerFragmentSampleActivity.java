package com.xt.samplefragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xt on 2019/8/19 16:09
 * Activity+BaseFragment+ViewPager+BasePageFragment
 */
public class FragmentViewPagerFragmentSampleActivity extends AppCompatActivity {

    private List<Class<? extends Fragment>> mFragmentClassList;
    private View                            mFragmentContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view_pager_fragment_sample);

        mFragmentContainer = findViewById(R.id.fragmentContainer);
        findViewById(R.id.btn0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(0);
            }
        });
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(1);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(2);
            }
        });

        mFragmentClassList = new ArrayList<>();
        mFragmentClassList.add(FragmentWithViewPager.class);
        mFragmentClassList.add(SampleFragment.class);
        mFragmentClassList.add(SampleFragment.class);

        showFragment(0);
    }

    private void showFragment(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        FragmentUtils.showFragment(this, getSupportFragmentManager(), mFragmentContainer, position, mFragmentClassList, args);
    }
}
