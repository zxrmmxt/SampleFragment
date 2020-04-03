package com.xt.samplefragment;

import android.view.View;
import android.widget.Toast;

import java.util.Locale;

/**
 * @author xt on 2019/11/6 11:10
 */
public class SamplePageFragment extends BasePageFragment {
    private static final String TAG = SamplePageFragment.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_page;
    }

    @Override
    protected void initViews(View view) {

    }

    /**
     * 第一次显示才调用
     */
    @Override
    public void initData() {
        showToast(String.format(Locale.getDefault(), "%s%s%d%s", "初始化", TAG, getArguments().getInt("position"), "数据"));
    }

    /**
     * 每一次显示都调用
     */
    @Override
    public void updateData() {
        showToast(String.format(Locale.getDefault(), "%s%s%d%s", "刷新", TAG, getArguments().getInt("position"), "数据"));
    }

    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
