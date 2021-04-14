package ungpay.com.androidapplications.MultiMedia;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.util.ca.uol.aig.fftpack.RealDoubleFFT;

/**
 * 播放合成音频
 */
public class ChapterEight extends AppCompatActivity implements View.OnClickListener, OnTouchListener {

    private Button btnStartSound, btnEndSound, btnStartStopButton;
    private ImageView btnImageShowZhenfu;
    private static float BASE_FREQUENCY = 440;
    private boolean keepGoing = false;
    private LinearLayout linearLayout;
    private float synth_frquency = BASE_FREQUENCY;
    private AudioSoundTask audioSoundTask;
    private boolean play = false;

    private RealDoubleFFT transformer;
    private int blockSize = 256;
    private boolean isStarted = false;
    private RecorderAudioTask recorderAudioTask;
    private Canvas canvas;
    private Paint paint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_eight);
        this.setTitle(getIntent().getStringExtra("title"));
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        btnStartSound = findViewById(R.id.StartSound);
        btnEndSound = findViewById(R.id.EndSound);
        linearLayout = findViewById(R.id.use_finnger_sound);
        btnStartStopButton = findViewById(R.id.StartStopButton);
        btnImageShowZhenfu = findViewById(R.id.zhenfu_img);
        btnStartStopButton.setOnClickListener(this);
        linearLayout.setOnTouchListener(this);
        btnEndSound.setEnabled(false);
        btnStartSound.setOnClickListener(this);
        btnEndSound.setOnClickListener(this);
//        audioSoundTask = new AudioSoundTask();
//        audioSoundTask.execute();
        transformer = new RealDoubleFFT(blockSize);
        Bitmap bitmap = Bitmap.createBitmap(256, 200, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setColor(Color.GREEN);
        btnImageShowZhenfu.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.StartSound:
                startSund();
                break;
            case R.id.EndSound:
                endSound();
                break;
            case R.id.StartStopButton:
                canSeeZhenfuBtn();
                break;
        }
    }

    /**
     * 将声音振幅可视化操作
     */
    private void canSeeZhenfuBtn() {
        if (isStarted) {
            isStarted = false;
            btnStartStopButton.setText("Start");
            recorderAudioTask.cancel(true);
        } else {
            isStarted = true;
            btnStartStopButton.setText("Stop");
            recorderAudioTask = new RecorderAudioTask();
            recorderAudioTask.execute();
        }

    }

    /**
     * 开始播放合成音频
     */
    private void startSund() {
        keepGoing = true;
        AudioSoundTask audioSoundTask = new AudioSoundTask();
        audioSoundTask.execute();
        btnStartSound.setEnabled(false);
        btnEndSound.setEnabled(true);
    }

    /**
     * 停止播放合成音频
     */
    private void endSound() {
        keepGoing = false;
        btnEndSound.setEnabled(false);
        btnStartSound.setEnabled(true);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                play = true;
                synth_frquency = event.getX() + BASE_FREQUENCY;
                Log.e("~~~~~~~~~", synth_frquency + "");
                break;
            case MotionEvent.ACTION_UP:
                play = false;
                break;
        }
        return true;
    }

    /**
     * 将声音的振幅进行可视化操作
     */

    private class RecorderAudioTask extends AsyncTask<Void, double[], Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            int sampleHzSize = 8000;
            int bufferSize = AudioRecord.getMinBufferSize(sampleHzSize, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT);
            AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleHzSize, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);
            short[] buffer = new short[blockSize];
            double[] transformers = new double[blockSize];
            audioRecord.startRecording();
            while (isStarted) {
                int bufferReadResult = audioRecord.read(buffer, 0, blockSize);
                for (int i = 0; i < blockSize && i < bufferReadResult; i++) {
                    transformers[i] = buffer[i] / 32768.0;
                }
                transformer.ft(transformers);
                publishProgress(transformers);
            }
            audioRecord.stop();
            return null;
        }

        @Override
        protected void onProgressUpdate(double[]... values) {
            canvas.drawColor(Color.BLACK);
            for (int i = 0; i < values[0].length; i++) {
                int x = i;
                int downY = (int) (100 - (values[0][i] * 10));
                int upY = 100;
                canvas.drawLine(x, downY, x, upY, paint);
            }
            btnImageShowZhenfu.invalidate();
        }
    }

    /**
     * 根据View的滑动操作进行调整出声音的振幅
     */
    private class AudioSoundTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            final int sampleHzSize = 11025;
            int bufferSize = AudioTrack.getMinBufferSize(sampleHzSize, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT);
            AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleHzSize, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize, AudioTrack.MODE_STREAM);
            audioTrack.play();
            short[] buffer = new short[bufferSize];
            Log.e("~~~~~~short", buffer + "");
            float anguar_frequarey = (float) (2 * Math.PI) * BASE_FREQUENCY / sampleHzSize;
            float angle = 0;
            while (true) {
                if (play) {
                    for (int i = 0; i < buffer.length; i++) {
                        buffer[i] = (short) (Short.MAX_VALUE * ((float) Math.sin(angle)));
                        angle += anguar_frequarey;
                    }
                    audioTrack.write(buffer, 0, buffer.length);
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
