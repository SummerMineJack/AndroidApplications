package ungpay.com.androidapplications.AndroidAdvancedDevelopment.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import ungpay.com.androidapplications.R;

public class Fragment1 extends Fragment {

    private TextView parmas;
    private String argument;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View views = inflater.inflate(R.layout.fragment_fragment1, container, false);
        parmas = views.findViewById(R.id.parmas);
        return views;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parmas.setText(argument);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            argument = bundle.getString("params");
        }
    }

    public static Fragment1 newIntance(String activityParams) {
        Fragment1 fragment1 = new Fragment1();
        Bundle bundle = new Bundle();
        bundle.putString("params", activityParams);
        fragment1.setArguments(bundle);
        return fragment1;
    }

}
