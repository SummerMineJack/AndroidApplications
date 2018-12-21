package ungpay.com.androidapplications.MultiMedia;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.TimeUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.unit.DialogHelper;

/**
 * 音频捕获
 */
public class ChapterSeven extends AppCompatActivity implements OnClickListener, MediaPlayer.OnCompletionListener {

    private Button btnUseIntentCatchMusic, btnPlaySound4Recond, btnStratRecording, btnStopRecording, btnPlayRecording, btnStartAudiaoRecorder, btnStopAudioRecorder, btnStartPlayBackAudioRecorder, btnStopPlayBackAudioRecorder;
    private Uri resultFileUri;
    private MediaRecorder mediaRecorder;
    private File audioFile;
    private MediaPlayer mediaPlayer;
    private TextView zhenfu, status, status_audia_recorder;
    private boolean isRecording = false;
    private RecordAmpliute recordAmpliute;
    private boolean isPlaying = false;

    //AudioReocrder
    int sampleRateInHz = 11025;
    int channlecConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private RecordAudio recordAudio;
    private PlayAudio playAudio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_seven);
        this.setTitle(getIntent().getStringExtra("title"));
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        btnUseIntentCatchMusic = findViewById(R.id.chapter_seven_use_intent);
        btnPlaySound4Recond = findViewById(R.id.PlaySound4Recond);
        btnStratRecording = findViewById(R.id.StartRecording);
        btnStopRecording = findViewById(R.id.StopRecording);
        btnPlayRecording = findViewById(R.id.PlayRecording);

        btnStartAudiaoRecorder = findViewById(R.id.start_audio_recorder);
        btnStopAudioRecorder = findViewById(R.id.stop_audio_recorder);
        btnStartPlayBackAudioRecorder = findViewById(R.id.start_play_back_audio_recorder);
        btnStopPlayBackAudioRecorder = findViewById(R.id.stop_play_back_audio_recorder);
        status_audia_recorder = findViewById(R.id.status_audia_recorder);


        status = findViewById(R.id.status);
        zhenfu = findViewById(R.id.zhenfu);
        zhenfu.setText("0");
        status.setText("Ready");

        btnStartAudiaoRecorder.setOnClickListener(this);
        btnStopAudioRecorder.setOnClickListener(this);
        btnStartPlayBackAudioRecorder.setOnClickListener(this);
        btnStopPlayBackAudioRecorder.setOnClickListener(this);
        btnStratRecording.setOnClickListener(this);
        btnStopRecording.setOnClickListener(this);
        btnPlayRecording.setOnClickListener(this);
        btnStopRecording.setEnabled(false);
        btnPlayRecording.setEnabled(false);
        btnPlaySound4Recond.setEnabled(false);
        btnPlaySound4Recond.setOnClickListener(this);
        btnUseIntentCatchMusic.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chapter_seven_use_intent:
                useIntentCatchMusic();
                break;
            case R.id.PlaySound4Recond:
                playSound4Recond();
                break;
            case R.id.StartRecording:
                PermissionUtils.permission(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}).rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(ShouldRequest shouldRequest) {
                        DialogHelper.showRationaleDialog(shouldRequest);
                    }
                }).callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        startRecording();
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
            case R.id.StopRecording:
                stopRecording();
                break;
            case R.id.PlayRecording:
                playRecording();
                break;
            case R.id.start_audio_recorder:
                PermissionUtils.permission(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}).rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(ShouldRequest shouldRequest) {
                        DialogHelper.showRationaleDialog(shouldRequest);
                    }
                }).callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        try {
                            startAudiaoRecorder();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
            case R.id.stop_audio_recorder:
                isRecording = false;
                break;
            case R.id.start_play_back_audio_recorder:
                startPlayBackAudioReocrder();
                break;
            case R.id.stop_play_back_audio_recorder:
                stopPlayBackAudioReocrder();
                break;
        }
    }

    /**
     * 停止播放使用AudioRecorder进行录制的音频
     */
    private void stopPlayBackAudioReocrder() {
        isPlaying = false;
        btnStopPlayBackAudioRecorder.setEnabled(false);
        btnStartPlayBackAudioRecorder.setEnabled(true);

    }

    /**
     * 播放使用AudioRecorder进行录制的音频
     */
    private void startPlayBackAudioReocrder() {
        btnStartPlayBackAudioRecorder.setEnabled(true);
        playAudio = new PlayAudio();
        playAudio.execute();
        btnStopPlayBackAudioRecorder.setEnabled(true);

    }

    /**
     * 使用AudioRecording进行音频的编解码
     */
    private void startAudiaoRecorder() throws IOException {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audioRecorder");
        file.mkdirs();
        audioFile = File.createTempFile("audioRecorder", ".pcm", file);
        btnStartAudiaoRecorder.setEnabled(false);
        btnStopAudioRecorder.setEnabled(true);
        btnStartPlayBackAudioRecorder.setEnabled(true);
        recordAudio = new RecordAudio();
        recordAudio.execute();
    }

    /**
     * 使用mediaRecorder类进行录制音频
     */
    private void startRecording() {
        isRecording = true;
        recordAmpliute = new RecordAmpliute();
        recordAmpliute.execute();
        status.setText("Recording");
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        File recorderFilePath = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/huangjianLuzhi");
        recorderFilePath.mkdirs();
        try {
            audioFile = File.createTempFile("recording", ".mp3", recorderFilePath);
            mediaRecorder.setOutputFile(audioFile.getAbsolutePath());
            mediaRecorder.setAudioChannels(2);
            mediaRecorder.prepare();
            mediaRecorder.start();
            btnStopRecording.setEnabled(true);
            btnStratRecording.setEnabled(false);
            btnPlayRecording.setEnabled(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止录制
     */
    private void stopRecording() {
        isRecording = false;
        recordAmpliute.cancel(true);
        mediaRecorder.stop();
        mediaRecorder.release();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.TITLE, "personal is title");
        contentValues.put(MediaStore.MediaColumns.DATE_ADDED, TimeUtils.getNowDate().getTime());
        contentValues.put(MediaStore.MediaColumns.DATA, audioFile.getAbsolutePath());
        Uri newUri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contentValues);
        Log.e("~~~", "Uri:" + newUri);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
        try {
            mediaPlayer.setDataSource(audioFile.getAbsolutePath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnPlayRecording.setEnabled(true);
        btnStopRecording.setEnabled(false);
        btnStratRecording.setEnabled(true);
        status.setText("Ready to play");
    }

    /**
     * 播放录制
     */
    private void playRecording() {
        status.setText("Playing");
        mediaPlayer.start();
        btnPlayRecording.setEnabled(false);
        btnStopRecording.setEnabled(false);
        btnStratRecording.setEnabled(false);
    }


    /**
     * 播放刚刚录音的音频
     */
    private void playSound4Recond() {
        mediaPlayer = MediaPlayer.create(ChapterSeven.this, resultFileUri);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.start();
        btnPlaySound4Recond.setEnabled(false);

    }

    /**
     * 通过intent捕获音频
     */
    private void useIntentCatchMusic() {
        //使用intent打开录音功能
        startActivityForResult(new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    resultFileUri = data.getData();
                    btnPlaySound4Recond.setEnabled(true);
                    break;
            }
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        btnPlaySound4Recond.setEnabled(true);
        btnPlayRecording.setEnabled(true);
        btnStopRecording.setEnabled(false);
        btnStratRecording.setEnabled(true);
        status.setText("Ready");
    }

    /**
     * 使用异步的方式进行显示声音的振幅大小
     */
    private class RecordAmpliute extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            while (isRecording) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(mediaRecorder.getMaxAmplitude());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            zhenfu.setText(values[0].toString());
        }
    }

    /**
     * AudiaoRecorder进行原始音频播放
     */
    private class PlayAudio extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            isPlaying = true;
            int bufferSize = AudioTrack.getMinBufferSize(sampleRateInHz, channlecConfig, audioFormat);
            short[] audioData = new short[bufferSize / 4];
            try {
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(audioFile)));
                AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRateInHz, channlecConfig, audioFormat, bufferSize, AudioTrack.MODE_STREAM);
                audioTrack.play();
                while (isPlaying && dataInputStream.available() > 0) {
                    int i = 0;
                    while (dataInputStream.available() > 0 && i < audioData.length) {
                        audioData[i] = dataInputStream.readShort();
                        i++;
                    }
                    audioTrack.write(audioData, 0, audioData.length);
                }
                dataInputStream.close();
                handler.sendMessage(Message.obtain(handler, 0x0002));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x0002:
                    btnStartPlayBackAudioRecorder.setEnabled(false);
                    btnStopPlayBackAudioRecorder.setEnabled(true);
                    break;
            }
        }
    };

    /**
     * AudiaoRecorder进行原始音频播放
     */
    private class RecordAudio extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            isRecording = true;
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(audioFile)));
                int bufferSize = AudioRecord.getMinBufferSize(sampleRateInHz, channlecConfig, audioFormat);
                AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRateInHz, channlecConfig, audioFormat, bufferSize);
                short[] buffer = new short[bufferSize];
                audioRecord.startRecording();
                int r = 0;
                while (isRecording) {
                    int bufferResult = audioRecord.read(buffer, 0, bufferSize);
                    for (int i = 0; i < bufferResult; i++) {
                        dataOutputStream.writeShort(buffer[i]);
                    }
                    publishProgress(new Integer(r));
                    r++;
                }
                audioRecord.stop();
                dataOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            status_audia_recorder.setText(values[0].toString());
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            btnStartAudiaoRecorder.setEnabled(true);
            btnStopAudioRecorder.setEnabled(false);
            btnStartPlayBackAudioRecorder.setEnabled(true);
        }
    }
}
