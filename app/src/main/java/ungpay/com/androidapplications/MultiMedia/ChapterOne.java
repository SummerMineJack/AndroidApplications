package ungpay.com.androidapplications.MultiMedia;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.unit.DialogHelper;

/**
 * 打开相机并拍照获取拍照的图像
 */
public class ChapterOne extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView;
    private ImageView imageView;
    private Button btnOpenCamera;
    private Button btnOpenFileManager;
    private String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/huangjianshows/takePhone.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_one);
        this.setTitle(getIntent().getStringExtra("title"));
        imageView = findViewById(R.id.camera_result_img);
        btnOpenCamera = findViewById(R.id.open_camera);
        btnOpenCamera.setOnClickListener(this);
        btnOpenFileManager = findViewById(R.id.get_picture);
        btnOpenFileManager.setOnClickListener(this);
    }

    /**
     * 使用系统相机拍照后的图进行保存
     *
     * @param bitmap
     */
    private void saveFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        InputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        boolean isSuccessSave = FileIOUtils.writeFileFromIS(filePath, isBm, false);
        if (isSuccessSave) {
            ToastUtils.showLong("保存成功");
        } else {
            ToastUtils.showLong("保存失败");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0x001:
                    //这样获得的图片很小
                    Log.e("~~~~~~~~~~~~~~~~~~~", "" + data);
                    mImageView = findViewById(R.id.camera_result_img);
                    mImageView.setImageBitmap((Bitmap) data.getExtras().get("data"));
                    saveFile((Bitmap) data.getExtras().get("data"));
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_camera:
                if (DeviceUtils.getSDKVersionCode() >= 23) {
                    checkPermission();
                } else {
                    startActivityForResult(new Intent(MediaStore
                            .ACTION_IMAGE_CAPTURE), 0x001);
                }
                break;
            case R.id.get_picture:
                getPicture();
                break;
        }
    }

    /**
     * Api大于23时进行权限检测
     */
    private void checkPermission() {
        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        DialogHelper.showRationaleDialog(shouldRequest);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        startActivityForResult(new Intent(MediaStore
                                .ACTION_IMAGE_CAPTURE), 0x001);
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

    /**
     * 从本地获取图片并显示
     */
    private void getPicture() {
        ToastUtils.showLong("设置成功");
        imageView.setImageBitmap(ImageUtils.getBitmap(filePath, 800, 800));
    }
}
