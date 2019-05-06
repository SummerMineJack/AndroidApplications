package ungpay.com.androidapplications.AndroidAdvancedDevelopment.ViewStubMergeInclude;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ungpay.com.androidapplications.R;

public class LayoutMainactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_mainactivity);
        View view = findViewById(R.id.my_title_parent_id);
        //这里会报空值异常
        TextView titleTextView = view.findViewById(R.id.title_tv);
        titleTextView.setText("sdasd");
    }
}
