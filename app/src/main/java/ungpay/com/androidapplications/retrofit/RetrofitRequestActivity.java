package ungpay.com.androidapplications.retrofit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import ungpay.com.androidapplications.R;

public class RetrofitRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_request2);
        findViewById(R.id.btn_request_api).setOnClickListener(v -> requestApi());
    }

    private void requestApi() {
        AppLoginApi.userLogin(new ApiCallBack(this) {
            @Override
            public void onSuccess(Call call, ResultBean result) {

            }

            @Override
            public void onFailure(Call call, String message) {

            }

            @Override
            public void onUpDate(Call call) {

            }
        });

    }
}