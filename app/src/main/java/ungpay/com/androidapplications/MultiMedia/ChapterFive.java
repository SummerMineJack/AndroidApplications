package ungpay.com.androidapplications.MultiMedia;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UtilsTransActivity;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.unit.DialogHelper;

public class ChapterFive extends AppCompatActivity implements View.OnClickListener, SeekBar
        .OnSeekBarChangeListener {

    private Button btnOpenSystemMusic, btnStartMediaPlayer, btnStopMediaPlayer, btnUseMediaStoe;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_five);
        this.setTitle(getIntent().getStringExtra("title"));
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        btnOpenSystemMusic = findViewById(R.id.open_system_music);
        btnStartMediaPlayer = findViewById(R.id.start_media_player);
        btnStopMediaPlayer = findViewById(R.id.stop_media_player);
        btnUseMediaStoe = findViewById(R.id.use_media_store);
        seekBar = findViewById(R.id.Media_player_SeekBar);
        seekBar.setOnSeekBarChangeListener(this);
        btnUseMediaStoe.setOnClickListener(this);
        btnStopMediaPlayer.setOnClickListener(this);
        btnOpenSystemMusic.setOnClickListener(this);
        btnStartMediaPlayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_system_music:
                openSystemMusic();
                break;
            case R.id.start_media_player:
                useMediaPlayer4Start();
                break;
            case R.id.stop_media_player:
                useMediaPlayer4SStop();
                break;
            case R.id.use_media_store:
                useMediaStore();
                break;
        }
    }

    /**
     * q
     * 使用MediaStore进行查询音频文件
     */
    private void useMediaStore() {
        String[] col = {
                MediaStore.Audio.Media.DATA, MediaStore.Audio.Media._ID, MediaStore.Audio.Media
                .TITLE,
                MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.MIME_TYPE, MediaStore
                .Audio.Media
                .ARTIST, MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.IS_RINGTONE,
                MediaStore
                        .Audio.Media.IS_ALARM, MediaStore.Audio.Media.IS_MUSIC, MediaStore.Audio
                .Media
                .IS_NOTIFICATION};
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                col, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(col[2]);
        String titleMusic = cursor.getString(columnIndex);
        ToastUtils.showLong("根据cursor获取的音频标题为：" + titleMusic);
    }


    /**
     * 停止播放
     */
    private void useMediaPlayer4SStop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        seekBar.setProgress(0);
    }

    /**
     * 使用MediaPlayer进行播放音乐
     */
    private void useMediaPlayer4Start() {
        //1.直接使用R资源id进行播放音乐
        //mediaPlayer = MediaPlayer.create(ChapterFive.this, R.raw.popdanthology);
        //2.使用获取当前app raw下转换成uri进行播放音乐
        mediaPlayer = MediaPlayer.create(ChapterFive.this, Uri.parse("android" +
                ".resource://ungpay.com.androidapplications" + "/" + R.raw.popdanthology));
        //mediaPlayer.setOnCompletionListener(new MediaPlayerControl());
        int max = mediaPlayer.getDuration();
        seekBar.setMax(max);
        // 当前音乐播放完成的操作，可继续循环播放，也可进行其他操作
        //mediaPlayer.setLooping(true);//进行单曲循环播放
        mediaPlayer.start();
        checkPlayProgress();
    }

    private void checkPlayProgress() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        };
        timer.schedule(timerTask, 0, 10);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mediaPlayer.seekTo(seekBar.getProgress());
    }

    class MediaPlayerControl implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            //该方法是当当前播发的音乐完成后的操作
            mediaPlayer.start();
        }
    }

    /**
     * 通过意图打开音乐播放器
     */
    private void openSystemMusic() {
        PermissionUtils.permission(PermissionConstants.getPermissions(PermissionConstants.STORAGE))
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(UtilsTransActivity activity, ShouldRequest shouldRequest) {
                        DialogHelper.showRationaleDialog(shouldRequest);
                    }

                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        openMusic();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            DialogHelper.showOpenAppSettingDialog();
                        }
                        LogUtils.d(permissionsDeniedForever, permissionsDenied);
                    }
                })
                .request();

    }

    private void openMusic() {
        if (DeviceUtils.getSDKVersionCode() >= 24) {
            openMusic4Api24();
        } else {
            openMusic4Api21();
        }
    }

    private void openMusic4Api24() {
        /*try {
            InputStream inputStream = getResources().getAssets().open("popdanthology.mp3");
            File filePath = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
             "popdanthology.mp3");
            boolean isInputSuccess = FileIOUtils.writeFileFromIS(filePath, inputStream);
            if (isInputSuccess) {
                Uri file = FileProvider.getUriForFile(ChapterFive.this, BuildConfig
                .APPLICATION_ID + ".provider", filePath);
                startActivity(new Intent(Intent.ACTION_VIEW).setDataAndType(file, "audio/*"));
            } else {
                ToastUtils.showLong("创建音频文件失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        boolean isSuccess = ResourceUtils.copyFileFromRaw(R.raw.popdanthology,
                getCacheDir().getPath() + "/raw/popdanthology.mp3");
        ToastUtils.showLong(isSuccess + "");
        startActivity(new Intent(Intent.ACTION_VIEW).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent
                .FLAG_GRANT_WRITE_URI_PERMISSION).setDataAndType(FileProvider.getUriForFile
                (ChapterFive.this, "ungpay.com.androidapplications.provider",
                        FileUtils.getFileByPath(getCacheDir().getPath() + "/raw/popdanthology" +
                                ".mp3")), "audio/*"));
    }

    private void openMusic4Api21() {
        startActivity(new Intent(Intent.ACTION_VIEW).setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download", "more.mp3")), "audio/*"));

    }
}
