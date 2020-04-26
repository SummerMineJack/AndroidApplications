package ungpay.com.androidapplications.AndroidAdvancedDevelopment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ungpay.com.androidapplications.R;

public class TestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.rv_main);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("哈萨ki" + i);
        }
        SeeMoreAdapter seeMoreAdapter = new SeeMoreAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(seeMoreAdapter);


        seeMoreAdapter.setOnITEMClickListener(new SeeMoreAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                int mPosition = position + 1;
                Toast.makeText(getBaseContext(),"点击了第"+ mPosition +"个哈萨ki",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
