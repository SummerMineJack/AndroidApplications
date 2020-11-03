package ungpay.com.androidapplications.AndroidAdvancedDevelopment.AndroidContactPHP;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.GsonUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.network.RequestServerCallBack;
import ungpay.com.androidapplications.network.ServerRequest;

public class SelAllUserInfoActivity extends AppCompatActivity implements RequestServerCallBack,
        View.OnClickListener {

    private Button btnSelAll, btnLogin, btnRegiste;
    private ArrayList<UserBean> userBeans;
    private ListUserInfoAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_all_user_info);
        userBeans = new ArrayList<>();
        listView = findViewById(R.id.listView);
        btnSelAll = findViewById(R.id.selAllUserInfo);
        btnLogin = findViewById(R.id.login);
        btnRegiste = findViewById(R.id.registe);
        btnSelAll.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnRegiste.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selAllUserInfo:
                selAllUserInfo();
                break;
            case R.id.login:
                break;
            case R.id.registe:
                break;
        }
    }


    public void selAllUserInfo() {
        String url = "http://130.252.103.57/PHPApi4Android/SelAllUserInfo.php";
        ServerRequest serverRequest = new ServerRequest(null, url, null);
        serverRequest.setInterfaceCode(this);
        serverRequest.request4Get();
    }


    @Override
    public void Fail(String reponseCode, String reponseMessage) {
    }

    @Override
    public void Success(String successData) {
        try {
            JSONArray jsonArray = new JSONArray(successData);
            for (int i = 0; i < jsonArray.length(); i++) {
                UserBean homePageOrderInformation = GsonUtils.fromJson(jsonArray.getString(i),
                        UserBean.class);
                userBeans.add(homePageOrderInformation);
            }
            adapter = new ListUserInfoAdapter(userBeans, SelAllUserInfoActivity.this);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
