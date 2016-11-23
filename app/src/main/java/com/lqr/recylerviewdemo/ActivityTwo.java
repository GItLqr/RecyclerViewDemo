package com.lqr.recylerviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * @创建者 CSDN_LQR
 * @描述 瀑布流布局和流式布局
 */
public class ActivityTwo extends AppCompatActivity {

    private List<String> mData;
    private RecyclerView mRvOne;
    private TagFlowLayout mTfl;
    private String[] mVals = new String[]
            {"Button ImageView", "Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView", "Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
        initListener();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("item " + i);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_two);
        mRvOne = (RecyclerView) findViewById(R.id.rvOne);
        mTfl = (TagFlowLayout) findViewById(R.id.tfl);

        /*================== 瀑布流 ==================*/
        //3列、竖直方向
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        MyStaggeredAdapter myStaggeredAdapter = new MyStaggeredAdapter(this, mData);

        mRvOne.setLayoutManager(staggeredGridLayoutManager);
        mRvOne.setAdapter(myStaggeredAdapter);

        /*================== 流式布局 ==================*/
        mTfl.setAdapter(new TagAdapter<String>(mVals) {

            private List<Integer> heights = new ArrayList<Integer>();
            private List<Integer> colors = new ArrayList<Integer>();

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = View.inflate(ActivityTwo.this, R.layout.item_text, null);
                TextView tv = (TextView) view.findViewById(R.id.tv);

                if (heights.size() <= mVals.length) {
                    heights.add((int) (100 + Math.random() * 30));
                }

                if (colors.size() <= mVals.length) {
                    int red = (int) (30 + Math.random() * 195);
                    int green = (int) (30 + Math.random() * 195);
                    int blue = (int) (30 + Math.random() * 195);
                    colors.add(Color.argb(255, red, green, blue));
                }

                ViewGroup.LayoutParams lp = tv.getLayoutParams();
                lp.height = heights.get(position);
                tv.setLayoutParams(lp);
                tv.setBackgroundColor(colors.get(position));

                tv.setText(s);
                return view;
            }
        });
    }

    private void initListener() {

    }

}
