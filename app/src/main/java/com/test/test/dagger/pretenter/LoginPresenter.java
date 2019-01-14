package com.test.test.dagger.pretenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.test.test.dagger.model.ICommonView;
import com.test.test.dagger.model.User;

import javax.inject.Inject;

public class LoginPresenter {
    ICommonView iView;

    @Inject
    public LoginPresenter(ICommonView iView) {
        Log.d("*dagger","LoginPresenter.LoginPresenter");
        this.iView = iView;
    }

    public void login(User user) {
        Log.d("*dagger","LoginPresenter.login");
        Context mContext = iView.getContext();
        Toast.makeText(mContext, "login......", Toast.LENGTH_SHORT).show();
    }
}
