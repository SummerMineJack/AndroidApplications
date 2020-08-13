package ungpay.com.androidapplications.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * created by Summer on 2020/7/2
 */
public interface ApiInterface {
    @GET("toutiao/index?key=fb83a09e62fca69902b22fcd85983271")
    Call<ResultBean<List<NewsEntity>>> getAllNews();
}
