package ungpay.com.androidapplications.AndroidAdvancedDevelopment.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    public void onBindViewHolder(@NonNull SeeMoreViewHolder seeMoreViewHolder, @SuppressLint("RecyclerView") final int position) {
//        TextView textView = (TextView) seeMoreViewHolder.textView;
        if (getItemViewType(position) == TYPE_HIDE) {
            seeMoreViewHolder.textView.setText("收起");
            seeMoreViewHolder.imgPullDown.setVisibility(View.VISIBLE);
            seeMoreViewHolder.iv_item.setVisibility(View.GONE);
            seeMoreViewHolder.textView.setTextColor(Color.parseColor("#3E81E5"));
            seeMoreViewHolder.llTextViewImage.setGravity(Gravity.CENTER);
            seeMoreViewHolder.rl_item.setOnClickListener(v -> {
                mOpen = false;
                notifyDataSetChanged();
            });
        } else if (getItemViewType(position) == TYPE_SEE_MORE) {
            seeMoreViewHolder.textView.setText("查看更多");
            seeMoreViewHolder.imgPullDown.setVisibility(View.VISIBLE);
            seeMoreViewHolder.iv_item.setVisibility(View.GONE);
            seeMoreViewHolder.textView.setTextColor(Color.parseColor("#3E81E5"));
            seeMoreViewHolder.llTextViewImage.setGravity(Gravity.CENTER);
            seeMoreViewHolder.rl_item.setOnClickListener(v -> {
                mOpen = true;
                notifyDataSetChanged();
            });
        } else {
            seeMoreViewHolder.textView.setText(mList.get(position));
            seeMoreViewHolder.rl_item.setClickable(false);
            if (onItemClick != null) {
                seeMoreViewHolder.rl_item.setOnClickListener(v -> onItemClick.onItemClick(position));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        int beanSize = mList.size();
        if (beanSize == 1) {
            return TYPE_NORMAL;
        } else {
            if (mOpen) {
                if (position == mList.size()) {
                    return TYPE_HIDE;
                } else {
                    return TYPE_NORMAL;
                }
            } else {
                if (beanSize == 2) {
                    if (position == beanSize) {
                        return TYPE_SEE_MORE;
                    } else {
                        return TYPE_NORMAL;
                       /* if (position == beanSize - 1) {
                            return TYPE_SEE_MORE;
                        } else {
                            return TYPE_NORMAL;
                        }*/
                    }
                } else {
                    if (position == 2) {
                        return TYPE_SEE_MORE;
                    } else {
                        return TYPE_NORMAL;
                    }
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        int size = mList.size();
        if (size == 1) {
            return size;
        } else {
            if (mOpen) {
                return size + 1;
            } else {
                if (size == 2) {
                    return size;
                } else {
                    return 3;
                }
            }
        }
    }

    class SeeMoreViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView iv_item;
        ImageView imgPullDown;
        LinearLayout rl_item;
        LinearLayout llTextViewImage;


        public SeeMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemView);
            iv_item = itemView.findViewById(R.id.iv_item);
            rl_item = itemView.findViewById(R.id.rl_item);
            llTextViewImage = itemView.findViewById(R.id.ll_text_image);
            imgPullDown = itemView.findViewById(R.id.img_pull_down);
        }
    }


    public interface OnItemClick {
        void onItemClick(int position);
    }

    private OnItemClick onItemClick;

    public void setOnITEMClickListener(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}

