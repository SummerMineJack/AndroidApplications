package ungpay.com.androidapplications;

import android.annotation.SuppressLint;
import android.app.Application;

import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initCrashInfo();
    }

    @SuppressLint("MissingPermission")
    private void initCrashInfo() {
        CrashUtils.init(new CrashUtils.OnCrashListener() {
            @Override
            public void onCrash(String crashInfo, Throwable e) {
                //app崩溃异常日志在这里处理，可以进行上传服务器
                LogUtils.e(crashInfo);
            }
        });
    }

}
