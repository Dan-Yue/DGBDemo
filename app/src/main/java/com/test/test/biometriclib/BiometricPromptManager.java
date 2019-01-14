package com.test.test.biometriclib;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;


/**
 * Created by gaoyang on 2018/06/19.
 */
public class BiometricPromptManager {
    //1.分别根据Api版本来实例化两个类
    private IBiometricPromptImpl mImpl;
    private Activity mActivity;

    @RequiresApi(api = 28)
    public static BiometricPromptManager from(Activity activity) {
        return new BiometricPromptManager(activity);
    }

    @RequiresApi(api = 28)
    public BiometricPromptManager(Activity activity) {
        mActivity = activity;
        if (isAboveApi28()) {
            mImpl = new BiometricPromptApi28(activity);
        } else if (isAboveApi23()) {
            mImpl = new BiometricPromptApi23(activity);
        }
    }

    //2.判断版本号的办法
    private boolean isAboveApi28() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    private boolean isAboveApi23() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    //3.声明了一个接口IBiometricPromptImpl，Api28和Api23的实例都要继承
    public interface IBiometricPromptImpl {

        void authenticate(@NonNull CancellationSignal cancel,
                          @NonNull BiometricPromptManager.OnBiometricIdentifyCallback callback);

    }

    //4.对于系统是否支持指纹识别的判断
    //（1）isAboveApi23():是否高于23，Android高于23集成了指纹识别
    //（2）isHardwareDetected():这是用来判断系统硬件是否支持指纹识别，这里也是分情况判断，
    //但是AndroidP还不知道用什么确切的办法来判断，所以暂时用与AndroidM一样的方式。
    //Api23的具体实现在实现类中，后续你会看到。
    //（3）hasEnrolledFingerprints():这个方法是用来判断你的设备在系统设置里面是否设置了指纹。
    //如果用户没有设置，这时候你可以引导他去设置。
    //如果要引导的话，引导到安全设置页面就可以了，安全设置页面系统有统一的Intent，是【Settings.ACTION_SECURITY_SETTINGS】
    //（4）isKeyguardSecure():这个方法是判断系统有没有设置锁屏。
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isBiometricPromptEnable() {
        return isAboveApi23()
                && isHardwareDetected()
                && hasEnrolledFingerprints()
                && isKeyguardSecure();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isHardwareDetected() {
        if (isAboveApi28()) {
            //TODO 这是Api23的判断方法，也许以后有针对Api28的判断方法
            final FingerprintManager fm = mActivity.getSystemService(FingerprintManager.class);
            return fm != null && fm.isHardwareDetected();
        } else if (isAboveApi23()) {
            return ((BiometricPromptApi23) mImpl).isHardwareDetected();
        } else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean hasEnrolledFingerprints() {
        if (isAboveApi28()) {
            //TODO 这是Api23的判断方法，也许以后有针对Api28的判断方法
            final FingerprintManager manager = mActivity.getSystemService(FingerprintManager.class);
            return manager != null && manager.hasEnrolledFingerprints();
        } else if (isAboveApi23()) {
            return ((BiometricPromptApi23) mImpl).hasEnrolledFingerprints();
        } else {
            return false;
        }
    }

    public boolean isKeyguardSecure() {
        KeyguardManager keyguardManager = (KeyguardManager) mActivity.getSystemService(Context.KEYGUARD_SERVICE);
        if (keyguardManager.isKeyguardSecure()) {
            return true;
        }

        return false;
    }

    //5.关键方法authenticate鉴定方法
    public void authenticate(@NonNull OnBiometricIdentifyCallback callback) {
        mImpl.authenticate(new CancellationSignal(), callback);
    }

    public void authenticate(@NonNull CancellationSignal cancel,
                             @NonNull OnBiometricIdentifyCallback callback) {
        mImpl.authenticate(cancel, callback);
    }

    //6.相关回调方法，供入口处回调使用
    public interface OnBiometricIdentifyCallback {
        void onUsePassword();

        void onSucceeded();

        void onFailed();

        void onError(int code, String reason);

        void onCancel();
    }
}
