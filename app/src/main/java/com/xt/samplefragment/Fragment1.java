package com.xt.samplefragment;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xt on 2019/8/19 16:50
 */
public class Fragment1 extends SampleFragment {
    private List<Class<? extends Fragment>> mFragmentClassList;
    private View                            mFragmentContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_1;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mFragmentContainer = view.findViewById(R.id.fragmentContainer);
        view.findViewById(R.id.btn0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(0);
            }
        });
        view.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(1);
            }
        });
        view.findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(2);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mFragmentClassList = new ArrayList<>();
        mFragmentClassList.add(ChildFragment.class);
        mFragmentClassList.add(ChildFragment.class);
        mFragmentClassList.add(ChildFragment.class);

        showFragment(0);
    }

    private void showFragment(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        FragmentUtils.showFragment(getContext(), getChildFragmentManager(), mFragmentContainer, position, mFragmentClassList, args);
    }
}
