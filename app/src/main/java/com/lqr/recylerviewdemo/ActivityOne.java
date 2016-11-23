package com.lqr.recylerviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 listview和gridview
 */
public class ActivityOne extends AppCompatActivity {

    private List<String> mData;
    private RecyclerView mRvOne;
    private RecyclerView mRvTwo;
    private Button mBtnChange;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView();
        initData();
        initListener();
    }

    private void init() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("item " + i);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_one);
        mRvOne = (RecyclerView) findViewById(R.id.rvOne);
        mRvTwo = (RecyclerView) findViewById(R.id.rvTwo);
        mBtnChange = (Button) findViewById(R.id.btnChange);

        //创建适配器
        mMyAdapter = new MyAdapter(this, mData);

        //竖直RecyclerView
        LinearLayoutManager llmOne = new LinearLayoutManager(this);
//        llmOne.setOrientation(LinearLayoutManager.VERTICAL);//默认竖起方向
        mRvOne.setLayoutManager(llmOne);
        mRvOne.setAdapter(mMyAdapter);

        //水平recyclerView
        LinearLayoutManager llmTwo = new LinearLayoutManager(this);
        llmTwo.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTwo.setLayoutManager(llmTwo);
        mRvTwo.setAdapter(mMyAdapter);

    }

    private void initData() {

    }

    private void initListener() {
        mBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用风格布局
                //参1：上下文
                //参2：列数（默认竖直方向）
                GridLayoutManager glmOne = new GridLayoutManager(ActivityOne.this, 2);
                mRvOne.setLayoutManager(glmOne);
//                mRvOne.setAdapter(mMyAdapter);

                //参1：上下文
                //参2：行数（设置为水平方向）
                GridLayoutManager glmTwo = new GridLayoutManager(ActivityOne.this, 4);
                glmTwo.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRvTwo.setLayoutManager(glmTwo);
//                mRvTwo.setAdapter(mMyAdapter);
            }
        });
    }

    /**
     * @创建者 CSDN_LQR
     * @描述 自定义RecyclerView的适配器和ViewHolder
     */
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

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView mTv;

            public MyViewHolder(View itemView) {
                super(itemView);
                mTv = (TextView) itemView.findViewById(R.id.tv);
            }
        }
    }


}
