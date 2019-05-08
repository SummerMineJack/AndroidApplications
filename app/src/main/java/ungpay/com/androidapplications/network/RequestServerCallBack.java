package ungpay.com.androidapplications.network;

/**
 * Create by HuangJian on 2019/1/2
 * 请求服务端接口返回操作
 */
public interface RequestServerCallBack {
    void Fail(String reponseCode, String reponseMessage);

    void Success(String successData);
}
