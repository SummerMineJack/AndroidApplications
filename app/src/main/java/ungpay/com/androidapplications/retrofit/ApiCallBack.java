package ungpay.com.androidapplications.retrofit;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallBack<T> {
    private Callback<ResultBean<T>> callback;

    public Callback<ResultBean<T>> getCallback() {
        return callback;
    }

    public ApiCallBack(final Context context) {
        callback = new Callback<ResultBean<T>>() {
            @Override
            public void onResponse(Call<ResultBean<T>> call, Response<ResultBean<T>> response) {
                if (response != null) {
                    switch (response.code()) {
                        case 200:
                            //服务器响应请求成功
                            ResultBean<T> result = response.body();
                            if (result != null) {
                                switch (result.error_code) {
                                    case 0:
                                        ApiCallBack.this.onSuccess(call, result);
                                        break;
                                }
                            }
                            break;
                        case 401:
                            break;
                        case 403:
                            break;
                        case 503:
                            //503(服务不可用)服务器目前无法使用(由于超载或停机维护)。通常，这只是暂时状态
                            ApiCallBack.this.onFailure(call, "服务不可用，请稍候重试。");
                            break;
                        default:
                            break;
                    }
                } else {
                    ApiCallBack.this.onFailure(call, "请求服务器失败");
                }
            }

            @Override
            public void onFailure(Call<ResultBean<T>> call, Throwable t) {
                ApiCallBack.this.onFailure(call, "");
            }
        };
    }

    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     */
    public abstract void onSuccess(Call<ResultBean<T>> call, ResultBean<T> result);

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     */
    public abstract void onFailure(Call<ResultBean<T>> call, String message);

    public abstract void onUpDate(Call<ResultBean<T>> call);

    /**
     * 品酒失败回调此方法
     *
     * @param call
     * @param message
     */
    public void onPickWineFailed(Call<ResultBean<T>> call, String message) {
    }

}
