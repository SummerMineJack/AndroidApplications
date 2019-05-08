package ungpay.com.androidapplications.AndroidAdvancedDevelopment.AndroidContactPHP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ungpay.com.androidapplications.R;

public class ListUserInfoAdapter extends BaseAdapter {

    private ArrayList<UserBean> userBeans;
    private LayoutInflater inflater;

    public ListUserInfoAdapter(ArrayList<UserBean> userBean, Context mContext) {
        this.userBeans = userBean;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return userBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return userBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder = null;
        if (convertView == null) {
            viewHoder = new ViewHoder();
            convertView = inflater.inflate(R.layout.sel_user_info_layout, null);
            viewHoder.userid = convertView.findViewById(R.id.user_id);
            viewHoder.username = convertView.findViewById(R.id.username);
            viewHoder.userpassword = convertView.findViewById(R.id.userpassword);
            viewHoder.useremail = convertView.findViewById(R.id.user_email);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) convertView.getTag();
        }
        viewHoder.userid.setText(userBeans.get(position).getUserId());
        viewHoder.username.setText(userBeans.get(position).getUserName());
        viewHoder.userpassword.setText(userBeans.get(position).getUserPassword());
        viewHoder.useremail.setText(userBeans.get(position).getUserEmail());
        return convertView;
    }

    class ViewHoder {
        private TextView userid;
        private TextView username;
        private TextView userpassword;
        private TextView useremail;
    }

}
