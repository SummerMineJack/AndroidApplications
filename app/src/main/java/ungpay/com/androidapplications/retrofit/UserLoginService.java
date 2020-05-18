package ungpay.com.androidapplications.retrofit;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * created by Summer on 2020/5/18
 */
public interface UserLoginService {
    @POST(ServerInterfaceConstant.appSdkUserLogin)
    Call<String> userLogin();
}
