package ungpay.com.androidapplications.MultiMedia;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ungpay.com.androidapplications.databinding.ActivityMediaMainBinding;

public class MediaMainActivity extends AppCompatActivity {
    private ActivityMediaMainBinding activityMediaMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMediaMainBinding = ActivityMediaMainBinding.inflate(LayoutInflater.from(this));
        setContentView(activityMediaMainBinding.getRoot());
        activityMediaMainBinding.chapterOne.setOnClickListener(view -> startActivityCustom(ChapterOne.class, activityMediaMainBinding.chapterOne));
        activityMediaMainBinding.chapterTwo.setOnClickListener(view -> startActivityCustom(ChapterTwo.class, activityMediaMainBinding.chapterTwo));
        activityMediaMainBinding.chapterThree.setOnClickListener(view -> startActivityCustom(ChapterThree.class, activityMediaMainBinding.chapterThree));
        activityMediaMainBinding.chapterFour.setOnClickListener(view -> startActivityCustom(ChapterFour.class, activityMediaMainBinding.chapterFour));
        activityMediaMainBinding.chapterFive.setOnClickListener(view -> startActivityCustom(ChapterFive.class, activityMediaMainBinding.chapterFive));
        activityMediaMainBinding.chapterSix.setOnClickListener(view -> startActivityCustom(ChapterSix.class, activityMediaMainBinding.chapterSix));
        activityMediaMainBinding.chapterSeven.setOnClickListener(view -> startActivityCustom(ChapterSeven.class, activityMediaMainBinding.chapterSeven));
        activityMediaMainBinding.chapterEight.setOnClickListener(view -> startActivityCustom(ChapterEight.class, activityMediaMainBinding.chapterEight));
        activityMediaMainBinding.chapterNine.setOnClickListener(view -> startActivityCustom(ChapterNine.class, activityMediaMainBinding.chapterNine));
        activityMediaMainBinding.chapterTen.setOnClickListener(view -> startActivityCustom(ChapterTen.class, activityMediaMainBinding.chapterTen));
        activityMediaMainBinding.chapterEleven.setOnClickListener(view -> startActivityCustom(ChapterEleven.class, activityMediaMainBinding.chapterEleven));
        activityMediaMainBinding.chapterTwelve.setOnClickListener(view -> startActivityCustom(ChapterTwelve.class, activityMediaMainBinding.chapterTwelve));
    }

    private void startActivityCustom(Class activity, Button content) {
        startActivity(new Intent(MediaMainActivity.this, activity).putExtra("title", content.getText().toString()));
    }
}
