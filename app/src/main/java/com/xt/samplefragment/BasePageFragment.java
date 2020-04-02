package com.xt.samplefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author xt on 2019/8/19 13:05
 * 1、ViewPager+BasePageFragment
 * 1、ViewPager+BasePageFragment
 */
public abstract class BasePageFragment extends CommonFragment {
    private static final String  TAG = BasePageFragment.class.getSimpleName();
    protected            View    mRootView;
    private              boolean isInitData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            initViews(mRootView);
            loadDataLogic();
        }
        return mRootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(View view);

    /**
     * 使用场景：当fragment结合viewpager使用的时候 这个方法会调用
     * 这个方法是在oncreateView之前使用 不要使用控件
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mRootView == null) {
            return;
        }
        loadDataLogic();
    }

    private void loadDataLogic() {
        if (getUserVisibleHint()) {
            if (!isInitData) {
                isInitData = true;
                initData();
            }
            updateData();
        }
    }

    /**
     * 第一次显示才调用
     */
    public abstract void initData();

    /**
     * 每一次显示都调用
     */
    public abstract void updateData();
}
