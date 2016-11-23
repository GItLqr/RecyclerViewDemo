package com.lqr.recylerviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 删除动画和分割线
 */
public class ActivityThree extends AppCompatActivity {

    private RecyclerView mRv;
    private List<String> mData = new ArrayList<>();
    private MyStaggeredAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mData.add("item " + i);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_three);
        mRv = (RecyclerView) findViewById(R.id.rv);

        mAdapter = new MyStaggeredAdapter(this, mData);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRv.setAdapter(mAdapter);
        mRv.setLayoutManager(mLinearLayoutManager);

        //设置条目动画
        mRv.setItemAnimator(new DefaultItemAnimator());

        //设置分割线
        mRv.addItemDecoration(new MyItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    /**
     * 删除选中
     *
     * @param view
     */
    public void delete(View view) {
        mAdapter.remove(mAdapter.select);
    }

    /**
     * @创建者 CSDN_LQR
     * @描述 RecyclerView和条目分割线
     */
    class MyItemDecoration extends RecyclerView.ItemDecoration {
        private Context mContext;
        private int mOrientatioin;
        private Drawable mDivider;

        public MyItemDecoration(Context context, int orientatioin) {
            mContext = context;
            setOrientatioin(orientatioin);
            //得到分割线Drawable
            TypedArray ta = mContext.obtainStyledAttributes(ATTRS);
            mDivider = ta.getDrawable(0);
            ta.recycle();
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            if (mOrientatioin == LinearLayoutManager.HORIZONTAL) {
                drawVerticalLine(c, parent, state);
            } else {
                drawHorizontalLine(c, parent, state);
            }
        }

        /**
         * 画横线
         *
         * @param c
         * @param parent
         * @param state
         */
        public void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);

                //获得child的布局信息
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        /**
         * 画竖线
         *
         * @param c
         * @param parent
         * @param state
         */
        public void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int top = parent.getPaddingTop();
            int bottom = parent.getHeight() - parent.getPaddingBottom();
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);

                //获得child的布局信息
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

        }

        /**
         * 由于Divider也有长宽高，每一个item需要向下或者向右偏移
         *
         * @param outRect
         * @param view
         * @param parent
         * @param state
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //画横线，就是往下偏移一个分割线的高度
            if (mOrientatioin == LinearLayoutManager.HORIZONTAL) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {//画竖线，就是往右偏移一个分割线的宽度
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }

        /**
         * 设置屏幕方向
         *
         * @param orientatioin
         */
        private void setOrientatioin(int orientatioin) {
            if (orientatioin != LinearLayoutManager.HORIZONTAL && orientatioin != LinearLayoutManager.VERTICAL) {
                throw new IllegalArgumentException("请设置一个方向");
            }
            mOrientatioin = orientatioin;
        }

        public final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };
    }

}
