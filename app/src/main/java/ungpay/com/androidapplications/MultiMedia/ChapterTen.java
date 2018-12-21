package ungpay.com.androidapplications.MultiMedia;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.blankj.utilcode.util.FileUtils;

import java.io.IOException;
import java.util.ArrayList;

import ungpay.com.androidapplications.BuildConfig;
import ungpay.com.androidapplications.R;

/**
 * 视频进阶
 */
public class ChapterTen extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, MediaPlayer.OnBufferingUpdateListener, OnCompletionListener, MediaPlayer.OnPreparedListener, Callback, MediaController.MediaPlayerControl {

    private Button btnQueryVideo4MediaStore, btnPlayInternetVideo4VideoView, btnPlayInternetVideo4MediaPlayer;
    private ListView queryResult;
    private VideoView videoView;
    private ArrayList<VideoInfo> videoInfos;

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    private TextView tvShowStatus;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_ten);
        this.setTitle(getIntent().getStringExtra("title"));
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        videoInfos = new ArrayList<>();
        btnQueryVideo4MediaStore = findViewById(R.id.QueryVideo4MediaStore);
        btnPlayInternetVideo4VideoView = findViewById(R.id.internetVideo4VideView);
        btnPlayInternetVideo4MediaPlayer = findViewById(R.id.internetVideo4MediaPlayer);
        videoView = findViewById(R.id.play2networkVideo);
        queryResult = findViewById(R.id.query_result);
        surfaceView = findViewById(R.id.mediaSurfaceView);
        tvShowStatus = findViewById(R.id.networkvideostatus);
        btnPlayInternetVideo4MediaPlayer.setOnClickListener(this);
        btnPlayInternetVideo4VideoView.setOnClickListener(this);
        btnQueryVideo4MediaStore.setOnClickListener(this);
        initMediaPlayer();
    }

    /**
     * 初始化MediaPlayer
     */
    private void initMediaPlayer() {
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
        String filePath = "rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov";
        try {
            mediaPlayer.setDataSource(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvShowStatus.setText("MediaPlayer is DataSource Set");
        mediaController = new MediaController(this);
        mediaController.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mediaController.isShowing()) {
                    mediaController.hide();
                } else {
                    mediaController.show();
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.QueryVideo4MediaStore:
                queryVideo4MediaStore();
                break;
            case R.id.internetVideo4VideView:
                internetVideo4VideoView();
                break;
            case R.id.internetVideo4MediaPlayer:
                break;
        }
    }

    /**
     * 播放网络视频
     * rstp
     */
    private void internetVideo4MediaPlayer() {


    }

    /**
     * 播放网络视频
     * rstp
     */
    private void internetVideo4VideoView() {
        queryResult.setVisibility(View.GONE);
        videoView.setVisibility(View.VISIBLE);
        surfaceView.setVisibility(View.GONE);
        Uri uri = Uri.parse("rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov");
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.start();
    }

    /**
     * 使用MediaStore进行查询视频检索
     */
    private void queryVideo4MediaStore() {
        queryResult.setVisibility(View.VISIBLE);
        videoView.setVisibility(View.GONE);
        surfaceView.setVisibility(View.GONE);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        String[] mediaColums = {MediaStore.Video.Media._ID, MediaStore.Video.Media.DATA, MediaStore.Video.Media.TITLE, MediaStore.Video.Media.MIME_TYPE};
        Cursor mediaCursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, mediaColums, null, null, null);
        if (mediaCursor.moveToFirst()) {
            do {
                VideoInfo videoInfo = new VideoInfo();
                videoInfo.setFilePath(mediaCursor.getString(mediaCursor.getColumnIndex(MediaStore.Video.Media.DATA)));
                videoInfo.setMimeType(mediaCursor.getString(mediaCursor.getColumnIndex(MediaStore.Video.Media.MIME_TYPE)));
                videoInfo.setTitle(mediaCursor.getString(mediaCursor.getColumnIndex(MediaStore.Video.Media.TITLE)));
                mediaMetadataRetriever.setDataSource(mediaCursor.getString(mediaCursor.getColumnIndex(MediaStore.Video.Media.DATA)));
                Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime();
                videoInfo.setThumbPath(bitmap);
                videoInfos.add(videoInfo);
            } while (mediaCursor.moveToNext());

        }
        queryResult.setAdapter(new ListViewAdapter());
        queryResult.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        VideoInfo videoInfo = videoInfos.get(position);
        Uri fileUri = FileProvider.getUriForFile(ChapterTen.this, BuildConfig.APPLICATION_ID + ".provider", FileUtils.getFileByPath(videoInfo.getFilePath().trim()));
        startActivity(new Intent(Intent.ACTION_VIEW).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent
                .FLAG_GRANT_WRITE_URI_PERMISSION).setDataAndType(fileUri, "video/*"));
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        tvShowStatus.setText("MediaPlyer :" + percent + "%");
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        tvShowStatus.setText("MediaPlayer is Compled");
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        tvShowStatus.setText("MediaPlayer is Prepared");
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(findViewById(R.id.mediaSurfaceView));
        mediaController.setEnabled(true);
        mediaController.show();
        mediaPlayer.start();
        tvShowStatus.setText("MediaPlayer started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer.setDisplay(holder);
        tvShowStatus.setText("MediaPlayer is Display set it");
        mediaPlayer.prepareAsync();
        tvShowStatus.setText("MediaPlayer PrepareAsync");
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    /**
     * 进行显示视频信息的ListViewAdapter
     */
    public class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return videoInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return videoInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(ChapterTen.this).inflate(R.layout.activity_chapter_ten_list_item, null);
                viewHolder.videoPic = convertView.findViewById(R.id.video_pic);
                viewHolder.videoTitle = convertView.findViewById(R.id.video_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.videoTitle.setText(videoInfos.get(position).getTitle());
            viewHolder.videoPic.setImageBitmap(videoInfos.get(position).getThumbPath());
            return convertView;
        }

        class ViewHolder {
            TextView videoTitle;
            ImageView videoPic;
        }
    }

    /**
     * 视频信息实体类
     */
    public class VideoInfo implements Parcelable {
        public String filePath;
        public String mimeType;
        public Bitmap thumbPath;
        public String title;

        public VideoInfo() {

        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getMimeType() {
            return mimeType;
        }

        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        public Bitmap getThumbPath() {
            return thumbPath;
        }

        public void setThumbPath(Bitmap thumbPath) {
            this.thumbPath = thumbPath;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        protected VideoInfo(Parcel in) {
            filePath = in.readString();
            mimeType = in.readString();
            thumbPath = in.readParcelable(Bitmap.class.getClassLoader());
            title = in.readString();
        }

        public final Creator<VideoInfo> CREATOR = new Creator<VideoInfo>() {
            @Override
            public VideoInfo createFromParcel(Parcel in) {
                return new VideoInfo(in);
            }

            @Override
            public VideoInfo[] newArray(int size) {
                return new VideoInfo[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(filePath);
            dest.writeString(mimeType);
            dest.writeParcelable(thumbPath, flags);
            dest.writeString(title);
        }
    }

}
