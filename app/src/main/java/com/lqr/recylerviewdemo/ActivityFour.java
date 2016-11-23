package com.lqr.recylerviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 分类型RecyclerView(ViewType)
 */
public class ActivityFour extends AppCompatActivity {

    private List<String> mData;
    private RecyclerView mRv;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initView();
    }


    private void initView() {
        setContentView(R.layout.activity_four);
        mRv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(linearLayoutManager);
        mMyAdapter = new MyAdapter(this, mData);
        mRv.setAdapter(mMyAdapter);
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add("item " + i);
        }
    }


    /**
     * @创建者 CSDN_LQR
     * @描述
     */
    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context mContext;
        private List<String> mData;

        public MyAdapter(Context context, List<String> data) {
            mContext = context;
            mData = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == 0) {
                View view = View.inflate(mContext, R.layout.vp, null);
                return new Item0ViewHolder(view);
            } else {
                View view = View.inflate(mContext, R.layout.item_text, null);
                return new MyViewHolder(view);
            }

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof Item0ViewHolder) {
                ((Item0ViewHolder) holder).mVp.setAdapter(new PagerAdapter() {
                    @Override
                    public int getCount() {
                        return mData.size();
                    }

                    @Override
                    public boolean isViewFromObject(View view, Object object) {
                        return view == object;
                    }

                    @Override
                    public Object instantiateItem(ViewGroup container, int position) {
                        View view = View.inflate(mContext, R.layout.item_text, null);

                        TextView tv = (TextView) view.findViewById(R.id.tv);
                        tv.setText(mData.get(position));

                        container.addView(view);
                        return view;
                    }

                    @Override
                    public void destroyItem(ViewGroup container, int position, Object object) {
                        container.removeView((View) object);
                    }
                });
            } else {
                ((MyViewHolder) holder).mTv.setText(mData.get(position - 1));
            }
        }


        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public int getItemCount() {
            return mData.size() + 1;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView mTv;

            public MyViewHolder(View itemView) {
                super(itemView);
                mTv = (TextView) itemView.findViewById(R.id.tv);
            }
        }

        /**
         * @创建者 CSDN_LQR
         * @描述 第一个条目的ViewHolder
         */
        public class Item0ViewHolder extends RecyclerView.ViewHolder {
            ViewPager mVp;

            public Item0ViewHolder(View itemView) {
                super(itemView);
                mVp = (ViewPager) itemView.findViewById(R.id.vp);
            }
        }
    }
}
