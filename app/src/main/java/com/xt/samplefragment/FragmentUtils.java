package com.xt.samplefragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

/**
 * @author xt on 2020/4/2 15:43
 */
public class FragmentUtils {
    public static FragmentManager getSupportFragmentManager(@NonNull FragmentActivity fragmentActivity) {
        return fragmentActivity.getSupportFragmentManager();
    }

    public static Fragment addFragment(Context context, @NonNull FragmentManager fragmentManager, Class<? extends Fragment> fragmentClass, int containerViewId, String tag, Bundle args) {
        Fragment            fragment = Fragment.instantiate(context, fragmentClass.getName(), args);
        FragmentTransaction ft       = fragmentManager.beginTransaction();
        ft.add(containerViewId, fragment, tag).commitAllowingStateLoss();
        return fragment;
    }

    private static FragmentTransaction beginTransaction(FragmentManager fragmentManager) {
        return fragmentManager.beginTransaction();
    }

    public static Fragment hideFragment(FragmentManager fragmentManager, @NonNull Fragment fragment) {
        if (fragment.isHidden()) {
            return fragment;
        }
        beginTransaction(fragmentManager).hide(fragment).commitAllowingStateLoss();
        return fragment;
    }

    private static Fragment showFragment(FragmentManager fragmentManager, @NonNull Fragment fragment) {
        if (fragment.isHidden()) {
            beginTransaction(fragmentManager).show(fragment).commitAllowingStateLoss();
        }
        return fragment;
    }


    public static void showFragment(Context context, @NonNull FragmentManager fragmentManager, View container, int position, List<Class<? extends Fragment>> fragmentClassList, Bundle args) {
        int containerViewId = container.getId();
        for (int i = 0; i < fragmentClassList.size(); i++) {
            String   tagPrefix = fragmentClassList.get(i).getSimpleName();
            Fragment fragment  = fragmentManager.findFragmentByTag(tagPrefix + i);
            if (fragment != null) {
                if (i != position) {
                    hideFragment(fragmentManager, fragment);
                }
            }
        }
        {
            String   tagPrefix    = fragmentClassList.get(position).getSimpleName();
            String   showTag      = tagPrefix + position;
            Fragment showFragment = fragmentManager.findFragmentByTag(showTag);
            if (showFragment == null) {
                showFragment = addFragment(context, fragmentManager, fragmentClassList.get(position), containerViewId, showTag, args);
            }
            showFragment(fragmentManager, showFragment);
        }
    }

    public static Fragment findFragment(int position, Class<? extends Fragment> fragmentClass, FragmentManager fragmentManager) {
        String tagPrefix = fragmentClass.getSimpleName();
        return fragmentManager.findFragmentByTag(tagPrefix + position);
    }
}
