package ungpay.com.androidapplications.alipaycard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.animation.AccelerateDecelerateInterpolator;

public abstract class AnimatorAdapter {
    static final int ANIMATION_DURATION = 400;

    protected CardStackView mCardStackView;
    protected AnimatorSet mSet;

    public AnimatorAdapter(CardStackView cardStackView) {
        mCardStackView = cardStackView;
    }

    protected void initAnimatorSet() {
        mSet = new AnimatorSet();
        mSet.setInterpolator(new AccelerateDecelerateInterpolator());
        mSet.setDuration(getDuration());
    }

    public void itemClick(final CardStackView.ViewHolder viewHolder, int position) {
        if (mSet != null && mSet.isRunning()) return;
        initAnimatorSet();
        if (mCardStackView.isExpending()) {
            onItemCollapse(viewHolder);
        } else {
            onItemExpand(viewHolder, position);
        }
       /* if (mCardStackView.getSelectPosition() == position) {
            onItemCollapse(viewHolder);
        } else {
            onItemExpand(viewHolder, position);
        }*/
        if (mCardStackView.getChildCount() == 1)
            mSet.end();
    }

    protected abstract void itemExpandAnimatorSet(CardStackView.ViewHolder viewHolder, int position);

    protected abstract void itemCollapseAnimatorSet(CardStackView.ViewHolder viewHolder);

    private void onItemExpand(final CardStackView.ViewHolder viewHolder, int position) {
        final int preSelectPosition = mCardStackView.getSelectPosition();
        final CardStackView.ViewHolder preSelectViewHolder = mCardStackView.getViewHolder(preSelectPosition);
        if (preSelectViewHolder != null) {
            preSelectViewHolder.onItemExpand(false);
        }
        mCardStackView.setSelectPosition(position);
        itemExpandAnimatorSet(viewHolder, position);
        mSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mCardStackView.setScrollEnable(false);
                if (preSelectViewHolder != null) {
                    preSelectViewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_START, false);
                }
                viewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_START, true);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewHolder.onItemExpand(true);
                if (preSelectViewHolder != null) {
                    preSelectViewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_END, false);
                }
                viewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_END, true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                if (preSelectViewHolder != null) {
                    preSelectViewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_CANCEL, false);
                }
                viewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_CANCEL, true);
            }
        });
        mSet.start();
    }

    private void onItemCollapse(final CardStackView.ViewHolder viewHolder) {
        itemCollapseAnimatorSet(viewHolder);
        mSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                viewHolder.onItemExpand(false);
                mCardStackView.setScrollEnable(true);
                viewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_START, false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mCardStackView.setSelectPosition(CardStackView.DEFAULT_SELECT_POSITION);
                viewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_END, false);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                viewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_CANCEL, false);
            }
        });
        mSet.start();
    }

    /**
     * 设置动画偏移量方法
     *
     * @param collapseShowItemCount
     * @return
     */
    protected int getCollapseStartTop(int collapseShowItemCount) {
        int returnNumber;
        int numBottomShow = mCardStackView.getNumBottomShow();
        int cardChildCount = mCardStackView.getChildCount();
        int cardSelectPosition = mCardStackView.getSelectPosition();
        int overlapGapsCollapse = mCardStackView.getOverlapGapsCollapse();
        if (numBottomShow - (cardChildCount - cardSelectPosition) > numBottomShow) {
            returnNumber = numBottomShow;
        } else {
            returnNumber = cardChildCount - cardSelectPosition - 1;
        }
        returnNumber = overlapGapsCollapse * (numBottomShow - collapseShowItemCount - returnNumber);
        return returnNumber;
    }


    /**
     * 设置动画偏移量方法
     *
     * @param collapseShowItemCount
     * @return
     */
    protected int getCollapseStartTopCopy(int collapseShowItemCount) {
        int returnNumber;
        int numBottomShow = mCardStackView.getNumBottomShow();
        int cardChildCount = mCardStackView.getChildCount();
        int cardSelectPosition = mCardStackView.getSelectPosition();
        int overlapGapsCollapse = mCardStackView.getOverlapGapsCollapse();
        if (numBottomShow - (cardChildCount - cardSelectPosition) > numBottomShow) {
            returnNumber = numBottomShow;
        } else {
            returnNumber = cardChildCount - cardSelectPosition - 1;
        }
        int difference = numBottomShow - collapseShowItemCount - returnNumber;
        returnNumber = difference * overlapGapsCollapse;
        return returnNumber;
    }


    public int getDuration() {
        return mCardStackView.getDuration();
    }
}
