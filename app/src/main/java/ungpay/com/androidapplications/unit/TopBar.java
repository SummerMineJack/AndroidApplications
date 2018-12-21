package ungpay.com.androidapplications.unit;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import ungpay.com.androidapplications.R;


/**
 * @author haixin
 */
public class TopBar extends LinearLayout {

    private Button titleButton;
    private TextView leftText;
    private TextView rightText;

    private ImageButton leftButton;
    private ImageButton rightButton;

    public TopBar(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    public TopBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.activity_topbar, this);
        titleButton = this.findViewById(R.id.titleButton);
        leftText = this.findViewById(R.id.leftText);
        rightText = this.findViewById(R.id.rightText);
        leftButton = this.findViewById(R.id.leftButton);
        rightButton = this.findViewById(R.id.rightButton);
    }

    public Button getTitleButton() {
        return titleButton;
    }

    public TextView getLeftText() {
        return leftText;
    }

    public TextView getRightText() {
        return rightText;
    }

    public ImageButton getLeftButton() {
        return leftButton;
    }

    public ImageButton getRightButton() {
        return rightButton;
    }


    public void OnTitleTextClickListener(OnClickListener listener) {
        getTitleButton().setOnClickListener(listener);
    }

    public void OnLeftTextClickListener(OnClickListener listener) {
        getLeftText().setOnClickListener(listener);
    }

    public void OnRightTextClickListener(OnClickListener listener) {
        getRightText().setOnClickListener(listener);
    }

    public void OnLeftButtonClickListener(OnClickListener listener) {
        getLeftButton().setOnClickListener(listener);
    }

    public void OnRightButtonClickListener(OnClickListener listener) {
        getRightButton().setOnClickListener(listener);
    }

}
