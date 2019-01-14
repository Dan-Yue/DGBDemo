package com.test.test.dagger.module;

import android.util.Log;

import com.test.test.dagger.model.ICommonView;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModule {

    private ICommonView iView;

    public CommonModule(ICommonView iView) {
        Log.d("*dagger","CommonModule.CommonModule");
        this.iView = iView;
    }

    @Provides
    @ActivityScope
    public ICommonView provideIcommonView() {
        Log.d("*dagger","CommonModule.provideIcommonView");
        return this.iView;
    }
}
