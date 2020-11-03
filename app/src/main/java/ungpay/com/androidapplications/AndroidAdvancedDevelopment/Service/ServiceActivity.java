package ungpay.com.androidapplications.AndroidAdvancedDevelopment.Service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ungpay.com.androidapplications.R;

/**
 * Android——Service相关
 */
public class ServiceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        findViewById(R.id.StartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(ServiceActivity.this, MyService.class));
            }
        });
        findViewById(R.id.StopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(ServiceActivity.this, MyService.class));
            }
        });
        findViewById(R.id.bindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(ServiceActivity.this, MyService.class), myConnection
                        , BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.unbindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(myConnection);
            }
        });
    }

    /**
     * 该接口是可以监听服务开启或关闭的状态信息
     */
    private ServiceConnection myConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("~~~~~~~~~~~~", "onServiceConnected");
            MyService.MyBind binder= (MyService.MyBind) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("~~~~~~~~~~~~", "onServiceDisconnected");
        }
    };
}
