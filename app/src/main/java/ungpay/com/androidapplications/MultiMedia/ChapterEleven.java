package ungpay.com.androidapplications.MultiMedia;

import android.content.ContentValues;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UtilsTransActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.unit.DialogHelper;

/**
 * 视频捕获
 */
public class ChapterEleven extends AppCompatActivity implements OnClickListener, OnErrorListener {

    private Button btnuUseIntent2RecordVideo, btnuUseIntent2Play, btnSaveRecorderVideo, btnVideoStartRecorder, btnVideoStopRecorder, btnVideoPlayRecorder;
    private VideoView videoView;
    private Uri recorderVideoUri;

    //录制
    private MediaRecorder mediaRecorder;
    private String videoFilePath;
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private boolean isRecording = false;
    private boolean isPlaying = false;
    private boolean isOpenCamera = false;
    private String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ChapterEleven";
    private File videoFile;
    private Camera mCamera;
    private int cameraPosition = Camera.CameraInfo.CAMERA_FACING_FRONT;//当前摄像头
    private int videoWidth = 0;// 视频分辨率宽度
    private int videoHeight = 0;// 视频分辨率高度
    public Camera.Size pictureSize;//照片分辨率
    private Camera.Size previewSize;//预览分辨率
    private  VideoView recorderVidePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_eleven);
        this.setTitle(getIntent().getStringExtra("title"));
        initView();
    }

    /**
     * s
     * 初始化视图
     */
    private void initView() {
        btnuUseIntent2RecordVideo = findViewById(R.id.useIntent2RecordVideo);
        btnuUseIntent2Play = findViewById(R.id.useIntent2Play);
        videoView = findViewById(R.id.intentVideView);
        btnSaveRecorderVideo = findViewById(R.id.saveRecorderVideo);
        btnVideoStartRecorder = findViewById(R.id.startRecorder);
        btnVideoStopRecorder = findViewById(R.id.stopRecorder);
        btnVideoPlayRecorder = findViewById(R.id.playRecorder);
        btnVideoPlayRecorder.setOnClickListener(this);
        btnVideoStopRecorder.setOnClickListener(this);
        btnVideoStartRecorder.setOnClickListener(this);
        btnSaveRecorderVideo.setOnClickListener(this);
        btnuUseIntent2Play.setEnabled(false);
        btnSaveRecorderVideo.setEnabled(false);
        btnuUseIntent2Play.setOnClickListener(this);
        btnuUseIntent2RecordVideo.setOnClickListener(this);
        initMediaRecorder();

    }

    /**
     * 初始化MediaRecorder
     */
    private void initMediaRecorder() {
        surfaceView = findViewById(R.id.CustomVideo);
        recorderVidePlayer=findViewById(R.id.recordingVideoPlayer);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(callbacks);
    }

    /**
     * Holder准备的回调监听
     */
    private SurfaceHolder.Callback callbacks = new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (mCamera != null) {
                mCamera.stopPreview();//停掉原来摄像头的预览
                mCamera.release();//释放资源
                mCamera = null;//取消原来摄像头
            }
            int i = Camera.getNumberOfCameras();
            //如果只有一个摄像头
            if (i == 1) {
                mCamera = Camera.open();
            } else {
                mCamera = Camera.open(cameraPosition);
            }
            isOpenCamera = true;
            try {
                setCameraParms();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            try {
                stopRecorder();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (mCamera != null) {
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }
        }
    };

    private float calcPreviewPercent() {
        float d = ScreenUtils.getScreenHeight();
        return d / ScreenUtils.getScreenWidth();
    }

    private Camera.Size findSizeFromList(List<Camera.Size> supportedPictureSizes, Camera.Size size) {
        Camera.Size s = null;
        if (supportedPictureSizes != null && !supportedPictureSizes.isEmpty()) {
            for (Camera.Size su : supportedPictureSizes) {
                if (size.width == su.width && size.height == su.height) {
                    s = su;
                    break;
                }
            }
        }
        return s;
    }

    // 根据摄像头的获取与屏幕分辨率最为接近的一个分辨率
    private Camera.Size getPictureMaxSize(List<Camera.Size> l, Camera.Size size) {
        Camera.Size s = null;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).width >= size.width && l.get(i).height >= size.width
                    && l.get(i).height != l.get(i).width) {
                if (s == null) {
                    s = l.get(i);
                } else {
                    if (s.height * s.width > l.get(i).width * l.get(i).height) {
                        s = l.get(i);
                    }
                }
            }
        }
        return s;
    }

    // 获取预览的最大分辨率
    private Camera.Size getPreviewMaxSize(List<Camera.Size> l, float j) {
        int idx_best = 0;
        int best_width = 0;
        float best_diff = 100.0f;
        for (int i = 0; i < l.size(); i++) {
            int w = l.get(i).width;
            int h = l.get(i).height;
            if (w * h < ScreenUtils.getScreenHeight() * ScreenUtils.getScreenWidth())
                continue;
            float previewPercent = (float) w / h;
            float diff = Math.abs(previewPercent - j);
            if (diff < best_diff) {
                idx_best = i;
                best_diff = diff;
                best_width = w;
            } else if (diff == best_diff && w > best_width) {
                idx_best = i;
                best_diff = diff;
                best_width = w;
            }
        }
        return l.get(idx_best);
    }

    private void setCameraParms() throws IOException {
        mCamera.setPreviewDisplay(surfaceHolder);
        Camera.Parameters myParam = mCamera.getParameters();
        List<String> flashModes = myParam.getSupportedFlashModes();
        String flashMode = myParam.getFlashMode();
        // Check if camera flash exists
        if (flashModes != null && !Camera.Parameters.FLASH_MODE_OFF.equals(flashMode)) {
            // Turn off the flash
            if (flashModes.contains(Camera.Parameters.FLASH_MODE_OFF)) {
                myParam.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            }
        }

        float percent = calcPreviewPercent();
        List<Camera.Size> supportedPreviewSizes = myParam.getSupportedPreviewSizes();
        //因为有些手机videoSizes不提供  比如华为mate7  那样的话就只能拿照相机预览数据了 当然预览数据尺寸不知道能不能当视频录制尺寸
        List<Camera.Size> videoSizes = mCamera.getParameters().getSupportedVideoSizes();
        previewSize = getPreviewMaxSize(supportedPreviewSizes, percent);
        Log.e("~~", "预览尺寸w===" + previewSize.width + ",h==="
                + previewSize.height);
        if (videoSizes != null) {
            for (Camera.Size _s : videoSizes) {
                float videoS = (float) _s.width / _s.height;
                if (videoS == percent && _s.width < previewSize.width && _s.height < previewSize.height) {
                    videoWidth = _s.width;
                    videoHeight = _s.height;
                    continue;
                }
            }
        }
        if (videoWidth == 0 && videoHeight == 0) {
            //如果没有拿到值  那就是当前的预览数据了
            videoWidth = previewSize.width;
            videoHeight = previewSize.height;
        }
        Log.e("~~", "录像尺寸w===" + videoWidth + ",h==="
                + videoHeight);
        // 获取摄像头支持的各种分辨率
        List<Camera.Size> supportedPictureSizes = myParam.getSupportedPictureSizes();
        pictureSize = findSizeFromList(supportedPictureSizes, previewSize);
        if (pictureSize == null) {
            pictureSize = getPictureMaxSize(supportedPictureSizes, previewSize);
        }
        Log.e("~~", "照片尺寸w===" + pictureSize.width + ",h==="
                + pictureSize.height);
        // 设置照片分辨率，注意要在摄像头支持的范围内选择
        myParam.setPictureSize(pictureSize.width, pictureSize.height);
        // 设置预浏尺寸，注意要在摄像头支持的范围内选择
        myParam.setPreviewSize(previewSize.width, previewSize.height);
        myParam.setJpegQuality(70);
        mCamera.setParameters(myParam);
        mCamera.setDisplayOrientation(90);
        mCamera.startPreview();
        mCamera.cancelAutoFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.useIntent2RecordVideo:
                PermissionUtils.permission(PermissionConstants.getPermissions(PermissionConstants.CAMERA)).rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(UtilsTransActivity activity, ShouldRequest shouldRequest) {
                        DialogHelper.showRationaleDialog(shouldRequest);
                    }

                }).callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        intent2RecordVideo();
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
            case R.id.useIntent2Play:
                play2RecorderVideo();
                break;
            case R.id.saveRecorderVideo:
                PermissionUtils.permission(PermissionConstants.getPermissions(PermissionConstants.STORAGE)).rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(UtilsTransActivity activity, ShouldRequest shouldRequest) {
                        DialogHelper.showRationaleDialog(shouldRequest);
                    }

                }).callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        saveRecorderVideo();
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
            case R.id.startRecorder:
                try {
                    startRecorder();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.stopRecorder:
                stopRecorder();
                break;
            case R.id.playRecorder:
                playRecorder();
                break;
        }
    }

    /**
     * 开始录制视频
     */
    private void startRecorder() throws IOException {
        createRecorderDir();
        initRecord();
        isRecording = true;
    }

    /**
     * 初始化Recorder配置
     */
    private void initRecord() throws IOException {
//必须解锁  不然会报错
        mCamera.unlock();
        mediaRecorder = new MediaRecorder();
        mediaRecorder.reset();
        if (mCamera != null)
            mediaRecorder.setCamera(mCamera);
        mediaRecorder.setOnErrorListener(this);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        //后置摄像头是旋转90度
        if (cameraPosition == 0) {
            mediaRecorder.setOrientationHint(90);// 输出旋转90度，保持竖屏录制
        } else {
            //前置摄像头是旋转270度
            mediaRecorder.setOrientationHint(270);
        }
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);// 视频输出格式
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);// 音频格式
        //H264用得最广  基本每个手机都支持  反倒其他的没有每个都支持
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mediaRecorder.setVideoSize(videoWidth, videoHeight);// 设置分辨率
        mediaRecorder.setVideoEncodingBitRate(2 * 1024 * 1024);// 设置帧频率，然后就清晰了

        // 设置录制的视频帧率。必须放在设置编码和格式的后面，否则报错
        // mediaRecorder.setVideoFrameRate(20);

        // mediaRecorder.setMaxDuration(Constant.MAXVEDIOTIME * 1000);
        mediaRecorder.setOutputFile(videoFile.getAbsolutePath());

        mediaRecorder.prepare();
        mediaRecorder.start();
    }

    /**
     * 创建录制的文件
     */
    private void createRecorderDir() throws IOException {
        File sampleDir = new File(filePath);
        if (!sampleDir.exists()) {
            sampleDir.mkdirs();
        }
        File vecordDir = sampleDir;
        // 创建文件
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日_HH_mm_ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        videoFile = File.createTempFile(str, ".mp4", vecordDir);//mp4格式
    }

    /**
     * 停止录制视频
     */
    private void stopRecorder() {
        if (mediaRecorder != null) {
            // 设置后不会崩
            mediaRecorder.setOnErrorListener(null);
            mediaRecorder.setPreviewDisplay(null);
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
        isRecording = false;
    }

    /**
     * 开始播放录制的视频
     */
    private void playRecorder() {
        recorderVidePlayer.setVideoPath(videoFile.getAbsolutePath());
        recorderVidePlayer.start();
    }

    /**
     * 保存录制的视频
     */
    private void saveRecorderVideo() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.TITLE, "This is just recorder video");
        if (getContentResolver().update(recorderVideoUri, contentValues, null, null) == 1) {
            ToastUtils.showLong("更新Video信息成功");
        } else {
            ToastUtils.showLong("更新失败");
        }
    }

    /**
     * 播放刚刚录制的视频
     */
    private void play2RecorderVideo() {
        videoView.setVideoURI(recorderVideoUri);
        videoView.setMediaController(new MediaController(this));
        videoView.start();
    }

    /**
     * 使用intent进行录制视频
     */
    private void intent2RecordVideo() {
        startActivityForResult(new Intent(MediaStore.ACTION_VIDEO_CAPTURE), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    recorderVideoUri = data.getData();
                    btnuUseIntent2Play.setEnabled(true);
                    btnSaveRecorderVideo.setEnabled(true);
                    btnuUseIntent2RecordVideo.setEnabled(false);
                    videoView.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    public void onError(MediaRecorder mr, int what, int extra) {

    }
}
