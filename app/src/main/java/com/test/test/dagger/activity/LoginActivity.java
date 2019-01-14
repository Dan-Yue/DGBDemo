package com.test.test.dagger.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.test.test.R;
import com.test.test.dagger.model.ICommonView;
import com.test.test.dagger.model.User;
import com.test.test.dagger.module.CommonModule;
import com.test.test.dagger.module.DaggerCommonCompnent;
import com.test.test.dagger.pretenter.LoginPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity implements ICommonView {

    @BindView(R.id.btn_login)
    Button btn;
    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        DaggerCommonCompnent.
                builder().
                commonModule(new CommonModule(this)).
                build().
                Inject(this);
    }


    @OnClick(R.id.btn_login)
    void login() {
        Log.d("*dagger","LoginActivity.login");
        presenter.login(new User());
    }

    @Override
    public Context getContext() {
        Log.d("*dagger","LoginActivity.getContext");
        return this;
    }
}
