package ungpay.com.androidapplications.MultiMedia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;

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
import ungpay.com.androidapplications.databinding.ActivityChapterTwelveBinding;
import ungpay.com.androidapplications.unit.DialogHelper;

/**
 * web媒体服务
 */
public class ChapterTwelve extends AppCompatActivity implements View.OnClickListener, LocationListener {

    private static final String flickrApiKey = "7be35df6be53b684f8c920db434f6e7b";
    private ListView showJokeResult;
    private ArrayList<Data> datas;
    private XMLUser xmlUser;

    private ActivityChapterTwelveBinding activityChapterTwelveBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChapterTwelveBinding = ActivityChapterTwelveBinding.inflate(LayoutInflater.from(this));
        setContentView(activityChapterTwelveBinding.getRoot());
        this.setTitle(getIntent().getStringExtra("title"));
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        datas = new ArrayList<>();
        showJokeResult = findViewById(R.id.flickr_listView);
        activityChapterTwelveBinding.recorderXml.setOnClickListener(this);
        activityChapterTwelveBinding.getLocation.setOnClickListener(this);
        activityChapterTwelveBinding.goService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_service:
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

    private Location lastLocation;

    /**
     * 获取位置信息
     */
    @SuppressLint("MissingPermission")
    private void getLocation() {
        PermissionUtils.permission(PermissionConstants.LOCATION).rationale((activity, shouldRequest) -> DialogHelper.showRationaleDialog(shouldRequest)).callback(new PermissionUtils.SimpleCallback() {
            @Override
            public void onGranted() {
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                List<String> providers = locationManager.getProviders(true);
                for (String provider : providers) {
                    Location l = locationManager.getLastKnownLocation(provider);
                    if (l == null) {
                        continue;
                    }
                    if (lastLocation == null || l.getAccuracy() < lastLocation.getAccuracy()) {
                        lastLocation = l;
                    }
                    activityChapterTwelveBinding.locationTV.setText("经度：" + lastLocation.getLatitude() + " 纬度：" + lastLocation.getLongitude());
                }
            }

            @Override
            public void onDenied() {
                getLocation();
            }
        }).request();
    }

    @Override
    public void onLocationChanged(Location location) {
        activityChapterTwelveBinding.locationTV.setText("经度：" + location.getLatitude() + " 纬度：" + location.getLongitude());
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
