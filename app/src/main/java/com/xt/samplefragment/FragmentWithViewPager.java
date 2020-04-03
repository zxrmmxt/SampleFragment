package com.xt.samplefragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xt on 2019/11/5 11:09
 */
public class FragmentWithViewPager extends SampleFragment {
    private ViewPager            mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private List<Fragment>       mFragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_with_view_pager;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshViewPager();
            }
        });
        view.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPage();
            }
        });
        mViewPager = view.findViewById(R.id.viewPager);
        //设置缓存page的个数 2*n+1;
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mFragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_UNCHANGED;
            }

            @Override
            public long getItemId(int position) {
                //可以来源于数据,用以刷新ViewPager
                return super.getItemId(position);
            }
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        refreshViewPager();
    }

    @Override
    protected void updateData() {
        super.updateData();
    }

    /**
     * 刷新viewpager
     */
    private void refreshViewPager() {
        {
            //|FragmentPagerAdapter|清除缓存的fragment,|FragmentStatePagerAdapter|不需要此代码
            FragmentManager     supportFragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction    = supportFragmentManager.beginTransaction();
            for (Fragment fragment : mFragments) {
                fragmentTransaction.remove(fragment);
            }
            fragmentTransaction.commitAllowingStateLoss();
            supportFragmentManager.executePendingTransactions();
        }

        {
            mFragments.clear();
            mFragmentPagerAdapter.notifyDataSetChanged();
        }

        for (int i = 0; i < 2; i++) {
            mFragments.add(instantiate(i));
        }
        mFragmentPagerAdapter.notifyDataSetChanged();
    }

    private void addPage() {
        int position = mFragments.size();
        mFragments.add(instantiate(position));
        mFragmentPagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(position);
    }

    private Fragment instantiate(int position) {
        Fragment fragment = getChildFragmentManager().getFragmentFactory().instantiate(getActivity().getClassLoader(), SamplePageFragment.class.getName());
        Bundle   args     = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }
}
