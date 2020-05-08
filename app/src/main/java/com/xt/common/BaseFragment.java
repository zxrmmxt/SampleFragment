package com.xt.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;


/**
 * @author xuti on 2017/8/7.
 * 使用场景：add hide show进行fragment切换的时候,支持fragment嵌套
 * 1、Activity+BaseFragment
 * 2、Activity+BaseFragment+BaseFragment
 * 3、Activity+ViewPager+BasePageFragment
 * 4、Activity+BaseFragment+ViewPager+BasePageFragment
 */

public abstract class BaseFragment extends CommonFragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    protected            View   mRootView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (needInflateRootView()) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            initViews(mRootView);
            initData();
        }
        return mRootView;
    }

    protected boolean needInflateRootView() {
        return mRootView == null;
    }

    protected abstract int getLayoutId();

    protected void initViews(View view) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        //未嵌套fragment
        if (!isHidden()) {
            updateData();
            {
                //处理fragment嵌套的关键逻辑
                List<Fragment> fragments = getChildFragmentManager().getFragments();
                if (!fragments.isEmpty()) {
                    for (Fragment fragment : fragments) {
                        if (fragment instanceof BasePageFragment) {
                            if (fragment.isResumed()) {
                                fragment.onResume();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 使用场景：add hide show进行fragment切换的时候
     * 使用add hide() show()方法切换fragment  不会走任何的生命周期
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            updateData();

            //处理fragment嵌套的关键逻辑
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            if (!fragments.isEmpty()) {
                for (Fragment fragment : fragments) {
                    fragment.onHiddenChanged(fragment.isHidden());
                    if (fragment instanceof BasePageFragment) {
                        if (fragment.isResumed()) {
                            fragment.onResume();
                        }
                    }
                }
            }
        }


    }

    /**
     * 第一次显示才调用
     */
    protected abstract void initData();

    /**
     * 每一次显示都调用
     */
    protected abstract void updateData();
}
