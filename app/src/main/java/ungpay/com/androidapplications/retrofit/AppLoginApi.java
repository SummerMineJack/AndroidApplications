package ungpay.com.androidapplications.retrofit;

import java.util.List;

import retrofit2.Call;

/**
 * created by Summer on 2020/7/3
 */
public class AppLoginApi {
    public static void userLogin(ApiCallBack callBack) {
        ApiInterface service = RetrofitManager.getInstance().create(ApiInterface.class);
        Call<ResultBean<List<NewsEntity>>> call = service.getAllNews();
        call.enqueue(callBack.getCallback());
    }
}
