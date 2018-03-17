package com.xianwei.movies.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by xianwei li on 3/17/2018.
 */

public abstract class BaseFragment extends Fragment implements BaseView {
    public BaseActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpDagger();
        setUpPresenter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.activity = activity;
            activity.onFragmentAttached();
            setCallbackListener(context);

        }
    }

    @Override
    public void onDetach() {
        activity = null;
        super.onDetach();
    }

    @Override
    public void hideKeyboard() {
        if (activity != null) {
            activity.hideKeyboard();
        }
    }

    public BaseActivity getBaseActivity() {
        return activity;
    }

    public abstract void setCallbackListener(Context context);

    public interface Callback {
        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
