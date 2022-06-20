package com.hy.baseapp.common.utils;

import static me.hy.jetpackmvvm.ext.util.CommonExtKt.dp2px;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by lht on 2017/10/26.
 * recylerview Item之间的间隔
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mBottomSpace;
    private int mLeftSpace;
    private int mRightSpace;
    private int mTopSpace;

    /**
     * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * The default implementation sets the bounds of outRect to 0 and returns.
     * <p>
     * <p>
     * If this ItemDecoration does not affect the positioning of item widget, it should set
     * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
     * before returning.
     * <p>
     * <p>
     * If you need to access Adapter for additional data, you can call
     * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
     * View.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = 0;
        }

        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildPosition(view) == 0){
//            outRect.top = 0;
//    }
        outRect.top = mTopSpace;
        outRect.left = mLeftSpace;
        outRect.right = mRightSpace;
        outRect.bottom = mBottomSpace;
    }

    public SpaceItemDecoration(int topSpace, int bottomSpace, int leftSpace, int rightSpace) {
        this.mBottomSpace = dp2px(bottomSpace);
        this.mTopSpace = dp2px(topSpace);
        this.mLeftSpace = dp2px(leftSpace);
        this.mRightSpace = dp2px(rightSpace);
    }

    public SpaceItemDecoration(int space) {
        this.mBottomSpace = dp2px(space);
        this.mTopSpace = dp2px(space);
        this.mLeftSpace = dp2px(space);
        this.mRightSpace = dp2px(space);
    }

}
