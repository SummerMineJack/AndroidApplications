package ungpay.com.androidapplications.MultiMedia;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import ungpay.com.androidapplications.R;

/**
 * 使用服务进行播放音乐
 */
public class ChapterSix extends AppCompatActivity implements View.OnClickListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {

    private Button btnStartService, btnStopService, btnstartMusic, btnStopMusic,
            btnLiveParseMusic, btnLiveStartMusic, btnLiveStopMusic;
    private Intent serviceIntent;
    private TextView tvShowSeek, tvShowStatus;
    private ChapterSixService bgService;
    private MediaPlayer mediaPlayer;
    private String mp3Url = "http://music.163.com/song/media/outer/url?id=1332424898.mp3";
    private Vector musicItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_six);
        this.setTitle(getIntent().getStringExtra("title"));
        serviceIntent = new Intent(ChapterSix.this, ChapterSixService.class);
        initView();
        initMediaPlayer();
    }

    /**
     * 初始化MediaPlayer
     */
    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
        tvShowSeek.setText("MediaPlayer is created");
        try {
            mediaPlayer.setDataSource(mp3Url);
            tvShowStatus.setText("setDataSource success");
            tvShowStatus.setText("callinig prepareIng");
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        btnStartService = findViewById(R.id.StartService);
        btnStopService = findViewById(R.id.StopService);
        btnstartMusic = findViewById(R.id.start_music);
        tvShowSeek = findViewById(R.id.prepare_tv);
        tvShowStatus = findViewById(R.id.status_tv);
        btnStopMusic = findViewById(R.id.stop_music);
        btnLiveParseMusic = findViewById(R.id.paresButton);
        btnLiveStartMusic = findViewById(R.id.startButton);
        btnLiveStopMusic = findViewById(R.id.stopButton);
        btnLiveStopMusic.setOnClickListener(this);
        btnLiveStartMusic.setOnClickListener(this);
        btnLiveParseMusic.setOnClickListener(this);
        btnLiveStartMusic.setEnabled(false);
        btnLiveStopMusic.setEnabled(false);
        btnStopMusic.setEnabled(false);
        btnstartMusic.setEnabled(false);
        btnstartMusic.setOnClickListener(this);
        btnStopMusic.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnStartService.setOnClickListener(this);
    }

    /**
     * 开启服务
     */
    private void startSerive() {
        startService(serviceIntent);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            bgService = ((ChapterSixService.ChapterSixServiceBinder) iBinder).getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bgService = null;
        }
    };

    /**
     * 关闭服务
     */
    private void stopService() {
        stopService(serviceIntent);
        unbindService(serviceConnection);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.StartService:
                startSerive();
                break;
            case R.id.StopService:
                stopService();
                break;
            case R.id.start_music:
                startMusic();
                break;
            case R.id.stop_music:
                stopMusic();
                break;
            case R.id.paresButton:
                ToastUtils.showLong("停止维护");
//                parseMusicFile();
                break;
            case R.id.startButton:
                playListMusic();
                break;
            case R.id.stopButton:
                break;
        }
    }

    /**
     * 加载音乐文件单
     */
    private void parseMusicFile() {
        musicItems = new Vector();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL requestURL = new URL("https://y.qq.com/n/yqq/playlist/3267752660.html");
                    URLConnection urlConnection = requestURL.openConnection();
                    HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        handler.sendMessage(Message.obtain(handler, 2));
                        InputStream inputStream = urlConnection.getInputStream();
                        BufferedReader bufferedReader =
                                new BufferedReader(new InputStreamReader(inputStream));
                        String line = null;

                        while ((line = bufferedReader.readLine()) != null) {
                            Log.e("~~~", line);
                            String filePath = "";
                            if (line.startsWith("http://")) {
                                filePath = line;
                            }
                            PlayFileList playFileList = new PlayFileList(filePath);
                            musicItems.add(playFileList);
                        }
                        inputStream.close();
                    } else {
                        handler.sendMessage(Message.obtain(handler, 1));
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        btnLiveStartMusic.setEnabled(true);
    }

    private void playListMusic() {
        btnLiveStartMusic.setEnabled(false);
        int curretnMusicListItem = 0;
        if (musicItems.size() > 0) {
            String filePath = ((PlayFileList) musicItems.get(curretnMusicListItem)).getFilePaht();
            try {
                mediaPlayer.setDataSource(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                ToastUtils.showLong("mediaPlayer找不到文件");
            }
            mediaPlayer.prepareAsync();
        }
    }

    class PlayFileList {
        String filePaht;

        public PlayFileList(String filePath) {
            filePaht = filePath;

        }

        public String getFilePaht() {
            return filePaht;
        }

        public void setFilePaht(String filePaht) {
            this.filePaht = filePaht;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                ToastUtils.showLong("地址访问失败");
            } else if (msg.what == 2) {
                ToastUtils.showLong("地址访问成功");
            }
        }
    };


    /**
     * 开始播放音乐
     */
    private void startMusic() {
        mediaPlayer.start();
    }

    /**
     * 停止音乐
     */
    private void stopMusic() {
        mediaPlayer.stop();
    }


    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        tvShowSeek.setText("" + i + "%");
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        ToastUtils.showLong("MediaPlayer is Completion");
        btnstartMusic.setEnabled(true);
        btnStopMusic.setEnabled(false);
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        tvShowStatus.setText("MediaPlayer is Error");
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        btnstartMusic.setEnabled(true);
        btnLiveStopMusic.setEnabled(true);
    }
}
