package ungpay.com.androidapplications.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import ungpay.com.androidapplications.R;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener,
        Handler.Callback {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        initView();
    }

    private void initView() {
        handler = new Handler(this);
        findViewById(R.id.create_looper_for_ui).setOnClickListener(this);
        findViewById(R.id.create_looper_for_work).setOnClickListener(this);
        findViewById(R.id.create_looper_for_work_two).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_looper_for_ui:
                createLooperOnUi();
                break;
            case R.id.create_looper_for_work:
                createLooperForWorkThreadForOneWay();
                break;
            case R.id.create_looper_for_work_two:
                createLooperForWorkThreadForTwoWay();
                break;
        }
    }

    /**
     * 接受looper管理的MessageQueue消息队列获取的消息进行处理
     *
     * @param message
     * @return
     */
    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                ToastUtils.showLong("在子线程中使用ui线程的handler发送消息的提示");
                break;
        }
        return false;
    }

    /**
     * 新建一个子线程进行测试
     * 在子线程中进行ui操作 Toast提示 第二种是通过ui线程的looper进行提示Toast，那么如何发送到ui线程？
     * 使用Handler发送消息
     */
    private void createLooperForWorkThreadForTwoWay() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //通过ui线程中的handler进行发送
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    /**
     * 新建一个子线程进行测试
     * 在子线程中进行ui操作 Toast提示 第一种在子线程中操作ui线程的方法O
     */
    private void createLooperForWorkThreadForOneWay() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                ToastUtils.showLong("这个Toast提示是子线程中提示的哦");
                Looper.loop();
            }
        }).start();
    }

    /**
     * 尝试在ui线程中创建looper
     * 将会抛出异常 RuntimeException
     * 为什么会抛异常？
     * 因为在主线程中也就是当前的ui线程中已经创建了looper，每一个线程有且仅只能有一个looper实例，
     * 每个looper实例可以管理多个Handler 如:handler1,handler2
     */
    private void createLooperOnUi() {
        try {
            Looper.prepare();
        } catch (Exception e) {
            Log.e("~~~~异常", e.toString());
        }

    }


}
