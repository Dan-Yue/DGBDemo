package com.test.test.dagger.module;

import com.test.test.dagger.activity.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(modules = CommonModule.class)
public interface CommonCompnent {
    void Inject(LoginActivity activity);
}
