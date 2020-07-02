package ungpay.com.androidapplications.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ungpay.com.androidapplications.R;

public class RetrofitRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_request2);
        findViewById(R.id.btn_request_api).setOnClickListener(v -> requestApi());
    }

    private void requestApi() {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://v.juhe.cn/").build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.getAllNews().enqueue(new Callback<NewsEntity>() {
            @Override
            public void onResponse(Call<NewsEntity> call, Response<NewsEntity> response) {
                LogUtils.e(response.body().getResult());
            }

            @Override
            public void onFailure(Call<NewsEntity> call, Throwable t) {
                LogUtils.e(t.getMessage());
            }
        });

    }
}