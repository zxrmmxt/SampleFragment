package com.xt.samplefragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xt on 2019/8/14 10:53
 * ViewPager+BasePageFragment
 * FragmentPagerAdapter继承PagerAdapter,通过名字，我们知道它专注于任意页为Fragment的情况。通过FragmentPagerAdapter文档所述，该类中每一个生成的Fragment都将保存在内存中，所以缺点非常明显，对于存在相对较多的fragment，程序将会吃掉非常多的内容。所以FragmentPagerAdapter适合那些相数量相对较少，静态的页面。对于存在多个fragment的情况，一般推荐使用FragmentStatePagerAdapter。
 */
public class ViewPagerFragmentSampleActivity extends AppCompatActivity {
    private ViewPager            mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private List<Fragment>       mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_fragment_sample);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshViewPager();
            }
        });
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPage();
            }
        });
        mViewPager = findViewById(R.id.viewPager);
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

        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_UNCHANGED;
            }

            @Override
            public long getItemId(int position) {
                //可以来源于数据,用以刷新ViewPager
                return position;
            }
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);

        refreshViewPager();
    }

    /**
     * 刷新viewpager
     */
    private void refreshViewPager() {
        {
            //|FragmentPagerAdapter|清除缓存的fragment,|FragmentStatePagerAdapter|不需要此代码
            FragmentManager     supportFragmentManager = getSupportFragmentManager();
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
            /*if (mFragmentPagerAdapter != null) {
                mFragmentPagerAdapter.notifyDataSetChanged();
                mFragmentPagerAdapter = null;
            }*/
        }

        for (int i = 0; i < 2; i++) {
            Fragment instantiate = instantiate(i);
            mFragments.add(instantiate);
        }
        mFragmentPagerAdapter.notifyDataSetChanged();
    }

    private void addPage() {
        int      position    = mFragments.size();
        Fragment instantiate = instantiate(position);
        mFragments.add(instantiate);
        mFragmentPagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(position);
    }

    private Fragment instantiate(int position) {
        Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate(getClassLoader(), SamplePageFragment.class.getName());
        Bundle   args     = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }
}
