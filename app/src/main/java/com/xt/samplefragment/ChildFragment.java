package com.xt.samplefragment;

import java.util.Locale;

/**
 * @author xt on 2019/8/19 16:56
 */
public class ChildFragment extends SampleFragment {
    /**
     * 第一次显示才调用
     */
    @Override
    protected void initData() {
        showToast(String.format(Locale.getDefault(), "%s%d%s", "ChildFragment初始化界面", getArguments().getInt("position"), "数据"));
    }

    /**
     * 每一次显示都调用
     */
    @Override
    protected void updateData() {
        showToast(String.format(Locale.getDefault(), "%s%d%s", "ChildFragment刷新界面", getArguments().getInt("position"), "数据"));
    }
}
