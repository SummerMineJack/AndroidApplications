package ungpay.com.androidapplications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import ungpay.com.androidapplications.AndroidAdvancedDevelopment.Service.ServiceActivity;
import ungpay.com.androidapplications.AndroidAdvancedDevelopment.activity.ActivityMainActiivty;
import ungpay.com.androidapplications.AndroidAdvancedDevelopment.activity.MainActivitys;
import ungpay.com.androidapplications.AndroidAdvancedDevelopment.activity.TestActivity;
import ungpay.com.androidapplications.AndroidAdvancedDevelopment.fragment.FragmentMainActivity;
import ungpay.com.androidapplications.AndroidAdvancedDevelopment.handler.HandlerActivity;
import ungpay.com.androidapplications.MultiMedia.MediaMainActivity;
import ungpay.com.androidapplications.alipaycard.AlipayCardViewActivity;
import ungpay.com.androidapplications.customcard.CustomAlipayCardActivity;
import ungpay.com.androidapplications.databinding.ActivityMainActiivtyBinding;
import ungpay.com.androidapplications.databinding.ActivityMainBinding;
import ungpay.com.androidapplications.ocr.OcrActivity;
import ungpay.com.androidapplications.retrofit.RetrofitRequestActivity;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding=ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(activityMainBinding.getRoot());
        activityMainBinding.MultiMediaBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MediaMainActivity.class)));
        activityMainBinding.handlerIntent.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HandlerActivity.class)));
        activityMainBinding.FragmentBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FragmentMainActivity.class)));
        activityMainBinding.activityBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ActivityMainActiivty.class)));
        activityMainBinding.serviceBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ServiceActivity.class)));
        activityMainBinding.Recy.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TestActivity.class)));
        activityMainBinding.stepView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivitys.class)));
        activityMainBinding.cardBankView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AlipayCardViewActivity.class)));
        activityMainBinding.ocrScan.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OcrActivity.class)));
        activityMainBinding.retrofitNetworkRequest.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RetrofitRequestActivity.class)));
        activityMainBinding.customAlipayCard.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CustomAlipayCardActivity.class)));
    }
}