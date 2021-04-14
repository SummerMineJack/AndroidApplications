package ungpay.com.androidapplications.MultiMedia;

import android.Manifest;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.PathUtils;

import java.io.File;

import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.databinding.ActivityChapterOneBinding;

/**
 * 打开相机并拍照获取拍照的图像
 */
public class ChapterOne extends AppCompatActivity implements View.OnClickListener {
    private ActivityChapterOneBinding activityChapterOneBinding;
    private Uri imageUri;
    private ActivityResultLauncher requestOpenCameraLauncher = registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> {
        if (result) {
            activityChapterOneBinding.cameraResultImg.setImageURI(imageUri);
        }
    });
    private ActivityResultLauncher requestCameraLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
        File file = createPicFile();
        imageUri = FileProvider.getUriForFile(ChapterOne.this, AppUtils.getAppPackageName() + ".fileprovider", file);
        requestOpenCameraLauncher.launch(imageUri);
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChapterOneBinding = ActivityChapterOneBinding.inflate(LayoutInflater.from(this));
        setContentView(activityChapterOneBinding.getRoot());
        activityChapterOneBinding.openCamera.setOnClickListener(this);
        activityChapterOneBinding.getPicture.setOnClickListener(this);
    }


    /**
     * 生成拍照所得的照片文件
     *
     * @return
     */
    private File createPicFile() {
        return new File(PathUtils.getExternalAppPicturesPath() + "/name.jpg");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_camera:
                requestCameraLauncher.launch(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE});
                break;
            case R.id.get_picture:
                Bitmap bitmap = ImageUtils.getBitmap(PathUtils.getExternalAppPicturesPath() + "/name.jpg");
                activityChapterOneBinding.cameraResultSet.setImageBitmap(bitmap);
                break;
        }
    }
}