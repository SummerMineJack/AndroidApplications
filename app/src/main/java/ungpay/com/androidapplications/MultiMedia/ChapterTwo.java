package ungpay.com.androidapplications.MultiMedia;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
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
import android.os.Handler;
import android.os.HandlerThread;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.unit.DialogHelper;

public class ChapterTwo extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout take_camera_framelayout;
    private ImageView cameraFront;
    private Button btnDelayTakcPicture;
    private TextView countDown;
    private ImageView camera_flash;
    private ImageView camera_result_call_back;
    private SurfaceView surfaceView_camera_one;
    private CameraManager cameraManager;
    private SurfaceHolder surfaceHolder;
    private ImageReader imageReader;
    private Handler childHandler;
    private Handler mainHandler;
    private String backCamera = CameraCharacteristics.LENS_FACING_BACK + "";//后置摄像头
    private String fontCamera = CameraCharacteristics.LENS_FACING_FRONT + "";//前置摄像头
    private String cameraId;
    private CameraDevice cameraDevice;
    private CameraCaptureSession cameraCaptureSession;
    private CaptureRequest.Builder cameraCaptureRequestBuild;
    private RadioGroup rd_group;
    private int delayTackPicture = 3;
    private CountDownTimer countDownTimer;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    private Button btntack_picture;

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
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        surfaceView_camera_one = findViewById(R.id.surfaceView_camera_one);
        take_camera_framelayout = findViewById(R.id.take_camera_framelayout);
        cameraFront = findViewById(R.id.camera_change);
        btnDelayTakcPicture = findViewById(R.id.take_picture);
        countDown = findViewById(R.id.count_down);
        camera_flash = findViewById(R.id.camera_flash);
        btntack_picture = findViewById(R.id.tack_picture);
        camera_result_call_back = findViewById(R.id.camera_result_call_back);
        rd_group = findViewById(R.id.rd_group);
        rd_group.setOnCheckedChangeListener(radioGroupListener);
        cameraFront.setOnClickListener(this);
        btntack_picture.setOnClickListener(this);
        camera_flash.setOnClickListener(this);
        btnDelayTakcPicture.setOnClickListener(this);
        checkPerminsion();
    }

    private void checkPerminsion() {
        PermissionUtils.permission(PermissionConstants.CAMERA).rationale((activity, shouldRequest) -> DialogHelper.showRationaleDialog(shouldRequest)).callback(new PermissionUtils.FullCallback() {
            @Override
            public void onGranted(List<String> permissionsGranted) {
                initSurfaceHolder();
            }

            @Override
            public void onDenied(List<String> permissionsDeniedForever,
                                 List<String> permissionsDenied) {
                if (!permissionsDeniedForever.isEmpty()) {
                    DialogHelper.showOpenAppSettingDialog();
                }
                checkPerminsion();
            }
        }).request();
    }

    private RadioGroup.OnCheckedChangeListener radioGroupListener =
            new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.three_second:
                            delayTackPicture = 3;
                            break;
                        case R.id.five_second:
                            delayTackPicture = 5;
                            break;
                        case R.id.ten_second:
                            delayTackPicture = 10;
                            break;
                    }
                }
            };

    /**
     * 初始化SurfaceHolder
     */
    private void initSurfaceHolder() {
        surfaceHolder = surfaceView_camera_one.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    initCamera(fontCamera);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }


    /**
     * 初始化Camera
     */
    @SuppressLint("MissingPermission")
    private void initCamera(String camera) throws CameraAccessException {
        HandlerThread handlerThread = new HandlerThread("Camera2");
        handlerThread.start();
        childHandler = new Handler(handlerThread.getLooper());
        mainHandler = new Handler(getMainLooper());
        imageReader = ImageReader.newInstance(1920,

                1080 - SizeUtils.getMeasuredHeight(btntack_picture), ImageFormat.JPEG, 1);
        //拍照后得到当前拍的照片进行保存
        imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                Image image = reader.acquireNextImage();
                ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);//由缓冲区存入字节数组
                final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                if (bitmap != null) {
                    camera_result_call_back.setImageBitmap(bitmap);
                    btntack_picture.setVisibility(View.VISIBLE);
                    camera_result_call_back.setVisibility(View.VISIBLE);
                    take_camera_framelayout.setVisibility(View.GONE);
                    closeCamera();
                }
            }
        }, mainHandler);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        cameraManager.openCamera(camera, stateCallback, mainHandler);
    }

    private CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            cameraDevice = camera;
            try {
                startPreview(CaptureRequest.FLASH_MODE_OFF);//进行预览画面
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            camera.close();
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            ToastUtils.showLong("相机已出错");
        }
    };

    /**
     * 进行预览画面
     */
    private void startPreview(final int flashMode) throws CameraAccessException {
        cameraId = cameraDevice.getId();
        cameraCaptureRequestBuild =
                cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
        cameraCaptureRequestBuild.addTarget(surfaceHolder.getSurface());
        cameraDevice.createCaptureSession(Arrays.asList(surfaceHolder.getSurface(),
                imageReader.getSurface()), new CameraCaptureSession.StateCallback() {
            @Override
            public void onConfigured(@NonNull CameraCaptureSession session) {
                cameraCaptureSession = session;
                try {
                    // 自动对焦
                    cameraCaptureRequestBuild.set(CaptureRequest.FLASH_MODE, flashMode);
                    cameraCaptureRequestBuild.set(CaptureRequest.CONTROL_AF_MODE,
                            CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                    // 显示预览
                    CaptureRequest previewRequest = cameraCaptureRequestBuild.build();
                    cameraCaptureSession.setRepeatingRequest(previewRequest, null, childHandler);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                ToastUtils.showLong("Camera配置失败");
            }
        }, childHandler);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_change:
                swtichCamera();
                break;
            case R.id.take_picture:
                //使用计时器进行延迟拍照
                countDown.setVisibility(View.VISIBLE);
                countDownTimer = new CountDownTimer(delayTackPicture * 1000 + 500, 1000) {
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
                break;
            case R.id.camera_flash:
                if (cameraCaptureRequestBuild.get(CaptureRequest.FLASH_MODE).equals(CaptureRequest.FLASH_MODE_TORCH)) {
                    try {
                        startPreview(CaptureRequest.FLASH_MODE_OFF);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                } else if (cameraCaptureRequestBuild.get(CaptureRequest.FLASH_MODE).equals(CaptureRequest.FLASH_MODE_OFF)) {
                    try {
                        startPreview(CaptureRequest.FLASH_MODE_TORCH);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.tack_picture:
                btntack_picture.setVisibility(View.GONE);
                camera_result_call_back.setVisibility(View.GONE);
                take_camera_framelayout.setVisibility(View.VISIBLE);
                btnDelayTakcPicture.setVisibility(View.VISIBLE);
                reopenCamera();
                break;

        }
    }

    /**
     * 拍照
     */
    private void takePicture() {
        if (cameraDevice == null) return;
        // 创建拍照需要的CaptureRequest.Builder
        final CaptureRequest.Builder captureRequestBuilder;
        try {
            captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice
                    .TEMPLATE_STILL_CAPTURE);
            // 将imageReader的surface作为CaptureRequest.Builder的目标
            captureRequestBuilder.addTarget(imageReader.getSurface());
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
            cameraCaptureSession.capture(mCaptureRequest, null, childHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 切换摄像头
     */
    private void swtichCamera() {
        if (cameraId.equals(backCamera)) {
            camera_flash.setVisibility(View.VISIBLE);
            cameraId = fontCamera;
            closeCamera();
            reopenCamera();
        } else if (cameraId.equals(fontCamera)) {
            camera_flash.setVisibility(View.INVISIBLE);
            cameraId = backCamera;
            closeCamera();
            reopenCamera();
        }
    }

    /**
     * 切换摄像头先关闭摄像头
     */
    private void closeCamera() {
        if (imageReader != null) {
            imageReader.close();
            imageReader = null;
        }
        if (cameraDevice != null) {
            cameraDevice.close();
            cameraDevice = null;
        }
        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            cameraCaptureSession = null;
        }
    }

    /**
     * 重新打开Camera
     */
    private void reopenCamera() {
        try {
            initCamera(cameraId);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeCamera();
    }
}
