package ungpay.com.androidapplications.MultiMedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ungpay.com.androidapplications.R;

public class MediaMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_main);
        findViewById(R.id.chapter_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  startActivity(new Intent(MediaMainActivity.this, ChapterOne.class).putExtra
                        ("title", ((Button) findViewById(R.id.chapter_one)).getText()));*/
            }
        });
        findViewById(R.id.chapter_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediaMainActivity.this, ChapterTwo.class).putExtra
                        ("title", ((Button) findViewById(R.id.chapter_two)).getText()));
            }
        });
        findViewById(R.id.chapter_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediaMainActivity.this, ChapterThree.class).putExtra
                        ("title", ((Button) findViewById(R.id.chapter_three)).getText()));
            }
        });
        findViewById(R.id.chapter_four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediaMainActivity.this, ChapterFour.class).putExtra
                        ("title", ((Button) findViewById(R.id.chapter_four)).getText()));
            }
        });
        findViewById(R.id.chapter_five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediaMainActivity.this, ChapterFive.class).putExtra
                        ("title", ((Button) findViewById(R.id.chapter_five)).getText()));
            }
        });
        findViewById(R.id.chapter_six).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediaMainActivity.this, ChapterSix.class).putExtra
                        ("title", ((Button) findViewById(R.id.chapter_six)).getText()));
            }
        });
        findViewById(R.id.chapter_seven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediaMainActivity.this, ChapterSeven.class).putExtra
                        ("title", ((Button) findViewById(R.id.chapter_seven)).getText()));
            }
        });
        findViewById(R.id.chapter_eight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediaMainActivity.this, ChapterEight.class).putExtra
                        ("title", ((Button) findViewById(R.id.chapter_eight)).getText()));
            }
        });
        findViewById(R.id.chapter_nine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediaMainActivity.this, ChapterNine.class).putExtra
                        ("title", ((Button) findViewById(R.id.chapter_nine)).getText()));
            }
        });
        findViewById(R.id.chapter_ten).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediaMainActivity.this, ChapterTen.class).putExtra
                        ("title", ((Button) findViewById(R.id.chapter_ten)).getText()));
            }
        });
        findViewById(R.id.chapter_eleven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediaMainActivity.this, ChapterEleven.class).putExtra
                        ("title", ((Button) findViewById(R.id.chapter_eleven)).getText()));
            }
        });
        findViewById(R.id.chapter_twelve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MediaMainActivity.this, ChapterTwelve.class).putExtra
                        ("title", ((Button) findViewById(R.id.chapter_twelve)).getText()));
            }
        });

    }
}
