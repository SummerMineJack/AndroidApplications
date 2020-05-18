package ungpay.com.androidapplications.MultiMedia;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.UtilsTransActivity;


import java.util.List;

import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.unit.DialogHelper;

/**
 * Create by Summer on 2018/12/10
 */
public class ChapterFour extends AppCompatActivity implements View.OnTouchListener, View
        .OnClickListener {

    private ImageView createBitmap_result;
    private float downX, downY;
    private float upX, upY;
    private Canvas canvas;
    private Paint paint;
    private Button btnOpenGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_four);
        this.setTitle(getIntent().getStringExtra("title"));
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        createBitmap_result = findViewById(R.id.createBitmap_result);
        btnOpenGallery = findViewById(R.id.open_gallery);
        btnOpenGallery.setOnClickListener(this);
       /* Bitmap bitmap = Bitmap.createBitmap(ScreenUtils.getScreenWidth(), ScreenUtils
                .getScreenHeight(), Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(Color.GREEN);
        paint = new Paint();
        canvas = new Canvas(bitmap);
        createBitmap_result.setImageBitmap(bitmap);
        createBitmap_result.setOnTouchListener(this);*/
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = motionEvent.getX();
                downY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                upX = motionEvent.getX();
                upY = motionEvent.getY();
                canvas.drawLine(downX, downY, upX, upY, paint);
                createBitmap_result.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                upX = motionEvent.getX();
                upY = motionEvent.getY();
                canvas.drawLine(downX, downY, upX, upY, paint);
                createBitmap_result.invalidate();
                downX = upX;
                downY = upY;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    /**
     * 打开手机自带的相册浏览照片并显示
     */
    private void getGallaryImage() {
        startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media
                .EXTERNAL_CONTENT_URI), 0x001);
    }

    private void custromBitmap(Bitmap bitmap){
        bitmap=bitmap.copy(Bitmap.Config.ARGB_8888,true);
        paint = new Paint();
        canvas = new Canvas(bitmap);
        ImageUtils.save(bitmap,Environment.getExternalStorageDirectory().getAbsolutePath()+"/huangjianimage/hj.png",Bitmap.CompressFormat.PNG);
        createBitmap_result.setImageBitmap(bitmap);
        createBitmap_result.setOnTouchListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0x001:
                    Uri resultDataUri = data.getData();
                    String[] filePathColumns = {MediaStore.Images.Media.DATA};
                    Cursor c = getContentResolver().query(resultDataUri, filePathColumns, null,
                            null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePathColumns[0]);
                    String imagePath = c.getString(columnIndex);
                    Bitmap bitmap = ImageUtils.getBitmap(imagePath);
                    if (bitmap == null) {
                        createBitmap_result.setImageBitmap(BitmapFactory.decodeResource(getResources
                                (), R.mipmap.ic_launcher));
                    } else {
                        custromBitmap(bitmap);
                    }
                    c.close();
                    break;
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_gallery:
                PermissionUtils.permission(PermissionConstants.getPermissions(PermissionConstants
                        .STORAGE)).rationale((activity, shouldRequest) -> DialogHelper.showRationaleDialog(shouldRequest)).callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        getGallaryImage();
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

                break;
        }
    }
}
