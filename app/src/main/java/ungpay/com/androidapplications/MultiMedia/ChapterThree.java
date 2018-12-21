package ungpay.com.androidapplications.MultiMedia;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;

import org.summer.utils.ImageUtils;

import java.util.List;

import ungpay.com.androidapplications.R;
import ungpay.com.androidapplications.unit.DialogHelper;

public class ChapterThree extends AppCompatActivity implements View.OnClickListener {

    private Button btnOpenGallery;
    private Button btnCreateBitmap;
    private Button btnMatrixBitmap;
    private Button btnDetailBitmap;
    private ImageView open_gallery_img, open_gallery_imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_three);
        this.setTitle(getIntent().getStringExtra("title"));
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        btnOpenGallery = findViewById(R.id.open_gallery);
        open_gallery_img = findViewById(R.id.open_gallery_img);
        btnCreateBitmap = findViewById(R.id.createBitmap);
        btnMatrixBitmap = findViewById(R.id.MatrixBitmap);
        btnDetailBitmap = findViewById(R.id.detailBitmap);
        open_gallery_imgs = findViewById(R.id.open_gallery_imgs);
        btnDetailBitmap.setOnClickListener(this);
        btnMatrixBitmap.setOnClickListener(this);
        btnCreateBitmap.setOnClickListener(this);
        btnOpenGallery.setOnClickListener(this);
    }

    /**
     * 打开手机自带的相册浏览照片并显示
     */
    private void getGallaryImage() {
        startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media
                .EXTERNAL_CONTENT_URI), 0x001);
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
                        open_gallery_img.setImageBitmap(BitmapFactory.decodeResource(getResources
                                (), R.mipmap.ic_launcher));
                    } else {
                        open_gallery_img.setImageBitmap(bitmap);
                    }
                    c.close();
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_gallery:
                PermissionUtils.permission(PermissionConstants.getPermissions(PermissionConstants
                        .STORAGE)).rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(ShouldRequest shouldRequest) {
                        DialogHelper.showRationaleDialog(shouldRequest);
                    }
                }).callback(new PermissionUtils.FullCallback() {
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
            case R.id.createBitmap:
                createBitmap2Bitmap();
                break;
            case R.id.MatrixBitmap:
                setMatrixBitmap();
                break;
            case R.id.detailBitmap:
                setBtnDetailBitmap();
                break;
        }
    }

    /**
     * 将图片进行处理【灰度，颜色处理】
     */
    private void setBtnDetailBitmap() {
        Drawable drawable = open_gallery_img.getDrawable();
        Bitmap bitmap = ImageUtils.drawable2Bitmap(drawable);
        Bitmap canvasBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap
                .getConfig());
        Canvas canvas = new Canvas(canvasBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.2f);
        colorMatrix.set(new float[]{
                //行：依次是：红，绿，蓝，Alpha，不起作用
                //列：按行来说：红，绿，蓝，Alpha
                10, 0, 0, 0, -30,
                0, 1, 0, 0, -30,
                0, 0, 1, 0, -30,
                0, 0, 0, 30, 0,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, new Matrix(), paint);
        open_gallery_img.setImageBitmap(bitmap);
        open_gallery_imgs.setImageBitmap(canvasBitmap);
    }

    /**
     * 倾斜图片
     */
    private void setMatrixBitmap() {
        Drawable drawable = open_gallery_img.getDrawable();
        Bitmap bitmap = ImageUtils.drawable2Bitmap(drawable);
        open_gallery_img.setImageBitmap(ImageUtils.scale(bitmap, 100, 100));
    }


    /**
     * 在位图上绘制位图
     */
    private void createBitmap2Bitmap() {
        Drawable drawable = open_gallery_img.getDrawable();
        if (drawable != null) {
            Bitmap bitmap = ImageUtils.drawable2Bitmap(drawable);
            Bitmap iconLogoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap
                    .ic_launcher);
            Bitmap bitmap1 = ImageUtils.addImageWatermark(bitmap, iconLogoBitmap, 0, 0, 230);
            Bitmap currentBitmap = ImageUtils.addImageWatermark(bitmap1, iconLogoBitmap,
                    iconLogoBitmap.getWidth(), iconLogoBitmap.getHeight(),
                    200);
            open_gallery_img.setImageBitmap(currentBitmap);
        }
    }
}
