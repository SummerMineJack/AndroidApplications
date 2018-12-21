package ungpay.com.androidapplications.MultiMedia;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import ungpay.com.androidapplications.R;

public class ChapterSixService extends Service implements MediaPlayer.OnCompletionListener {
    private MediaPlayer mediaPlayer;
    private final IBinder iBinder = new ChapterSixServiceBinder();

    public ChapterSixService() {
    }

    public class ChapterSixServiceBinder extends Binder {
        ChapterSixService getService() {
            return ChapterSixService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("~~", "Service is Created");
        mediaPlayer = MediaPlayer.create(this, R.raw.popdanthology);
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("~~", "Service is Destory");
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    public void haveFun() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 2500);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("~~", "Service is onStartCommand");
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        stopSelf();
    }
}
