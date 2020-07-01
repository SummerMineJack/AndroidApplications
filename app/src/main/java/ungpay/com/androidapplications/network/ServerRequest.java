package ungpay.com.androidapplications.network;

import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;

/**
 * Create by HuangJian on 2019/1/2
 * 服务端接口请求
 */
public class ServerRequest {
    private RequestServerCallBack interfaceCode;
    private RequestParams requestParams;
    private JSONObject jsonObject;
    private String requestUrl;

    public ServerRequest(RequestParams requestParams, String requestUrl, JSONObject jsonObject) {
        this.requestParams = requestParams;
        this.requestUrl = requestUrl;
        this.jsonObject = jsonObject;
    }

    public void setInterfaceCode(RequestServerCallBack interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    /**
     * post请求
     */
    public void request4Post(Context context) throws UnsupportedEncodingException {
        HttpUtil.post4json(context, requestUrl,
                new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8")), "application" +
                        "/json",
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        if (statusCode == 200 && response != null) {
                            if (response.optString("successFlag").equals("00")) {
                                if (response.optString("respCode").equals("00")) {
                                    String respData = response.optString("respData");
                                    if (interfaceCode != null) {
                                        interfaceCode.Success(respData);
                                    }
                                }
                            } else {
                                if (interfaceCode != null) {
                                    interfaceCode.Fail(response.optString("respCode"),
                                            response.optString("respDesc"));
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                          JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        if (interfaceCode != null) {
                            interfaceCode.Fail(String.valueOf(statusCode), "");
                        }
                    }

                });

    }

    /**
     * post请求
     */
    public void requset4PostForm() {
        HttpUtil.post4Str(requestUrl, requestParams, new AsyncHttpResponseHandlerCallBack());
    }

    /**
     * get请求
     */
    public void request4Get() {
        HttpUtil.get(requestUrl, requestParams, new AsyncHttpResponseHandlerCallBack());
    }

    /**
     * get post请求回调
     */
    private class AsyncHttpResponseHandlerCallBack extends AsyncHttpResponseHandler {

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            if (statusCode == 200 && responseBody != null) {
                try {
                    String jsonResult = new String(responseBody, "utf-8");
                    if ((jsonResult != null) && (jsonResult.contains("{"))) {
                        JSONObject serverJson = new JSONObject(jsonResult);
                        if (serverJson.optInt("code") == 200) {
                            String respData = serverJson.optString("data");
                            if (interfaceCode != null) {
                                interfaceCode.Success(respData);
                            }
                        } else {
                            if (interfaceCode != null) {
                                interfaceCode.Fail(serverJson.optString("respCode"),
                                        serverJson.optString("respDesc"));
                            }
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
                              Throwable error) {
            if (interfaceCode != null) {
                interfaceCode.Fail(String.valueOf(statusCode), "");
            }
        }
    }

}
