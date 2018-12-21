package ungpay.com.androidapplications.MultiMedia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.network.DialogCallback;
import ungpay.com.androidapplications.network.ServerReponse;
import ungpay.com.androidapplications.unit.DialogHelper;

/**
 * web媒体服务
 */
public class ChapterTwelve extends AppCompatActivity implements View.OnClickListener, LocationListener {

    private static final String flickrApiKey = "7be35df6be53b684f8c920db434f6e7b";
    private Button btnFlickrInternetService, btnGetLocation, btnRecorderXml;
    private ListView showJokeResult;
    private ArrayList<Data> datas;
    private LocationManager locationManager;
    private TextView locationTV;
    private String provider;
    private XMLUser xmlUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_twelve);
        this.setTitle(getIntent().getStringExtra("title"));
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        datas = new ArrayList<>();
        btnFlickrInternetService = findViewById(R.id.go_service);
        showJokeResult = findViewById(R.id.flickr_listView);
        btnGetLocation = findViewById(R.id.get_location);
        locationTV = findViewById(R.id.locationTV);
        btnRecorderXml = findViewById(R.id.recorderXml);
        btnRecorderXml.setOnClickListener(this);
        btnGetLocation.setOnClickListener(this);
        btnFlickrInternetService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_service:
                useInternetService();
                break;
            case R.id.get_location:
                getLocation();
                break;
            case R.id.recorderXml:
                recorderXML();
                break;
        }
    }


    /**
     * 本地XML实体类
     */
    public class XMLUser {
        String userId;
        String userName;
        String userLastName;

        public XMLUser() {
            userId = "";
            userName = "";
            userLastName = "";
        }

    }

    /**
     * 解析本地的XML
     */
    private void recorderXML() {
        //使用字符串拼接xml
        String xmlContent = "<?xml version=\"1.0\"?>\n<user>\n<userId>20</userId>\n<userName>HuangJian</userName>\n<userLastName>SummerJack</userLastName></user>";
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new UserXmlHander());
            xmlReader.parse(new InputSource(new ByteArrayInputStream(xmlContent.getBytes())));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class UserXmlHander extends DefaultHandler {
        static final int NONE = 0;
        static final int ID = 1;
        static final int NAME = 2;
        static final int LASTNAME = 3;
        int stats = NONE;
        static final String ID_ELEMENT = "userId";
        static final String NAME_ELEMENT = "userName";
        static final String LASTNAME_ELEMENT = "userLastName";

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            xmlUser = new XMLUser();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            Log.e("~~~~", "endDocument");
            Log.e("~~~~", "UserInfo:" + xmlUser.userId + " " + xmlUser.userName + " " + xmlUser.userLastName);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (localName.equalsIgnoreCase(ID_ELEMENT)) {
                stats = ID;
            } else if (localName.equalsIgnoreCase(NAME_ELEMENT)) {
                stats = NAME;
            } else if (localName.equalsIgnoreCase(LASTNAME_ELEMENT)) {
                stats = LASTNAME;
            } else {
                stats = NONE;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            Log.e("~~~~", "endElement");
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            String stringChars = new String(ch, start, length);
            if (stats == ID) {
                xmlUser.userId += stringChars.trim();
            } else if (stats == NAME) {
                xmlUser.userName += stringChars.trim();
            } else if (stats == LASTNAME) {
                xmlUser.userLastName += stringChars.trim();
            }
        }
    }

    /**
     * 获取位置信息
     */
    private void getLocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        PermissionUtils.permission(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}).rationale(new PermissionUtils.OnRationaleListener() {
            @Override
            public void rationale(ShouldRequest shouldRequest) {
                DialogHelper.showRationaleDialog(shouldRequest);
            }
        }).callback(new PermissionUtils.FullCallback() {
            @Override
            public void onGranted(List<String> permissionsGranted) {
                if (ActivityCompat.checkSelfPermission(ChapterTwelve.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ChapterTwelve.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000l, 5.0f, ChapterTwelve.this);
                List<String> providerList = locationManager.getProviders(true);
// 传入 true 就表示只有启用的位置提供器才会被返回。

                if (providerList.contains(LocationManager.GPS_PROVIDER)) {
                    provider = LocationManager.GPS_PROVIDER;
                } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
                    provider = LocationManager.NETWORK_PROVIDER;
                }
                Location location = locationManager.getLastKnownLocation(provider);
                locationTV.setText("经度：" + location.getLatitude() + " 纬度：" + location.getLongitude());
            }

            @Override
            public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                if (!permissionsDeniedForever.isEmpty()) {
                    DialogHelper.showOpenAppSettingDialog();
                }
                LogUtils.d(permissionsDeniedForever, permissionsDenied);
            }
        }).request();
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    /**
     * 访问api网络
     */
    private void useInternetService() {
        showJokeResult.setVisibility(View.VISIBLE);
        OkGo.<ServerReponse<Result>>get("http://v.juhe.cn/joke/content/text.php").tag(this)
                .params("page", "20")
                .params("pagesize", "15")
                .params("key", "a51a682a669992d512ee34bc9f0d64da")
                .execute(new DialogCallback<ServerReponse<Result>>(this) {
                    @Override
                    public void onSuccess(Response<ServerReponse<Result>> response) {
                        handlers.sendMessage(Message.obtain(handlers, 1, response));
                    }

                    @Override
                    public void onError(Response<ServerReponse<Result>> response) {
                        super.onError(response);
                        handlers.sendMessage(Message.obtain(handlers, 1, response));
                    }
                });
    }

    /**
     * 网络请求后进行UI线程操作
     */
    private Handler handlers = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Response<ServerReponse<List<Data>>> response = (Response<ServerReponse
                            <List<Data>>>) msg.obj;
                    Result results = (Result) response.body().getResult();
                    for (int i = 0; i < results.getData().size(); i++) {
                        Data data = new Data();
                        data.setContent(results.getData().get(i).getContent());
                        data.setHashId(results.getData().get(i).getHashId());
                        data.setUnixtime(results.getData().get(i).getUnixtime());
                        data.setUpdatetime(results.getData().get(i).getUpdatetime());
                        datas.add(data);
                    }
                    showJokeResult.setAdapter(new ShowJokeListAdapter());
                    break;
            }

        }
    };

    @Override
    public void onLocationChanged(Location location) {
        locationTV.setText("经度：" + location.getLatitude() + " 纬度：" + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public class ShowJokeListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Viewholder viewholder = null;
            if (convertView == null) {
                viewholder = new Viewholder();
                convertView = LayoutInflater.from(ChapterTwelve.this).inflate(R.layout.activity_chapter_twelve_list_item, null);
                viewholder.tvTime = convertView.findViewById(R.id.timeS);
                viewholder.tvContent = convertView.findViewById(R.id.contentS);
                convertView.setTag(viewholder);
            } else {
                viewholder = (Viewholder) convertView.getTag();
            }
            viewholder.tvTime.setText(datas.get(position).getUpdatetime());
            viewholder.tvContent.setText(datas.get(position).getContent());
            return convertView;
        }

        class Viewholder {
            TextView tvTime;
            TextView tvContent;
        }
    }


    public class Result {
        public ArrayList<Data> data;

        public ArrayList<Data> getData() {
            return data;
        }

        public void setData(ArrayList<Data> data) {
            this.data = data;
        }
    }

    public class Data implements Parcelable {
        private String content;
        private String hashId;
        private long unixtime;
        private String updatetime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHashId() {
            return hashId;
        }

        public void setHashId(String hashId) {
            this.hashId = hashId;
        }

        public long getUnixtime() {
            return unixtime;
        }

        public void setUnixtime(long unixtime) {
            this.unixtime = unixtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public Data() {

        }

        protected Data(Parcel in) {
            content = in.readString();
            hashId = in.readString();
            unixtime = in.readLong();
            updatetime = in.readString();
        }

        public final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(content);
            dest.writeString(hashId);
            dest.writeLong(unixtime);
            dest.writeString(updatetime);
        }
    }
}
