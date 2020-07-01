package ungpay.com.androidapplications;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ungpay.com.androidapplications.AndroidAdvancedDevelopment.Service.ServiceActivity;
import ungpay.com.androidapplications.AndroidAdvancedDevelopment.activity.ActivityMainActiivty;
import ungpay.com.androidapplications.AndroidAdvancedDevelopment.activity.MainActivitys;
import ungpay.com.androidapplications.AndroidAdvancedDevelopment.activity.TestActivity;
import ungpay.com.androidapplications.AndroidAdvancedDevelopment.fragment.FragmentMainActivity;
import ungpay.com.androidapplications.AndroidAdvancedDevelopment.handler.HandlerActivity;
import ungpay.com.androidapplications.MultiMedia.MediaMainActivity;
import ungpay.com.androidapplications.alipaycard.AlipayCardViewActivity;
import ungpay.com.androidapplications.ocr.OcrActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.MultiMedia_btn).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MediaMainActivity.class)));
        findViewById(R.id.handler_intent).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HandlerActivity.class)));
        findViewById(R.id.Fragment_btn).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FragmentMainActivity.class)));
        findViewById(R.id.activity_btn).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ActivityMainActiivty.class)));
        findViewById(R.id.service_btn).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ServiceActivity.class)));
        findViewById(R.id.Recy).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestActivity.class)));
        findViewById(R.id.step_view).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivitys.class)));
        findViewById(R.id.card_bank_view).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AlipayCardViewActivity.class)));
        findViewById(R.id.ocr_scan).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OcrActivity.class)));
    }
}