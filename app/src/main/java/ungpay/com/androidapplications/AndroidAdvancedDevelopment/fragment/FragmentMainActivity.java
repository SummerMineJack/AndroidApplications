package ungpay.com.androidapplications.AndroidAdvancedDevelopment.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ungpay.com.androidapplications.R;

/**
 * Fragment的练习训练
 */
public class FragmentMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOpenOne;
    private Button btnOpenTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);
        getFragmentManager().beginTransaction().replace(R.id.fragment_root, Fragment1.newIntance(
                "activity调用了onCreate方法进行初始显示")).commit();
        initView();
    }

    private void initView() {
        btnOpenOne = findViewById(R.id.open_fragment_one);
        btnOpenTwo = findViewById(R.id.open_fragment_two);
        btnOpenTwo.setOnClickListener(this);
        btnOpenOne.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_fragment_one:
                getFragmentManager().beginTransaction().replace(R.id.fragment_root,
                        Fragment1.newIntance(
                                "我点击了按钮传递的值")).commit();
                break;
            case R.id.open_fragment_two:
                getFragmentManager().beginTransaction().replace(R.id.fragment_root,
                        new Fragment2()).commit();
                break;
        }
    }
}
