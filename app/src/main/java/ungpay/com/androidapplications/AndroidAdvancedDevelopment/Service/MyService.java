package ungpay.com.androidapplications.AndroidAdvancedDevelopment.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private String data;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e("~~~", "onBind");
        return new MyBind();
    }

    public class MyBind extends Binder {
        public void setData(String data) {
            MyService.this.data = data;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("~~~", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("~~~", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("~~~", "onDestroy");
    }
}
