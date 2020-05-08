package com.xt.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author xt on 2019/8/19 13:05
 * 1、Activity+ViewPager+BasePageFragment
 * 2、Activity+BaseFragment+ViewPager+BasePageFragment
 */
public abstract class BasePageFragment extends CommonFragment {
    private static final String  TAG          = BasePageFragment.class.getSimpleName();
    protected            View    mRootView;
    private              boolean mIsFirstLoad = true;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            initViews(mRootView);
        }
        return mRootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(View view);

    @Override
    public void onResume() {
        super.onResume();
        loadDataLogic();
    }

    /**
     * 如果我们使用的是FragmentPagerAdapter，
     * 切换导致Fragment被销毁时是不会执行onDestory()和onDetach()方法的，
     * 只会执行到onDestroyView()方法，因此在onDestroyView()方法中我们还需要将{@link #mIsFirstLoad}这个变量重置，
     * 否则当Fragment再次可见时就不会调用{@link #initData()}
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsFirstLoad = true;
    }

    private void loadDataLogic() {
        if (mIsFirstLoad) {
            mIsFirstLoad = false;
            initData();
        }
        updateData();
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
