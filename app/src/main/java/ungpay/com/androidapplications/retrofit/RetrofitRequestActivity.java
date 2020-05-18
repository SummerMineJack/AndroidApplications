package ungpay.com.androidapplications.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import retrofit2.Retrofit;
import ungpay.com.androidapplications.R;

public class RetrofitRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_request);
        findViewById(R.id.request).setOnClickListener(v -> {

        });
    }

    private void requestNetworkApi(){

    }
}
