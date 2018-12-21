package ungpay.com.androidapplications.MultiMedia;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;

import org.summer.utils.DeviceUtils;
import org.summer.utils.ImageUtils;
import org.summer.utils.ToastUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.unit.DialogHelper;

public class ChapterTwo extends AppCompatActivity implements View.OnClickListener {

    private int cameraBack, cameraUp;
    private Camera camera1;
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private Handler mainHandler, childHandler;
    private ImageReader mImageReader;
    private CameraDevice mCameraDevice;
    private ImageView iv_show;
    private CameraManager cameraManager;
    private CameraCaptureSession mCameraCaptureSession;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    private Button btnDelayTakcPicture;
    private TextView countDown;

    ///为了使照片竖直显示
    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_two);
        this.setTitle(getIntent().getStringExtra("title"));
        surfaceView = findViewById(R.id.surfaceView_camera_one);
        btnDelayTakcPicture = findViewById(R.id.take_picture);
        countDown = findViewById(R.id.count_down);
        btnDelayTakcPicture.setOnClickListener(this);
        iv_show = findViewById(R.id.camera_result_back_show);
        if (DeviceUtils.getSDKVersion() >= 21) {
            PermissionUtils.permission(PermissionConstants.CAMERA)
                    .rationale(new PermissionUtils.OnRationaleListener() {
                        @Override
                        public void rationale(final ShouldRequest shouldRequest) {
                            DialogHelper.showRationaleDialog(shouldRequest);
                        }
                    })
                    .callback(new PermissionUtils.FullCallback() {
                        @Override
                        public void onGranted(List<String> permissionsGranted) {
                            useCameraTwoApi();
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
        } else {
            useCameraOneApi();
        }
    }

    /**
     * 使用Camera1进行开发
     */
    private void useCameraOneApi() {
        cameraBack = Camera.CameraInfo.CAMERA_FACING_BACK;
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(new useCameraOneApi());
    }

    private CountDownTimer countDownTimer;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.take_picture:
                //使用计时器进行延迟拍照
                countDown.setVisibility(View.VISIBLE);
                if (countDownTimer == null) {
                    countDownTimer = new CountDownTimer(3000 + 500, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            countDown.setText((int) millisUntilFinished / 1000 + "");
                        }

                        @Override
                        public void onFinish() {
                            countDown.setVisibility(View.GONE);
                            btnDelayTakcPicture.setVisibility(View.GONE);
                            takePicture();
                        }
                    };
                    countDownTimer.start();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    class useCameraOneApi implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            camera1 = Camera.open(cameraBack);
            try {
                Camera.Parameters parameters = camera1.getParameters();
                if (getResources().getConfiguration().orientation != Configuration
                        .ORIENTATION_LANDSCAPE) {
                    parameters.set("orientation", "portrait");
                    parameters.setRotation(90);
                    camera1.setDisplayOrientation(90);
                } else {
                    parameters.set("orientation", "landscape");
                    parameters.setRotation(0);
                    camera1.setDisplayOrientation(0);

                }
                camera1.setParameters(parameters);
                camera1.setPreviewDisplay(surfaceHolder);
            } catch (IOException e) {
                e.printStackTrace();
                camera1.release();
            }
            camera1.startPreview();
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            camera1.stopPreview();
            camera1.release();
        }
    }

    /**
     * 使用Camera2的api
     */
    private void useCameraTwoApi() {
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setKeepScreenOn(true);
        surfaceHolder.addCallback(new useCameraTwoApi());

    }

    class useCameraTwoApi implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            initCamera2();
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }
    }

    private void initCamera2() {
        HandlerThread handlerThread = new HandlerThread("Camera2");
        handlerThread.start();
        childHandler = new Handler(handlerThread.getLooper());
        mainHandler = new Handler(getMainLooper());
        String backCameraId = CameraCharacteristics.LENS_FACING_BACK + "";
        mImageReader = ImageReader.newInstance(1080, 1920, ImageFormat.JPEG, 1);
        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            //可以在这里处理拍照得到的临时照片 例如，写入本地
            @Override
            public void onImageAvailable(ImageReader reader) {
                mCameraDevice.close();
                surfaceView.setVisibility(View.GONE);
                iv_show.setVisibility(View.VISIBLE);
                // 拿到拍照照片数据
                Image image = reader.acquireNextImage();
                ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);//由缓冲区存入字节数组
                final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                if (bitmap != null) {
                    PermissionUtils.permission(PermissionConstants.STORAGE, PermissionConstants
                            .MICROPHONE).rationale(new PermissionUtils.OnRationaleListener() {


                        @Override
                        public void rationale(ShouldRequest shouldRequest) {
                            DialogHelper.showRationaleDialog(shouldRequest);
                        }
                    }).callback(new PermissionUtils.FullCallback() {
                        @Override
                        public void onGranted(List<String> permissionsGranted) {
                            ImageUtils.save(bitmap, Environment.getExternalStorageDirectory()
                                    .getAbsolutePath
                                            () + "/ChapterTow/test.png", Bitmap.CompressFormat.PNG);
                        }

                        @Override
                        public void onDenied(List<String> permissionsDeniedForever, List<String>
                                permissionsDenied) {
                            if (!permissionsDeniedForever.isEmpty()) {
                                DialogHelper.showOpenAppSettingDialog();
                            }
                            LogUtils.d(permissionsDeniedForever, permissionsDenied);

                        }
                    }).request();
                    iv_show.setImageBitmap(bitmap);
                }
            }
        }, mainHandler);
        //获取摄像头管理
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                    PackageManager.PERMISSION_GRANTED) {
                return;
            }
            //打开摄像头
            cameraManager.openCamera(backCameraId, stateCallback, mainHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 摄像头创建监听
     */
    private CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {//打开摄像头
            mCameraDevice = camera;
            //开启预览
            takePreview();
        }

        @Override
        public void onDisconnected(CameraDevice camera) {//关闭摄像头
            if (null != mCameraDevice) {
                mCameraDevice.close();
                ChapterTwo.this.mCameraDevice = null;
            }
        }

        @Override
        public void onError(CameraDevice camera, int error) {//发生错误
            ToastUtils.showLongToast(ChapterTwo.this, "摄像头开启失败");
        }
    };

    /**
     * 拍照
     */
    private void takePicture() {
        if (mCameraDevice == null) return;
        // 创建拍照需要的CaptureRequest.Builder
        final CaptureRequest.Builder captureRequestBuilder;
        try {
            captureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice
                    .TEMPLATE_STILL_CAPTURE);
            // 将imageReader的surface作为CaptureRequest.Builder的目标
            captureRequestBuilder.addTarget(mImageReader.getSurface());
            // 自动对焦
            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest
                    .CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            // 自动曝光
            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest
                    .CONTROL_AE_MODE_ON_AUTO_FLASH);
            // 获取手机方向
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            // 根据设备方向计算设置照片的方向
            captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));
            //拍照
            CaptureRequest mCaptureRequest = captureRequestBuilder.build();
            mCameraCaptureSession.capture(mCaptureRequest, null, childHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始预览
     */
    private void takePreview() {
        try {
            // 创建预览需要的CaptureRequest.Builder
            final CaptureRequest.Builder previewRequestBuilder = mCameraDevice
                    .createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            // 将SurfaceView的surface作为CaptureRequest.Builder的目标
            previewRequestBuilder.addTarget(surfaceHolder.getSurface());
            // 创建CameraCaptureSession，该对象负责管理处理预览请求和拍照请求
            mCameraDevice.createCaptureSession(Arrays.asList(surfaceHolder.getSurface(),
                    mImageReader.getSurface()), new CameraCaptureSession.StateCallback() // ③
            {
                @Override
                public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                    if (null == mCameraDevice) return;
                    // 当摄像头已经准备好时，开始显示预览
                    mCameraCaptureSession = cameraCaptureSession;
                    try {
                        // 自动对焦
                        previewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest
                                .CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                        // 打开闪光灯
                        previewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest
                                .CONTROL_AE_MODE_ON_AUTO_FLASH);
                        // 显示预览
                        CaptureRequest previewRequest = previewRequestBuilder.build();
                        mCameraCaptureSession.setRepeatingRequest(previewRequest, null,
                                childHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                    ToastUtils.showLongToast(ChapterTwo.this, "配置失败");
                }
            }, childHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
