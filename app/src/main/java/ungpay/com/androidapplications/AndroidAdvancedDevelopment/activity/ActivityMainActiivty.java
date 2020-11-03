package ungpay.com.androidapplications.AndroidAdvancedDevelopment.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;

import ungpay.com.androidapplications.R;

/**
 * activity相关
 */
public class ActivityMainActiivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_actiivty);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            ToastUtils.showLong("用户名：" + savedInstanceState.getString("Username") + " 用户等级" + savedInstanceState.getString("Userlevel"));
        } else {
            ToastUtils.showLong("无值");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outPersistentState.putString("Username", "HuangJian");
        outPersistentState.putString("Userlevel", "1000");
        outState.putString("Username", "HuangJian");
        outState.putString("Userlevel", "1000");
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
