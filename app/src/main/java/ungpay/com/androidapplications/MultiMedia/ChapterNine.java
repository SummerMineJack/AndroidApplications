package ungpay.com.androidapplications.MultiMedia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UtilsTransActivity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ungpay.com.androidapplications.BuildConfig;
import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.unit.DialogHelper;

/**
 * 视频的概述
 */
public class ChapterNine extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, SurfaceHolder
        .Callback, MediaController.MediaPlayerControl {

    private Button btnUseIntent2Plauy, btnUseVideoView2Play, btnUseMediaPlayer;
    private VideoView mVideoView;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_nine);
        this.setTitle(getIntent().getStringExtra("title"));
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        btnUseIntent2Plauy = findViewById(R.id.useIntent2Play);
        btnUseVideoView2Play = findViewById(R.id.useVideoView2Play);
        btnUseMediaPlayer = findViewById(R.id.useMediaPlayer);
        surfaceView = findViewById(R.id.MediaPlayerSurfaceView);
        btnUseMediaPlayer.setOnClickListener(this);
        mVideoView = findViewById(R.id.vide_view_play);
        btnUseVideoView2Play.setOnClickListener(this);
        btnUseIntent2Plauy.setOnClickListener(this);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.useIntent2Play:
                PermissionUtils.permission(PermissionConstants.getPermissions(PermissionConstants.STORAGE)).rationale((activity, shouldRequest) -> DialogHelper.showRationaleDialog(shouldRequest)).callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        playVideo4Intent1();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            DialogHelper.showOpenAppSettingDialog();
                        }
                        LogUtils.d(permissionsDeniedForever, permissionsDenied);
                    }
                }).request();
                break;
            case R.id.useVideoView2Play:
                useVideoView2Play();
                break;
            case R.id.useMediaPlayer:
                useMediaPlayer();
                break;
        }
    }

    /**
     * 使用MeidaPlayer进行播放视频
     */
    private void useMediaPlayer() {
        mediaController = new MediaController(ChapterNine.this);
        mVideoView.setVisibility(View.GONE);
        surfaceView.setVisibility(View.VISIBLE);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/mylove.mp4";
        try {
            mediaPlayer.setDataSource(filePath);

        } catch (IOException e) {
            e.printStackTrace();
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }

    /**
     * 使用videoView进行播放视频
     * 同样是使用FileUri进行播放视频
     */
    private void useVideoView2Play() {
        mVideoView.setVisibility(View.VISIBLE);
        surfaceView.setVisibility(View.GONE);
        Uri fileUri = FileProvider.getUriForFile(ChapterNine.this, BuildConfig.APPLICATION_ID + ".provider", FileUtils.getFileByPath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/LoveTheWayYouLie.mp4"));
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.setVideoURI(fileUri);
        mVideoView.start();
    }


    /**
     * 使用intent进行播放视频
     */
    private void playVideo4Intent() {
        String filename = "mylove.mp4";
        File file = new File(Environment.getExternalStorageDirectory() + "/Download", filename);
        Uri uri = FileProvider.getUriForFile
                (ChapterNine.this, BuildConfig.APPLICATION_ID + ".provider",
                        file);
        Log.e("~~~~~~~~~~", "B:" + uri + ":C");
        startActivity(new Intent(Intent.ACTION_VIEW).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent
                .FLAG_GRANT_WRITE_URI_PERMISSION).setDataAndType(uri, "video/*"));
    }

    private void playVideo4Intent1() {
        Uri fileUri = FileProvider.getUriForFile(ChapterNine.this, BuildConfig.APPLICATION_ID + ".provider", FileUtils.getFileByPath("/storage/emulated/0/Download/LoveTheWayYouLie.mp4"));
        startActivity(new Intent(Intent.ACTION_VIEW).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent
                .FLAG_GRANT_WRITE_URI_PERMISSION).setDataAndType(fileUri, "video/*"));
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        ToastUtils.showLong("MediaPlayer is Completion");

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(findViewById(R.id.MainView));
        mediaController.setEnabled(true);
        mediaController.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mediaController.isShowing()) {
            mediaController.hide();
        } else {
            mediaController.show();
        }
        return false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer.setDisplay(holder);
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
}
