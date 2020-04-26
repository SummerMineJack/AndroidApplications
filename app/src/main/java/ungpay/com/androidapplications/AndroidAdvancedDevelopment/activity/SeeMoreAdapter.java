package ungpay.com.androidapplications.AndroidAdvancedDevelopment.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ungpay.com.androidapplications.R;

class SeeMoreAdapter extends RecyclerView.Adapter<SeeMoreAdapter.SeeMoreViewHolder> {

    private final static int TYPE_NORMAL = 0;//正常条目
    private final static int TYPE_SEE_MORE = 1;//查看更多
    private final static int TYPE_HIDE = 2;//收起
    private List<String> mList;
    private boolean mOpen = false;//是否是展开状态

    SeeMoreAdapter(List<String> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public SeeMoreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
        return new SeeMoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeeMoreViewHolder seeMoreViewHolder, final int position) {
//        TextView textView = (TextView) seeMoreViewHolder.textView;
        if (getItemViewType(position) == TYPE_HIDE) {
            seeMoreViewHolder.textView.setText("收起");
            seeMoreViewHolder.rl_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOpen = false;
                    notifyDataSetChanged();
                }
            });
        } else if (getItemViewType(position) == TYPE_SEE_MORE) {
            seeMoreViewHolder.textView.setText("查看更多");
            seeMoreViewHolder.rl_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOpen = true;
                    notifyDataSetChanged();
                }
            });
        } else {
            seeMoreViewHolder.textView.setText(mList.get(position));
            seeMoreViewHolder.rl_item.setClickable(false);
            if (onItemClick!=null){
                seeMoreViewHolder.rl_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick.onItemClick(position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.size() <= 4) {
            return TYPE_NORMAL;
        }
        if (mOpen) {
            if (position == mList.size()) {
                return TYPE_HIDE;
            } else {
                return TYPE_NORMAL;
            }
        } else {
            if (position == 2) {
                return TYPE_SEE_MORE;
            } else {
                return TYPE_NORMAL;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList.size() > 4) {
            //若现在是展开状态 条目数量需要+1 "收起"条目
            if (mOpen) {
                return mList.size() + 1;
            } else {
                return 3;
            }
        } else {
            return mList.size();
        }
    }

    class SeeMoreViewHolder extends RecyclerView.ViewHolder {
        TextView textView ;
        ImageView iv_item ;
        RelativeLayout rl_item ;


        public SeeMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemView);
            iv_item = itemView.findViewById(R.id.iv_item);
            rl_item = itemView.findViewById(R.id.rl_item);
        }
    }


    public interface OnItemClick{
        void onItemClick(int position);
    }
    private OnItemClick onItemClick;
    public void setOnITEMClickListener(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

}

