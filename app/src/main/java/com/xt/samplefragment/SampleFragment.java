package com.xt.samplefragment;

import android.widget.Toast;

import java.util.Locale;

/**
 * @author xt on 2019/11/6 11:18
 */
public class SampleFragment extends BaseFragment {
    private static final String TAG = SampleFragment.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    /**
     * 第一次显示才调用
     */
    @Override
    protected void initData() {
        showToast(String.format(Locale.getDefault(), "%s%s%d%s", "初始化", TAG, getArguments().getInt("position"), "数据"));
    }

    /**
     * 每一次显示都调用
     */
    @Override
    protected void updateData() {
        showToast(String.format(Locale.getDefault(), "%s%s%d%s", "刷新", TAG, getArguments().getInt("position"), "数据"));
    }

    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
