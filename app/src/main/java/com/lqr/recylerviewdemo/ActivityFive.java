package com.lqr.recylerviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 RecyclerView上拉刷新
 */
public class ActivityFive extends AppCompatActivity {

    private List<String> mData;
    private RecyclerView mRv;
    private MaterialRefreshLayout mMaterialRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add("item " + i);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_five);
        mMaterialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        mRv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        MyAdapter myAdapter = new MyAdapter(this, mData);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setAdapter(myAdapter);

        mMaterialRefreshLayout.setWaveShow(true);
//        mMaterialRefreshLayout.setWaveColor(Color.parseColor("#60ff2020"));
        mMaterialRefreshLayout.setWaveColor(Color.parseColor("#abcdef"));
        mMaterialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                mMaterialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMaterialRefreshLayout.finishRefresh();
                    }
                }, 3000);
            }

            @Override
            public void onfinish() {
                super.onfinish();
                Toast.makeText(getApplicationContext(), "刷新完毕", Toast.LENGTH_LONG).show();
            }
        });
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private Context mContext;
        private List<String> mData;

        public MyAdapter(Context context, List<String> data) {
            mContext = context;
            mData = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(mContext, R.layout.item_text, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mTv.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView mTv;

            public MyViewHolder(View itemView) {
                super(itemView);
                mTv = (TextView) itemView.findViewById(R.id.tv);
            }
        }
    }
}
