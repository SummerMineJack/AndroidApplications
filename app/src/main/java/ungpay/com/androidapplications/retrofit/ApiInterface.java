package ungpay.com.androidapplications.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * created by Summer on 2020/7/2
 */
public interface ApiInterface {
    @GET("toutiao/index")
    Call<NewsEntity> getAllNews();
}
