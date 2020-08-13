
package ungpay.com.androidapplications.retrofit;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitManager {

    private static Retrofit mRetrofit;

    public static RetrofitManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private final static RetrofitManager INSTANCE = new RetrofitManager();
    }

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient sOkHttpClient = builder
                .retryOnConnectionFailure(true)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(sOkHttpClient)
                .build();
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }
}